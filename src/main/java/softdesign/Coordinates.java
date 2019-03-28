package main.java.softdesign;
import javax.vecmath.Point3d;

/*
The Coordinates class keeps track of the rover's position on the {OX, OY} axis,
which is equivalent to Simbad's {OZ, OX} axis
*/

public class Coordinates  {
    int x;
	int y;
	
	// Coordinates' constructor
    public Coordinates (){
	}
	
	/* This method converts Simbad's first coordinate from the OZ to the OX axis.
	More than that, it transforms it from a value in the range [0, 23] to 
	an int in the range [0, 46] in order to fit the database 46x46 matrix */
    public void getCoordinatesofX (Point3d coords){
        x = (int)(coords.z + 11);
        if(((coords.z + 11) - x) >= 0.5)
            x = 2*x + 1;
        else
			x = 2*x; 
	}
	
	/* This method converts Simbad's second coordinate from the OX to the OY axis.
	More than that, it transforms it from a value in the range [0, 23] to 
	an int in the range [0, 46] in order to fit the database 46x46 matrix */
    public void getCoordinatesofY(Point3d coords){
        y = (int)(coords.x + 11);
        if(((coords.x + 11) - y) >= 0.5)
            y = 2*y + 1;
        else
            y = 2*y;
        }
}