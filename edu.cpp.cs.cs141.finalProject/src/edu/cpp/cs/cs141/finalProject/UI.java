package edu.cpp.cs.cs141.finalProject;
import java.util.Scanner; 

public class UI 
{
	private GameEngine gE;
	private Scanner scanner;
	private char charInput;
	private int intInput;
	private int runGameChoice;
	private boolean playerQuit;
	
	/**
	 * @param gameEngine
	 * Takes a GameEngine makes that gE
	 * instantiates scanner
	 */
	public UI(GameEngine gameEngine) 
	{
		gE=gameEngine;
		scanner = new Scanner(System.in);
	}

	/**
	 * Prints title Screen Info
	 * asks user what they'd like to do
	 * runs that piece of code
	 */
	public void openTitleScreen()
	{
		do
		{		
			printTitleScreen();
			runGameChoice=getIntInput();
				
			if (runGameChoice==1)
			{
				System.out.println("1: New Game\n2: Load Game");
				if(getIntInput()==1)
				{
					playerQuit=false;
					startNewGame();
				}
				else
				{
					System.out.println("Enter file to load: ");
					loadGame(getStringInput());
				}
			}
			else if(runGameChoice==2)
			{
				printRules();
			}
		}while(runGameChoice!=3);
		System.out.println("Goodbye");
	}
	
	/**
	 * tells gameEngine to reset itself
	 * starts UI game loop
	 */
	public void startNewGame() 
	{
		gE.setupNewGame();
		startGameLoop();
	}
	
	/**
	 * @param fileName
	 * supposed to load a saved game
	 */
	public void loadGame(String fileName)
	{
		//IMPLEMENT THIS CODE
	}
	
	/**
	 * @param fileName
	 * supposed to save Game
	 */
	public void saveGame(String fileName)
	{
		//IMPLEMENT THIS CODE
	}
	
	/**
	 * Asks user which direction to look
	 */
	public void userLook()
	{
		printGrid();  //Print grid
		System.out.println("");   
		boolean runLoop=true;
		do
		{
			System.out.println("\n\nInput direction to look (WASD) or Q to quit"); 
			charInput=getCharInput();
			if(charInput!='Q')
			{
				gE.turnLightsOn(charInput); //turns lights on in direction asked
				runLoop=false;
			}
			else if(charInput=='Q')
			{
				playerQuit=true;
				quit();
				runLoop=false;
			}
			else
			{
				System.out.println("Invalid Input");
			}
		}while(runLoop);
	}
	
	/**
	 * Asks if the player wants to move or shoot then in what direction
	 */
	public void userMoveOrShoot()
	{
		printGrid(); //print grid with vision
		
		System.out.println("\n1: Move\n2: Shoot\n3: Quit");
		boolean runLoop=true;
		
		do
		{
			intInput=getIntInput();
			if(intInput==1)   //if 1 move if other input shoot
			{
				System.out.println("\nInput direction to go (WASD)");
				printEvent(gE.movePlayer(getCharInputNoQ()));
				runLoop=false;
			}
			else if(intInput ==2)
			{
				System.out.println("\nInput direction to shoot(WASD)");
				printEvent(gE.shoot(getCharInputNoQ()));
				printEvent(gE.checkForDeath()); //check if player is dead
				runLoop=false;
			}
			else
			{
				System.out.println("Invalid Input");
			}
		}while(runLoop==true);
	}
	
	/**
	 * The basic game loop,
	 * Player looks
	 * Player Moves/Shoots
	 * Ninjas move
	 * Repeat till game is over or player quits
	 */
	private void startGameLoop() 
	{
		askToggleDebugMode();
		do
		{
			if(!playerQuit)
				userLook();
			if(!playerQuit)
			{
				userMoveOrShoot();
				gE.moveNinjas();  //move the ninjas
				printPlayerInfo(); // print player info
			}
		}while(gE.getIsGameRunning()==true&&!playerQuit); //run again if the game is still running
		if(!playerQuit)
			printEndingScreen(); // prints whether user lost or won
		System.out.println("");
	}
	
	
	/**
	 * asks user if they'd like to turn debug mode on
	 */
	public void askToggleDebugMode()
	{
		System.out.println("Turn on debug mode?\n1: Yes\n2: No");
		if(getIntInput()==1)
		{
			gE.getGameGrid().toggleDebugMode();
		}
	}

	/**
	 * Quit mid game, asks if user wants to save, if so save
	 */
	public void quit()
	{
		System.out.println("Save?\n1: Yes\n2: No");
		if(getIntInput()==1)
		{
			System.out.println("Enter file name to save as ");
			saveGame(getStringInput());
		}
		playerQuit=true;
	}
	
	/**-------------------------------------------
	 * PRINT METHODS
	 * -------------------------------------------
	 */
	
	/**
	 * prints title screen info
	 */
	public void printTitleScreen()
	{
		System.out.println("Ninjas and Briefcases");
		System.out.println("game by Non-Artifical Intelgence");
		System.out.println("\nEnter 1 to start game.");
		System.out.println("Enter 2 to print rules.");
		System.out.println("Enter 3 to exit.");
	}
	
	/**
	 * @param eventType
	 * prints out a different event depending on eventType
	 */
	public void printEvent(int eventType)
	{
		/**Legend
		 *  0 = nothing
		 *  1 = Win
		 *  2 = Killed Ninja
		 *  3 = Picked Up Powerup
		 * -1 = Failed Move
		 * -3 = Death
		 * -4 = Failed Hit
		 * -5 = no ammo
		 * -6 = no briefcase
		 * -7 = overshield saved
		 */
		if(eventType==0)
		{
			//System.out.println("Nothing out of the oridnary happened.");
		}
		else if(eventType==1)
		{
			System.out.println("You found the briefcase.");
		}
		else if(eventType==2)
		{
			System.out.println("You shot a ninja");
		}
		else if(eventType==3)
		{
			System.out.println("You picked something up.");
		}
		else if(eventType==-1)
		{
			System.out.println("You bumped into something.");
		}
		else if(eventType==-3)
		{
			System.out.println("You were stabbed.");
		}
		else if(eventType==-4)
		{
			System.out.println("You hear the bullet hit a wall.");
		}
		else if(eventType==-5)
		{
			System.out.println("Click... you have no ammo.");
		}
		else if(eventType==-6)
		{
			System.out.println("You shuffle around for a briefcase but can't seem to find one.");
		}
		else if(eventType==-7)
		{
			System.out.println("You were stabbed but your overshield saved you.");
		}
	}
	
	/**
	 * Prints player info in this format
	 * Lives:
	 * Ammo:
	 * Radar:
	 * Overshield Charge:
	 */
	public void printPlayerInfo()
	{
		System.out.println("Lives: "+gE.getGameGrid().getPlayer().getLives());
		System.out.println("Ammo: "+gE.getGameGrid().getPlayer().getHasAmmo());
		System.out.println("Radar: "+gE.getGameGrid().getPlayer().getHasRadar());
		System.out.println("Overshield Charge: "+gE.getGameGrid().getPlayer().getOvershieldCharge());
	}
	
	/**
	 * Prints all the rules
	 */
	public void printRules()
	{
		System.out.println("Rules: Find the briefcase.\nEach turn you can look 2 spaces in 1 direction"
				+ "\nAfter you look you can move or shoot.\nThere are 6 ninjas. Avoid the ninjas otherwise you die."
				+ "\nThe briefcase is in one of the rooms (labled R).\nYou have 1 bullet and 3 lives"
				+ "\nEverytime you die you restart at the bottom left. Lose all your lives and you lose."
				+ "\nYou can only access the rooms from the top."
				+ "\nThere are 3 powerups, bullet, gives you a bullet if you already used one"
				+ "\nShield, it lasts 5 turns and prevents you from losing a life when stabbed"
				+ "\nRadar, shows you where the briefcase is located."
				+ "\nHave fun!\n\n");
	}
	
	/**
	 * prints whether user lost or won
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
	
	/**
	 * Prints grid in 9x9 fashion 
	 */
	public void printGrid()
	{
		for (int i=0; i<gE.getGameGrid().getGrid().length;++i)
		{
			System.out.println("");
			
			for(int j=0; j<gE.getGameGrid().getGrid().length; ++j)
			{
				System.out.print(gE.getGameGrid().getGrid()[i][j].returnString(gE.getGameGrid().getDebugMode(),gE.getGameGrid().getPlayer().getHasRadar()));
			}
		}
	}
	
	/**----------------------------------------------------------
	 * USER INPUT
	 * ----------------------------------------------------------
	 */
	
	/**
	 * @return char
	 * 
	 * gets a char input from user only W A S D or Q
	 */
	public char getCharInput()
	{
		boolean isLoopOver=false;
		char userInput = '0';
		while(isLoopOver==false) 
		{	
				userInput = scanner.next().toUpperCase().charAt(0);
				
				if (!(userInput=='W'||userInput=='A'||userInput=='S'||userInput=='D'||userInput=='Q'))
				{
					System.out.println("Not a valid input");
				}
				else
				{
					isLoopOver=true;
				}
		}
		return userInput;
	}
	
	/**
	 * @return char
	 * returns user input only W A S or D
	 */
	public char getCharInputNoQ()
	{
		boolean isLoopOver=false;
		char userInput = '0';
		while(isLoopOver==false) 
		{	
				userInput = scanner.next().toUpperCase().charAt(0);
				
				if (!(userInput=='W'||userInput=='A'||userInput=='S'||userInput=='D'))
				{
					System.out.println("Not a valid input");
				}
				else
				{
					isLoopOver=true;
				}
		}
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
	
	/**
	 * @return string
	 * returns string input of user
	 * (only used for file name)
	 */
	private String getStringInput()
	{
		   return(scanner.next());
	}

}
