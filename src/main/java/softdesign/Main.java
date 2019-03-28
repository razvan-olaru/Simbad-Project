package main.java.softdesign;

/**
 *
 * Start working on this file
 *
 * You can use as many source files and packages
 * as you like, as long as everything is below
 * main.java.softdesign
 *
 * Some resources:
 *
 * - https://github.com/jimmikaelkael/simbad/tree/master/src
 * - https://www.ibm.com/developerworks/java/library/j-robots/
 * - https://github.com/VU-SoftwareDesign/SimbadMultiRobot
 * - https://github.com/VU-SoftwareDesign/SimbadHelloWorld
 *
 */


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