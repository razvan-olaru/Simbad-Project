package main.java.softdesign;

import javax.vecmath.Vector3d;
import simbad.sim.Agent;
import simbad.sim.CameraSensor;
import simbad.sim.RangeSensorBelt;
import simbad.sim.RobotFactory;

public class Robot extends Agent {
	
	RangeSensorBelt sonar;
	RangeSensorBelt bumper;
	CameraSensor camera;

	public Robot(Vector3d position, String name) {
        super(position, name);
		// TODO Auto-generated constructor stub
        // Add sonar sensor
        sonar = RobotFactory.addSonarBeltSensor(this, 4);
        // Add bumper sensor
        bumper = RobotFactory.addBumperBeltSensor(this, 8);
        // Add camera sensor
        camera = RobotFactory.addCameraSensor(this);
	}
	
	private void distance() {
		// Get distance from sonar in every 20 frames
		if(getCounter() % 20 == 0) {
			for(int i = 0; i < sonar.getNumSensors(); i++) {
				double range = sonar.getMeasurement(i);
				System.out.println("Sonar : " + i + " distance = " + range);
			}
		}
	}
	
	private void hit() {
		// detects hit from bumper in every 20 frames
		if(getCounter() % 20 == 0) {
			for(int i = 0; i < bumper.getNumSensors(); i++) {
				//double range = bumper.getMeasurement(i);
				boolean hit = bumper.hasHit(i);
				System.out.println("Bumper : " + i + " has a hit = " + hit);
			}
		}
	}
	private void move() {
		// set speed 0.5 m/s
		this.setTranslationalVelocity(0.5);
		// change the orientation
		if(getCounter() % 100 == 0) {
			this.setRotationalVelocity(Math.PI /2 * (0.5 - Math.random()));
			
		}
	}

	@Override
	protected void initBehavior() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void performBehavior() {
		// TODO Auto-generated method stub
		distance();
		if(this.collisionDetected()) {
			hit();
			System.out.println("Collision");
			//stays and rotates until it gets a way out!
			this.setTranslationalVelocity(0);
			this.setRotationalVelocity(Math.PI /2);
		}else {
			System.out.println("No Obstacles");
			//move the robots
			move();
		}
		
	}

}