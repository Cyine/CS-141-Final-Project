package edu.cpp.cs.cs141.finalProject;
import java.util.Scanner; 

public class UI 
{
	private GameEngine gE;
	private Scanner scanner;
	private char charInput;
	
	public UI(GameEngine gameEngine) 
	{
		gE=gameEngine;
		scanner = new Scanner(System.in);
	}

	public void start() 
	{
		
		gE.setupNewGame();
		printGrid();
		System.out.println("");
		//print the grid
		startGameLoop();
		
	}
	
	private void startGameLoop() 
	{
		do
		{
			printGrid();
			System.out.println("");
			System.out.println("\n\nInput lights to turn on (WASD)");
			gE.turnLightsOn(getCharInput());
			printGrid();
			System.out.println("\n1: Move\n2: Shoot");
			if(getIntInput()==1)
			{
				System.out.println("\nInput direction to go (WASD)");
				gE.movePlayer(getCharInput());
			}
			else
			{
				System.out.println("\nInput direction to shoot(WASD)D");
				gE.shoot(getCharInput());
			}
			
			
		}while(gE.getIsGameRunning()==true);
		printEndingScreen();
	}

	/**-------------------------------------------
	 * PRINT METHODS
	 * -------------------------------------------
	 */
	
	/**
	 * Prints grid in 9x9 fashion 
	 */
	
	public void printEndingScreen()
	{
		if(gE.getIsGameWon()==true)
		{
			System.out.println("You win!");
		}
		else
		{
			System.out.println("You lost");
		}
	}
	public void printGrid()
	{
		for (int i=0; i<gE.getGameGrid().getGrid().length;++i)
		{
			System.out.println("");
			
			for(int j=0; j<gE.getGameGrid().getGrid().length; ++j)
			{
				System.out.print(gE.getGameGrid().getGrid()[i][j].returnString(gE.getGameGrid().getDebugMode()));
			}
		}
	}
	
	/**----------------------------------------------------------
	 * USER INPUT
	 * ----------------------------------------------------------
	 */
	public char getCharInput()
	{
		boolean isLoopOver=false;
		char userInput = '0';
		while(isLoopOver==false) 
		{	
				userInput = scanner.next().charAt(0);
				
				if (!(userInput=='W'||userInput=='A'||userInput=='S'||userInput=='D'))
				{
					System.out.println("Not a valid input");
				}
				else
				{
					isLoopOver=true;
				}
		}
		System.out.println(userInput);
		return userInput;
	}
	
	/**
	 * @return
	 * returns the user input if it is a integer
	 * if not integer asks for input again
	 */
	public int getIntInput()
	{
		boolean isLoopOver=false;
		int userInput =0;
		while(isLoopOver==false) 
		{	
			if(!scanner.hasNextInt()) 
			{
				scanner.next();
				System.out.println("ERROR, not a valid input.");
			}
			else
			{
				userInput=scanner.nextInt();
				if (userInput>4||userInput<1)
				{
					System.out.println("Not a valid number");
				}
				else
				{
					isLoopOver=true;
				}
			}
		}
		return userInput;
	}
	
	/**------------------------------------
	 * PAUSE TILL ENTER PRESSED FUNCTIONS
	 * ------------------------------------
	 */
	
	/**
	 * Stop the code till next line is entered Tell user to press enter
	 */
	private void promptEnterKey()
	{
		   System.out.println("Press ENTER to continue...\n");
		   scanner.nextLine();
	}

	/**
	 * psudeo way to clear buffer to prevent 
	 * game from skipping a "promtEnterKey" 
	 */
	private void clearNextLine()
	{
		scanner.nextLine();
	}

}
