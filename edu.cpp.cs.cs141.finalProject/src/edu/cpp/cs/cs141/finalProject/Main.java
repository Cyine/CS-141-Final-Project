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
		GameEngine game = new GameEngine();
		UI ui = new UI(game);
		
		ui.openTitleScreen();

	}

}
