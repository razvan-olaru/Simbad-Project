package main.java.softdesign;


import java.awt.Color;
import javax.vecmath.Color3f;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;
import simbad.sim.Box;
import simbad.sim.EnvironmentDescription;
import simbad.sim.Wall;

public class Environment  extends EnvironmentDescription{
	//Constructor
	public Environment() {
		// turn on the lights
        this.light1IsOn = true;
        this.light2IsOn = true;
        
        // enable the physics engine in order to have better physics effects on the objects
        this.setUsePhysics(true);
        
        // show the axes so that we know where things are
        this.showAxis(true);
        
        // Set the world size
        this.setWorldSize(30);
        
        Wall w1 = new Wall(new Vector3d(-12, 0, 0), 24, 2, this);
        w1.setColor(new Color3f(Color.BLACK));
        w1.rotate90(1);
        add(w1);
        Wall w2 = new Wall(new Vector3d(12, 0, 0), 24, 2, this);
        w2.setColor(new Color3f(Color.BLACK));
        w2.rotate90(1);
        add(w2);
        Wall w3 = new Wall(new Vector3d(0, 0, 12), 24, 2, this);
        w3.setColor(new Color3f(Color.BLACK));
        add(w3);
        Wall w4 = new Wall(new Vector3d(0, 0, -12), 24, 2, this);
        w4.setColor(new Color3f(Color.BLACK));
        add(w4);
        
        //create maze like environment 
        Wall interior = new Wall(new Vector3d(10, 0, -4.5), 3, 1, this);
        interior.setColor(new Color3f(Color.BLACK));
        add(interior);
        Wall interior12 = new Wall(new Vector3d(10, 0, 5), 2, 1, this);
        interior12.setColor(new Color3f(Color.BLACK));
        interior12.rotate90(1);
        add(interior12);
        Wall interior2 = new Wall(new Vector3d(5, 0, 10), 2, 1, this);
        interior2.setColor(new Color3f(Color.CYAN));
        add(interior2);
        Wall interior3 = new Wall(new Vector3d(5, 0, 0), 6, 1, this);
        interior3.setColor(new Color3f(Color.GRAY));
        add(interior3); 
        Wall interior4 = new Wall(new Vector3d(5, 0, 0), 6, 1, this);
        interior4.setColor(new Color3f(Color.GRAY));
        interior4.rotate90(1);
        add(interior4);
        Wall interior5 = new Wall(new Vector3d(-10, 0, -5), 8, 1, this);
        interior5.setColor(new Color3f(Color.GRAY));
        interior5.rotateY(30);
        add(interior5);
        Wall interior6 = new Wall(new Vector3d(-8, 0, -5), 2, 1, this);
        interior6.setColor(new Color3f(Color.BLACK));
        interior6.rotateY(45);
        add(interior6);
        Wall interior7 = new Wall(new Vector3d(-3, 0, -7), 2, 1, this);
        interior7.setColor(new Color3f(Color.BLACK));
        interior7.rotateY(170);
        add(interior7);
        Wall interior8 = new Wall(new Vector3d(3, 0, 7), 5, 1, this);
        interior8.setColor(new Color3f(Color.GRAY));
        interior8.rotateY(60);
        add(interior8);
        Wall interior9 = new Wall(new Vector3d(0, 0, 7), 5, 1, this); 
        interior9.setColor(new Color3f(Color.GRAY));
        interior9.rotateY(-60);
        add(interior9);
        Wall interior10 = new Wall(new Vector3d(9, 0, 9), 2, 1, this);
        interior10.setColor(new Color3f(Color.CYAN));
        interior10.rotateY(10);
        add(interior10);
        Wall interior11 = new Wall(new Vector3d(-9, 0, 9), 4, 1, this);
        interior11.rotateY(-10);
        interior11.setColor(new Color3f(Color.GRAY));
        add(interior11);
        Wall interior13 = new Wall(new Vector3d(8, 0, -9), 4, 1, this);
        interior13.rotateY(5);
        interior13.setColor(new Color3f(Color.RED));
        add(interior13); 
        
        //Assume CYAN colour is the target
        //create random placement of boxes:
        Box b1 = new Box(new Vector3d(10, 0 , 11), new Vector3f(1, 1, 1), this);
        b1.setColor(new Color3f(Color.CYAN));
        add(b1);
        Box b2 = new Box(new Vector3d(3, 0 , -3), new Vector3f(1, 1, 1), this);
        b2.setColor(new Color3f(Color.ORANGE));
        add(b2);
        Box b3 = new Box(new Vector3d(10, 0 , -9), new Vector3f(1, 1, 1), this);
        b3.setColor(new Color3f(Color.RED));
        add(b3);
        Box b4 = new Box(new Vector3d(-5, 0 , -9), new Vector3f(1, 1, 1), this);
        b4.setColor(new Color3f(Color.GRAY));
        add(b4);
        Box b5 = new Box(new Vector3d(-3, 0 , 9), new Vector3f(1, 1, 1), this);
        b5.setColor(new Color3f(Color.GRAY));
        add(b5); 
	}
}