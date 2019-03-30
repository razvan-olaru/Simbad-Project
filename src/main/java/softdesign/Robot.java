package main.java.softdesign;

import simbad.sim.Agent;
import simbad.sim.CameraSensor;
import simbad.sim.RangeSensorBelt;
import simbad.sim.RobotFactory;

import javax.media.j3d.Transform3D;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;
import java.awt.image.BufferedImage;

/*
The Robot class implements an agent that interacts with the created environment.
Its purpose is to inspect the environment and exctract information regarding free paths, obstacles and goal position.
This information is further used to update the CentralStation.
The target of the robot is to find the goal box and take a picture of it.
When the robot touches the goal, its search is complete and it shall stop moving.
*/

public class Robot extends Agent {

	private static final EnvColor goalColor = EnvColor.RED;	// The colour of the goal box

	/* t3d is a 4x4 double-precision floating point matrix, 
	further used to perform translations and rotations */
	private Transform3D t3d = new Transform3D();
	private RangeSensorBelt sonar; // Sensor that measures the distance to an object
	private RangeSensorBelt bumper; // Sensor with boolean values; true if it touches another object.
	private CameraSensor camera; // Camera used to inspect the environment
	private BufferedImage cameraImage; // Image captured by the camera

	/* Database which learns a mapping of the environment 
	by communicating with the robots */
	private CentralStation centralStation;
	private EnvironmentInformation newInfo; // Check newly learned information
	private boolean spotGoal = false; // Boolean value; True if the robot identified the goal on camera
	private boolean approachingGoal = false; // Boolean value; True if the robot is close to the goal
	private boolean touchGoal = false; // Boolean value; True if the robot reached the goal

	// Robot constructor, including sensors initialisation
	Robot(Vector3d position, String name) {
		super(position, name);
		sonar = RobotFactory.addSonarBeltSensor(this, 24);
		bumper = RobotFactory.addBumperBeltSensor(this, 12);
		camera = RobotFactory.addCameraSensor(this);
		cameraImage = camera.createCompatibleImage();
	}

	// Function used to easily differentiate between colors based on Java.Color constants
	private EnvColor rgbToEnvColor(int r, int g, int b) {
		if (r == g && g == b)
			return EnvColor.BW;
		else if (r == 0 && g == 0)
			return EnvColor.BLUE;
		else if (b == 0 && g == 0)
			return EnvColor.RED;
		else if (r == 0 && b == 0)
			return EnvColor.GREEN;
		else if (g == 0)
			return EnvColor.MAGENTA;
		else if (r == 0)
			return EnvColor.CYAN;
		else if (b == 0)
			return EnvColor.YELLOW;
		else
			return null;
	}

	/* 
	In this method, the rover takes a picture of the environment.
	It analyzes the center of the picture (precisely 25%) and searches for the goal colour.
	After that, it decides whether the robot sees the goal or not. In the first case,
	it can approximate whether the box is close or not.
	*/
	private void analyzeColors() {
		camera.copyVisionImage(cameraImage);
		int height = camera.getImageHeight();
		int width = camera.getImageWidth();
		int color, red, green, blue;
		int colorCounter = 0;

		for (int i = height / 4; i < height / 4 * 3; i++) {
			for (int j = width / 4; j < width / 4 * 3; j++) {
				color = cameraImage.getRGB(i, j);

				// Shifting bits from color to get the specific rgb values
				red = (color >> 16) & 0x000000FF;
				green = (color >> 8) & 0x000000FF;
				blue = (color) & 0x000000FF;

				if (rgbToEnvColor(red, green, blue) == goalColor)
					colorCounter++;
			}
		}
		if (colorCounter > 250)
			spotGoal = true;
		if (colorCounter < 100)
			spotGoal = false;
		if (colorCounter > 2000)
			approachingGoal = true;
		if (colorCounter < 1200)
			touchGoal = false;
	}

	// Initialise rover's behaviour and create CentralStation object
	@Override
	protected void initBehavior() {
		touchGoal = false;
		centralStation = CentralStation.getInstance();
		newInfo = new EnvironmentInformation();
	}

	/*
	Assuming the rover's point of reference is the camera's position (the same one as the position of sonar number 0):
	This method returns the rover's orientation with respect to the OX axis (equivalent to Simbad's OZ axis).
	The measurement of the robot's orientation is measured in degrees in trigonometric direction (anti clockwise).
	The method returns the orientation in degree numbers.
	*/
	private int getAngle() {
		getRotationTransform(t3d);
		double[] values = new double[16];
		t3d.get(values);
		double degree = Math.acos(values[0]) / (Math.PI * 2) * 360;
		if (Math.asin(values[2]) / (Math.PI * 2) * 360 > 0)
			degree = 180 + (180 - degree);
		degree = 360 - degree;

		return (int)degree;
	}

	// Applies both translational and rotational velocity
	private void setVelocity(double translation, double rotation){
		setTranslationalVelocity(translation);		
		setRotationalVelocity(rotation);
	}

	/*
	Method for obstacle avoidance. The rover checks its sonars' measurements.
	If there is an obstacle nearby, the updateDatabase() method is called which will further
	bring new information to the central station. More than that, the rover's movement is assigned randomly.
	If there is no obstacle near the rover, its velocity is fixed.
	*/
	private void avoidObstacles(){
		double left = sonar.getFrontLeftQuadrantMeasurement();
		double right = sonar.getFrontRightQuadrantMeasurement();
		double front = sonar.getFrontQuadrantMeasurement();

		if ((front < 0.4) || (left < 0.4) || (right < 0.4)) {
			updateDatabase(3);
			if (left < right)
				setRotationalVelocity(-1 - (0.1 * Math.random()));
			else
				setRotationalVelocity(1 - (0.1 * Math.random()));
			setTranslationalVelocity(0);	
		}
		else
			setVelocity(0.6, 0.4);
	}

	/*
	This method manages the rover's movement.
	Firstly, it checks whether the rover should stop its movement.
	If it is not the case, analyze environment and perform movement based on the distance to the goal:
		-Update database with the value 4 and stop rover's movement when it reaches the goal
		-Update database with the value 1 when rover finds an empty spot
		-Handle obstacle avoidance when the rover's bumper and sonar sensors detect object which is not goal
	*/
	@Override
	protected void performBehavior() {
		if(centralStation.shouldFinish() || centralStation.getMissionComplete() || touchGoal) {
			setVelocity(0, 0);
			return;
		}

		analyzeColors();
		
		if(approachingGoal){
			if(bumper.oneHasHit()){
				setVelocity(0, 0);
				touchGoal = true;
				updateDatabase(4);
				centralStation.completeMission();
			}
			else{
				updateDatabase(1);
				setVelocity(1, 0);
			}
		}
		else if((spotGoal) && (!touchGoal)){
			updateDatabase(1);
			if(bumper.oneHasHit())
				avoidObstacles();
			else
				setVelocity(0.6, 0);						
		}
		else if (!spotGoal && !touchGoal){
			updateDatabase(1);
			if (sonar.oneHasHit())                                  		
				avoidObstacles();
			else
				setVelocity(0.6, 0);
		}
	}

	/*
	This method checks the information that could further be updated in the database.
	Firstly, it gets the rover's current position. After that, the updateMatrix function 
	from the EnvironmentInformation class will verify possible neighbouring coordinates 
	that may be updated.
	*/
	private void updateDatabase(int value){
		double left = sonar.getQuadrantMeasurement(0.17,Math.PI/2); // from 10 to 90
		double right = sonar.getQuadrantMeasurement(6.1,3*(Math.PI/2)); // from 350 to 270 
		double front = sonar.getMeasurement(0);

		Point3d coords = new Point3d();
		getCoords(coords);

		Coordinates coordinates = new Coordinates(coords);

		newInfo.updateMeasurements(left,right,front);
				
		newInfo.checkCoordinates(coordinates, value, getAngle());
	}
}
