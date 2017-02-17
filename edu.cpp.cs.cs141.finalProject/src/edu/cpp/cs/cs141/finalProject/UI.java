package edu.cpp.cs.cs141.finalProject;

public class UI {

	public UI() {
		// TODO Auto-generated constructor stub
	}

	public static void start() {
		//set debug mode to true (default is false)
		GameEngine.g.toggleDebugMode();
		//randomize ninjas
		GameEngine.g.randomizeNinjas();
		//print the grid
		gameLoop();
		
	}
	
	private static void gameLoop() {
		// TODO Auto-generated method stub
		printGrid();
	}

	/**-------------------------------------------
	 * PRINT METHODS
	 * -------------------------------------------
	 */
	
	/**
	 * Prints grid in 9x9 fashion 
	 */
	public static void printGrid()
	{
		for (int i=0; i<GameGrid.getGrid().length;++i)
		{
			System.out.println("");
			
			for(int j=0; j<GameGrid.getGrid().length; ++j)
			{
				System.out.print(GameGrid.getGrid()[i][j].returnString(GameGrid.getDebugMode()));
			}
		}
	}
	

}
