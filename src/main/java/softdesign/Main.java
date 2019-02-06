package main.java.softdesign;

import javax.vecmath.Vector3d;

import simbad.gui.Simbad;
import simbad.sim.EnvironmentDescription;

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

public class Main {

	public static void main(String[] args) {
        // request antialising so that diagonal lines are not "starry"
        System.setProperty("j3d.implicitAntialiasing", "true");
        
        // creation of the environment containing all obstacles and robots
        EnvironmentDescription environment = new Environment();
        
        // create two instances of the same example robot
        Robot robot1 = new Robot(new Vector3d(0, 0, 0), "Robot 1");
        Robot robot2 = new Robot(new Vector3d(-2, 0, -2), "Robot 2");

        // add the two robots to the environment
        environment.add(robot1);
        environment.add(robot2);
        
        // here we create an instance of the whole Simbad simulator and we assign the newly created environment 
        Simbad frame = new Simbad(environment, false);
        frame.update(frame.getGraphics());
	}
}
