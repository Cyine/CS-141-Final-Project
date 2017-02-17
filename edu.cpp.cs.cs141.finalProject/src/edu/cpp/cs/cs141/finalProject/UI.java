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
		GameEngine.g.printGrid();
		
	}

}
