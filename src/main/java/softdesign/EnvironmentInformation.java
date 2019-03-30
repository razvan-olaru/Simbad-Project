package main.java.softdesign;

/*
This class receives new information observed by the rover.
Each rover is analyzed in approximately 50 frames per position, therefore it is
worth checking whether the matrix should be updated with the received value,
and whether the neighbouring coordinates of the rover should be further inspected.
*/
public final class EnvironmentInformation {
    private double currentX; // Current known x coordinate of the rover
	private double currentY; // Current known y coordinate of the rover
	private double [] measurements = new double[3]; // Sonar measurements of the rover
	
	// EnvironmentInformation Constructor
    public EnvironmentInformation() {}
	
	/* This method receives the rover's sonar measurements and adds
	them to an array */
	public void updateMeasurements(double left, double right, double front){		
		measurements[0] = left;
		measurements[1] = right;
		measurements[2] = front;		
	}

	/* This method checks the importance of the received information, where the angle parameter 
	represents the rover's orientation with respect to the OX axis.
		-If the new coordinates are identical to the last known position of the rover,
		the method does not update the matrix
		-If the new coordinates are different, update the data structures, the matrix will immediately update the matrix with either
		1 (representing a free spot), or 4 (representing the goal)
		-If the new coordinates are different and the value is equal to 3 (therefore, the rover encountered an obstacle),
		the method will update the database according to the obstacle's position in regard to the rover's sonar sensors. */
    public  void checkCoordinates(Coordinates newCoord, int value, int angle){
		CentralStation centralStation = CentralStation.getInstance(); // The database to be further updated
		
        if((currentX != newCoord.x) || (currentY != newCoord.y) && value != 3){
			centralStation.updateCoords(newCoord.x,  newCoord.y, value);
			currentX = newCoord.x;
			currentY = newCoord.y;
		}
		
        if(value == 4)
			centralStation.updateCoords(newCoord.x,  newCoord.y, value);

		if(value == 3){
			if (newCoord.x -1 > -1 && newCoord.x + 1 < 46 &&
				newCoord.y -1 > -1 && newCoord.y + 1 < 46) {
				if(angle > 45 && angle < 135){
					if (measurements[0] < 0.4){
						centralStation.updateCoords(newCoord.x - 1, newCoord.y -1,value);
						centralStation.updateCoords(newCoord.x, newCoord.y -1, value);
						centralStation.updateCoords(newCoord.x +1, newCoord.y -1, value);
					}
					else if (measurements[1] < 0.4){
						centralStation.updateCoords(newCoord.x -1, newCoord.y +1, value);
						centralStation.updateCoords(newCoord.x, newCoord.y +1, value);
						centralStation.updateCoords(newCoord.x +1, newCoord.y +1, value);
					} 
					else if (measurements[2] < 0.4)
						centralStation.updateCoords(newCoord.x -1, newCoord.y, value);
				}
				if(angle > 225 && angle < 315){
					if (measurements[0] < 0.4){
						centralStation.updateCoords(newCoord.x -1, newCoord.y +1, value);
						centralStation.updateCoords(newCoord.x, newCoord.y +1, value);
						centralStation.updateCoords(newCoord.x +1, newCoord.y +1, value);		
					}
					else if (measurements[1] < 0.4){
						centralStation.updateCoords(newCoord.x -1, newCoord.y -1,value);
						centralStation.updateCoords(newCoord.x, newCoord.y -1, value);
						centralStation.updateCoords(newCoord.x +1, newCoord.y -1, value);	
					} 
					else if (measurements[2] < 0.4)
						centralStation.updateCoords(newCoord.x +1, newCoord.y, value);
				}
				if((angle < 45 && angle >= 0) || (angle <= 360 && angle > 315)){
					if (measurements[0] < 0.4){
						centralStation.updateCoords(newCoord.x-1, newCoord.y, value);
						centralStation.updateCoords(newCoord.x-1, newCoord.y-1, value);
					}
					else if (measurements[1] < 0.4){
						centralStation.updateCoords(newCoord.x+1, newCoord.y-1, value);
						centralStation.updateCoords(newCoord.x+1, newCoord.y, value);
					} 
					else if (measurements[2] < 0.4)
						centralStation.updateCoords(newCoord.x, newCoord.y +1, value);
				}
				if(angle > 135 && angle < 225){
					if (measurements[0] < 0.4){
						centralStation.updateCoords(newCoord.x+1, newCoord.y, value);
						centralStation.updateCoords(newCoord.x+1, newCoord.y+1, value);
					}
					else if (measurements[1] < 0.4){
						centralStation.updateCoords(newCoord.x-1, newCoord.y, value);
						centralStation.updateCoords(newCoord.x-1, newCoord.y+1, value);
					} 
					else if (measurements[2] < 0.4)
						centralStation.updateCoords(newCoord.x, newCoord.y -1, value);
				}
			}
        }
    }
}