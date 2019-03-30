package main.java.softdesign;

import javax.vecmath.Vector3d;
import simbad.gui.Simbad;

public class Main {

	public static void main(String[] args) {
        
		// Request antialising so that diagonal lines are not "stairy"
        System.setProperty("j3d.implicitAntialiasing", "true");

        // Instance of environment
        Environment environment = new Environment();

        // Create instances of robot
        Robot robot1 = new Robot(new Vector3d(-11, 0, -11), "Robot-1");
        Robot robot2 = new Robot(new Vector3d(11,  0, -11), "Robot-2");
        Robot robot3 = new Robot(new Vector3d(-11,  0, 11), "Robot-3");
        Robot robot4 = new Robot(new Vector3d(11, 0, 11), "Robot-4");

        // Add robots into environment
		environment.add(robot1);
		environment.add(robot2);
        environment.add(robot3);
        environment.add(robot4);

        // Create instance of simbad simulator
        Simbad frame = new Simbad(environment, false);
        Utility.addStopButton(frame);

        frame.update(frame.getGraphics());
	}
}