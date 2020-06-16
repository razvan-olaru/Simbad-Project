package main.java.softdesign;
import javax.swing.JOptionPane;

/*
The CentralStation class provides a 46x46 matrix which represents the environment.
It receives information gathered from the rovers during their mission and it updates
the matrix with the received value. In this way, it maps the environment with the following:
-free spaces
-obstacles
-goal position
However, if the rovers did not end their mission but 60% of the environment has been explored,
the mission will be terminated.
*/
public class CentralStation {

    private static CentralStation single_instance = null; // Attribute for Singleton class
    private static final int ENV_SIZE = 46; // Size of matrix
    private static final float SEARCH_GOAL = (float) 0.6; // Exploring a maximum of 60% of the environment
    private static int ROBOTS = 4; // Number of rovers in the environment
    public int[][] matrix; // Database

    // Constructor
    private CentralStation(){
        matrix = new int[ENV_SIZE][ENV_SIZE];
    }

    // Singleton structure 
    public static CentralStation getInstance() 
    { 
        if (single_instance == null) 
            single_instance = new CentralStation(); 
        return single_instance;
    } 

    // // Print matrix at run-time
	// private void printMatrix() {
	// 	for (int i = 0; i < ENV_SIZE; i++) {
	// 		System.out.print("[ ");
	// 		for (int j = 0; j < ENV_SIZE; j++) {
	// 			System.out.print(matrix[i][j]);
	// 			System.out.print(" ");
	// 		}
	// 		System.out.println(" ]");
    //     }
    //     System.out.println();
	// }
    
    // Update database with the newly learned information
    public void updateCoords(int x, int y, int value) {
        if (matrix[x][y] < 2)
            matrix[x][y] = value;
    }

    // Check whether 60% of the environment has been explored
    public boolean shouldFinish() {
        int visited_count = 0;
        for (int i = 0; i < ENV_SIZE; i++) {
			for (int j = 0; j < ENV_SIZE; j++) {
                if(matrix[i][j] != 0)
                    visited_count++;
			}
        }
        return (((float) visited_count) / ((float) (ENV_SIZE * ENV_SIZE))) > SEARCH_GOAL;
    }

    /* Check if the mission has been completed - when all 4 rovers have reached the goal.
    If mission completed, announce the user */
    public void completeMission() {
        ROBOTS--;
        if (ROBOTS == 0) {
            // printMatrix();
            JOptionPane.showMessageDialog(null, "Mission Complete!","Message", JOptionPane.INFORMATION_MESSAGE);
            System.out.println("Success");
        }
    }

    // Returns termination of mission
    public boolean getMissionComplete() {
        return ROBOTS == 0;
    }

    // Returns termination of mission
    public void stopMission() {
        ROBOTS = 0;
    }
}