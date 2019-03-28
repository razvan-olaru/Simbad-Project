package main.java.softdesign;

import java.awt.Color;
import javax.vecmath.Color3f;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;
import simbad.sim.Box;
import simbad.sim.EnvironmentDescription;
import simbad.sim.Wall;
import java.util.ArrayList;

/*
This class builds the environment in which the rovers complete their mission.
It consists of four external walls and a number of obstacles placed in a maze-like structure.
The goal of the mission is represented by a red box placed in the middle of the maze.
*/
public class Environment extends EnvironmentDescription {

	ArrayList<Wall> Walls = new ArrayList<Wall>(); // Array of external walls
	ArrayList<Box> Obstacles = new ArrayList<Box>(); // Array of obstacles 
	Box goal; // Goal which will be searched for by the rovers

	public Environment() {

		light1SetPosition(-15,5,-15);
		light2SetPosition(15,5,15);

		this.light1IsOn = true;
		this.light2IsOn = true;

		this.setUsePhysics(true);
		this.setWorldSize(25);

		Wall exterior1 = new Wall(new Vector3d(-12, 0, 0), 24, 2, this);
		exterior1.setColor(new Color3f(Color.YELLOW));
		exterior1.rotate90(1);
		Walls.add(exterior1);

		Wall exterior2 = new Wall(new Vector3d(12, 0, 0), 24, 2, this);
		exterior2.setColor(new Color3f(Color.BLUE));
		exterior2.rotate90(1);
		Walls.add(exterior2);

		Wall exterior3 = new Wall(new Vector3d(0, 0, 12), 24, 2, this);
		exterior3.setColor(new Color3f(Color.CYAN));
		Walls.add(exterior3);

		Wall exterior4 = new Wall(new Vector3d(0, 0, -12), 24, 2, this);
		exterior4.setColor(new Color3f(Color.MAGENTA));
		Walls.add(exterior4);

		Wall interior1 = new Wall(new Vector3d(-11, 0, 0), 2, 2, this);
		interior1.setColor(new Color3f(Color.YELLOW));
		Obstacles.add(interior1);

		Wall interior2 = new Wall(new Vector3d(0, 0, 10), 16, 2, this);
		interior2.setColor(new Color3f(Color.CYAN));
		Obstacles.add(interior2);

		Wall interior3 = new Wall(new Vector3d(10, 0, 0), 16, 2, this);
		interior3.setColor(new Color3f(Color.BLUE));
		interior3.rotate90(1);
		Obstacles.add(interior3);

		Wall interior4 = new Wall(new Vector3d(0, 0, -10), 16, 2, this);
		interior4.setColor(new Color3f(Color.MAGENTA));
		Obstacles.add(interior4);

		Wall interior5 = new Wall(new Vector3d(8.5, 0, 0), 7, 2, this);
		interior5.setColor(new Color3f(Color.BLUE));
		Obstacles.add(interior5);

		Wall interior6 = new Wall(new Vector3d(0, 0, 7), 6, 2, this);
		interior6.setColor(new Color3f(Color.CYAN));
		interior6.rotate90(1);
		Obstacles.add(interior6);

		Wall interior7 = new Wall(new Vector3d(2, 0, 6), 4, 2, this);
		interior7.setColor(new Color3f(Color.BLUE));
		interior7.rotate90(1);
		Obstacles.add(interior7);

		Wall interior8 = new Wall(new Vector3d(3.5, 0, 8), 3, 2, this);
		interior8.setColor(new Color3f(Color.BLUE));
		Obstacles.add(interior8);

		Wall interior9 = new Wall(new Vector3d(8.5, 0, 8), 3, 2, this);
		interior9.setColor(new Color3f(Color.BLUE));
		Obstacles.add(interior9);

		Wall interior10 = new Wall(new Vector3d(5, 0, 6), 4, 2, this);
		interior10.setColor(new Color3f(Color.BLUE));
		interior10.rotate90(1);
		Obstacles.add(interior10);

		Wall interior11 = new Wall(new Vector3d(7, 0, 5), 6, 2, this);
		interior11.setColor(new Color3f(Color.BLUE));
		interior11.rotate90(1);
		Obstacles.add(interior11);

		Wall interior12 = new Wall(new Vector3d(-8, 0, 0), 12, 2, this);
		interior12.setColor(new Color3f(Color.CYAN));
		interior12.rotate90(1);
		Obstacles.add(interior12);

		Box box = new Box(new Vector3d(-5, 0 , 5.5), new Vector3f(6, 2, 1), this);
		box.setColor(new Color3f(Color.CYAN));
		Obstacles.add(box);

		Wall interior13 = new Wall(new Vector3d(-6.5, 0, 0), 3, 2, this);
		interior13.setColor(new Color3f(Color.CYAN));
		Obstacles.add(interior13);

		Wall interior14 = new Wall(new Vector3d(0, 0, -8.5), 3, 2, this);
		interior14.setColor(new Color3f(Color.MAGENTA));
		interior14.rotate90(1);
		Obstacles.add(interior14);

		Wall interior15 = new Wall(new Vector3d(-2, 0, -8), 4, 2, this);
		interior15.setColor(new Color3f(Color.MAGENTA));
		interior15.rotate90(1);
		Obstacles.add(interior15);

		Wall interior16 = new Wall(new Vector3d(-7, 0, -8), 6, 2, this);
		interior16.setColor(new Color3f(Color.YELLOW));
		Obstacles.add(interior16);

		Wall interior17 = new Wall(new Vector3d(-6, 0, -4), 4, 2, this);
		interior17.setColor(new Color3f(Color.CYAN));
		interior17.rotate90(1);
		Obstacles.add(interior17);

		Wall interior18 = new Wall(new Vector3d(-4, 0, -6), 4, 2, this);
		interior18.setColor(new Color3f(Color.MAGENTA));
		Obstacles.add(interior18);

		Wall interior19 = new Wall(new Vector3d(5, 0, -7), 5, 2, this);
		interior19.setColor(new Color3f(Color.BLUE));
		Obstacles.add(interior19);

		Wall interior20 = new Wall(new Vector3d(7.5, 0, -4.5), 5, 2, this);
		interior20.setColor(new Color3f(Color.BLUE));
		interior20.rotate90(1);
		Obstacles.add(interior20);

		Wall interior21 = new Wall(new Vector3d(5, 0, -2.5), 5, 2, this);
		interior21.setColor(new Color3f(Color.BLUE));
		interior21.rotate90(1);
		Obstacles.add(interior21);

		Wall interior22 = new Wall(new Vector3d(-10, 0, 0), 16, 2, this);
		interior22.setColor(new Color3f(Color.YELLOW));
		interior22.rotate90(1);
		Obstacles.add(interior22);


		goal = new Box(new Vector3d(0, 0 , 0), new Vector3f(3, 2, 3), this);
		goal.setColor(new Color3f(Color.RED));

		for (int i = 0; i < Walls.size(); i++)
			add(Walls.get(i));

		for (int i = 0; i < Obstacles.size(); i++)
			add(Obstacles.get(i));

		add(goal);
	}
}