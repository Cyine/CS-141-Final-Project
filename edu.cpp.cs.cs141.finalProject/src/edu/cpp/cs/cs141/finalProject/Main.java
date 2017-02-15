package edu.cpp.cs.cs141.finalProject;
public class Main {

	public static void main(String[] args) 
	{
		/** list of things not implemented yet
		 * any UI, any gameEngine
		 * using any powerUp
		 * any movement (player or ninjas)
		 * any saving
		 * shooting
		 */
		//Make a new gamegrid
		GameGrid g = new GameGrid();
		//set debug mode to true (default is false)
		g.toggleDebugMode();
		//randomize ninjas
		g.randomizeNinjas();
		//print the grid
		g.printGrid();

	}

}
