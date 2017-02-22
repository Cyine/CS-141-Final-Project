package edu.cpp.cs.cs141.finalProject;

public class GameGrid 
{
	/**
	 * player to put on grid
	 * ninjas is an array of ninjas
	 * grid is a 2d grid of spaces 
	 */
	private Player player;
	private Ninja[] ninjas;
	private static boolean isDebugModeOn;
	private static Space[][] grid;

	/**
	 * sets grid to be a 9x9 array
	 * defineGrid creates a new space for each spot in grid
	 * creates new player
	 * sets player to bottom left
	 * sets all powerups
	 * sets nijas to an array of ninjas of 6
	 * sets debug mode to false
	 */
	public GameGrid ()
	{
		setGrid(new Space[9][9]);
		defineGrid();
		player = new Player();
		setPlayerPos(8,0);
		setAllPowerUps();
		ninjas = new Ninja[6];
		defineNinjas();
		isDebugModeOn=false;
	}
	
	/**-----------------------------------------------
	 * GET METHODS
	 * -----------------------------------------------
	 */
	
	/**
	 * @return player
	 */
	public Player getPlayer()
	{
		return player;
	}
	
	/**
	 * @return ninjas
	 */
	public Ninja[] getNinjas()
	{
		return ninjas;
	}
	/**
	 * @return isDebugModeOn
	 */
	public boolean getDebugMode()
	{
		return isDebugModeOn;
	}
	
	/**
	 * @return grid
	 */
	public Space[][] getGrid() 
	{
		return grid;
	}
	
	/**--------------------------------------------------
	 * MUTATOR METHODS
	 * --------------------------------------------------
	 */
	
	//INITILIZING METHODS-------------------------------------
	/**
	 * initilizes the array of ninjas
	 * then randomizes them
	 */
	public void defineNinjas()
	{
		for (int i=0; i<ninjas.length;++i)
		{
				ninjas[i]=new Ninja(0,0);
		}
		randomizeNinjas();
	}
	
	/**
	 * makes every part of the grid an space except for 9 rooms
	 * then sets briefcase
	 */
	public void defineGrid()
	{
		for (int i=0; i<getGrid().length;++i) 
		{
			for(int j=0; j<getGrid()[0].length; ++j) //double for loop
			{
				grid[i][j]= new Space(i, j); //set space to space with coordinates i, j
				
				if ((i+1)%3==0 && (j+1)%3==0)
				{
					grid[i-1][j-1].toggleIsRoom(); //make spot a room
				}
			}
		}
		setBriefcase(); // set the briefcase to a random room
	}
	
	/**
	 * calls setPowerUp for each powerup
	 */
	public void setAllPowerUps()
	{
		setPowerUp("shield");
		setPowerUp("radar");
		setPowerUp("bullet");
	}
	
	/**
	 * @param powerUp
	 * takes a random number that represents a spot on the grid.
	 * If the spot on the grid does not have a player, does not have a breifcase
	 * and does not already have a powerUp, set the power up there. 
	 * continues to choose a random spot until the power up is set
	 * 
	 * pass the power up to place on grid (See setAllPowerUps)
	 */
	public void setPowerUp(String powerUp) 
	{
		int ranNum;         //create an int 
		boolean done=false; // create a boolean for the do/while loop
		do                  //do/while loop, do once but continue while done==false
		{ 
			//the %9 operator gives the
			ranNum=(int)(Math.random()*81); //creates a random int between 0-80 (1 for each spot on grid)
			if(getGrid()[(int)(ranNum/9)][ranNum%9].getPowerup()==null   //makes sure there is no power up on spot
				&& getGrid()[(int)(ranNum/9)][ranNum%9].getIsRoom()==false //makes sure the spot does not have the briefcase on it
				&&ranNum!=72) //the spot is not the bottom left (player starting location)
			{
				getGrid()[(int)(ranNum/9)][(int)(ranNum%9)].setPowerup(powerUp); //set the powerUp to that location
				done=true;                          //set done to true to end loop
			}
		}while (done==false); //rest of do/while loop
		
	}
		
	/**
	 * chooses a random number between 1-9
	 * for each number there is a room attached (case)
	 * sets the briefcase to that room.
	 */
	public void setBriefcase()
	{
		int ranNum=(int)(Math.random()*9)+1; //choose a random number between 1-9
		switch(ranNum)
		{
		case 1:
			getGrid()[1][1].toggleIsBriefcaseOccupying();
			break;
		case 2:
			getGrid()[1][4].toggleIsBriefcaseOccupying();
			break;
		case 3:
			getGrid()[1][7].toggleIsBriefcaseOccupying();
			break;
		case 4:
			getGrid()[4][1].toggleIsBriefcaseOccupying();
			break;
		case 5:
			getGrid()[4][4].toggleIsBriefcaseOccupying();
			break;
		case 6:
			getGrid()[4][7].toggleIsBriefcaseOccupying();
			break;
		case 7:
			getGrid()[7][1].toggleIsBriefcaseOccupying();
			break;
		case 8:
			getGrid()[7][4].toggleIsBriefcaseOccupying();
			break;
		case 9:
			getGrid()[7][7].toggleIsBriefcaseOccupying();
			break;
		}
		
	}

	/**
	 * @param theGrid
	 * set grid to grid passed
	 */
	public void setGrid(Space[][] theGrid) 
	{
		grid = theGrid;
	}

	//TOGGLE FUCNTIONS------------------------------------------------
	/**
	 * sets debug mode to its opposite value
	 */
	public void toggleDebugMode()
	{
		isDebugModeOn= !isDebugModeOn;
	}
	
	/**
	 * @param direction
	 * Takes direction W for up S for down A for left and D for right
	 * 
	 * Shows 2 rooms respective direction as long as it is not a ninja, edge or room
	 * Vision will stop if it runs into a room but will not stop because of a ninja
	 */
	public void toggleLightsOn(char direction)
	{
		if (direction == 'W')
		{
			if(player.getYPos()>0) // if it is not the edge
				grid[player.getYPos()-1][player.getXPos()].toggleIsVisible();
			if(player.getYPos()>1&&!grid[player.getYPos()-1][player.getXPos()].getIsRoom())// if the previous one is room and current space is not off grid
			grid[player.getYPos()-2][player.getXPos()].toggleIsVisible(); //toggle lights in that room
		}
		else if (direction == 'A')
		{
			if(player.getXPos()>0)
				grid[player.getYPos()][player.getXPos()-1].toggleIsVisible();
			if(player.getXPos()>1&&!grid[player.getYPos()][player.getXPos()-1].getIsRoom())
				grid[player.getYPos()][player.getXPos()-2].toggleIsVisible();
		}
		else if (direction == 'S'&&player.getYPos()!=8)
		{
			if(player.getYPos()<8)
				grid[player.getYPos()+1][player.getXPos()].toggleIsVisible();
			if(player.getYPos()<7&&!grid[player.getYPos()+1][player.getXPos()].getIsRoom())
				grid[player.getYPos()+2][player.getXPos()].toggleIsVisible();
		}
		else if(direction == 'D'&&player.getXPos()!=8)
		{
			if(player.getXPos()<8)
				grid[player.getYPos()][player.getXPos()+1].toggleIsVisible();
			if(player.getXPos()<7&&!grid[player.getYPos()][player.getXPos()+1].getIsRoom())
				grid[player.getYPos()][player.getXPos()+2].toggleIsVisible();
		}
		
	}
	
	//MOVE FUNCTIONS----------------------------------------------------
	/**
	 * @param direction
	 * Takes direction W for up S for down A for left and D for right
	 * 
	 * moves player respective direction as long as it is not a ninja, edge or room
	 * Does this by changing player position and changing the spaces that have isPlayerOccupying==true
	 * 
	 * Only exception is S, if S is inputed checks if it is a room, if it is a room checks room for briefcase
	 */
	public int movePlayer(char direction)
	{ 
		//if direction is W and 1 up is not off the grid and if one up is not a room and if one up does not have a ninja
		if (direction == 'W'&&player.getYPos()!=0&&!grid[player.getYPos()-1][player.getXPos()].getIsRoom()
				&&!grid[player.getYPos()-1][player.getXPos()].getIsNinjaOccupying())
		{
			grid[player.getYPos()][player.getXPos()].toggleIsPlayerOccupying(); //toggle isPlayerOccupying in current space
			player.changeYPos(-1);                 // move player up one
			grid[player.getYPos()][player.getXPos()].toggleIsPlayerOccupying(); // toggle isPlayerOccupying in new position
			return 0; //return 0
		}
		else if (direction == 'A'&&player.getXPos()!=0&&!grid[player.getYPos()][player.getXPos()-1].getIsRoom()
				&&!grid[player.getYPos()][player.getXPos()-1].getIsNinjaOccupying())
		{
			grid[player.getYPos()][player.getXPos()].toggleIsPlayerOccupying();
			player.changeXPos(-1);
			grid[player.getYPos()][player.getXPos()].toggleIsPlayerOccupying();
			return 0;
		}
		else if (direction == 'S'&&player.getYPos()!=8)
		{	
			if(!grid[player.getYPos()+1][player.getXPos()].getIsRoom() //if is not edge of grid, is not room and is not ninja occupying move to that space
					&&!grid[player.getYPos()+1][player.getXPos()].getIsNinjaOccupying())
			{
				grid[player.getYPos()][player.getXPos()].toggleIsPlayerOccupying();
				player.changeYPos(1);
				grid[player.getYPos()][player.getXPos()].toggleIsPlayerOccupying();
				return 0;
			}
			else if(grid[player.getYPos()+1][player.getXPos()].getIsRoom()) //if above code failed check if it is a room
			{
				if(grid[player.getYPos()+1][player.getXPos()].getIsBriefcaseOccupying()) //if it is a room check for briefcase
				{
					return 1; //eventType briefcase was found
				}
				return -6; //eventType saying no briefcase was found
			}
		}
		else if(direction == 'D'&&player.getXPos()!=8&&!grid[player.getYPos()][player.getXPos()+1].getIsRoom())
		{
				grid[player.getYPos()][player.getXPos()].toggleIsPlayerOccupying();
				player.changeXPos(1);
				grid[player.getYPos()][player.getXPos()].toggleIsPlayerOccupying();
				return 0;
		}
		
		return -1;
	}
	
	/**
	 * @param ninja
	 * @return 
	 * 
	 * chooses a random number between 0-3
	 * 0 is Up 
	 * 1 is Down 
	 * 2 is Right
	 * 3 is Left
	 * 
	 * before returning random int checks if the direction associated with that int is safe to move into
	 * checks made are not the edge of the grid, is not a room, the player is not occupying it and another ninja is not 
	 * occupying it
	 */
	public int getNinjaDirection(Ninja ninja)
	{
		int ranNum = ((int)(Math.random()*4));//random number between 0-3
		
		if(ranNum==0&&ninja.getYPos()!=0&& //if ranNum ==0
				!grid[ninja.getYPos()-1][ninja.getXPos()].getIsPlayerOccupying()&& //there is no player occupying the space above
				!grid[ninja.getYPos()-1][ninja.getXPos()].getIsNinjaOccupying()&& // there is no ninja occupying the space above
				!grid[ninja.getYPos()-1][ninja.getXPos()].getIsRoom()) // the space above is not a room
		{
			return 0; //if successful return 0 (0 translates as up in moveNinjas)
		}
		else if(ranNum==1&&ninja.getXPos()!=0&&
				!grid[ninja.getYPos()][ninja.getXPos()-1].getIsPlayerOccupying()&&
				!grid[ninja.getYPos()][ninja.getXPos()-1].getIsNinjaOccupying()&&
				!grid[ninja.getYPos()][ninja.getXPos()-1].getIsRoom())
		{
			return 1; //1 means left
		}
		else if(ranNum==2&&ninja.getYPos()!=8&&
				!grid[ninja.getYPos()+1][ninja.getXPos()].getIsPlayerOccupying()&&
				!grid[ninja.getYPos()+1][ninja.getXPos()].getIsNinjaOccupying()&&
				!grid[ninja.getYPos()+1][ninja.getXPos()].getIsRoom())
		{
			return 2; // 2 means down
		}
		else if(ranNum==3&&ninja.getXPos()!=8&&
				!grid[ninja.getYPos()][ninja.getXPos()+1].getIsPlayerOccupying()&&
				!grid[ninja.getYPos()][ninja.getXPos()+1].getIsNinjaOccupying()&&
				!grid[ninja.getYPos()][ninja.getXPos()+1].getIsRoom())
		{
			return 3; // 3 means right
		}
		return -1; //-1 means try again
		
	}

	/**
	 * set noMoveCounter to 0
	 * 
	 * makes a for loop with i=0; and runs until !(i<ninjas.length)
	 * if the ninja is alive run the randomize code, if not just implement ++i; to skip that ninja
	 * 
	 * randomize code chooses a randomNum chosen by getNinjaDirection()
	 * moves ninja in that direction
	 * 
	 * if the move is invalid add 1 to noMoveCounter
	 * if noMoveCounter gets to 30 skip that ninja 
	 * this is done to prevent infinite loop of code if a ninja is trapped by other ninjas
	 * 
	 * 
	 */
	public void moveNinjas()
	{
		int ranNum;
		int noMoveCounter=0;
		
		for(int i=0; i<ninjas.length;) //for each ninja
		{
			if(ninjas[i].getIsAlive()) // if the ninja is alive
			{
			ranNum = getNinjaDirection(ninjas[i]); //get a safe random number 0-3 
			if (ranNum == 0) //move ninja up
			{
				//toggle isNinja in current space, move ninja, toggle isNinja again
					grid[ninjas[i].getYPos()][ninjas[i].getXPos()].toggleIsNinjaOccupying();
					ninjas[i].changeYPos(-1);
					grid[ninjas[i].getYPos()][ninjas[i].getXPos()].toggleIsNinjaOccupying();
					++i;
				}
				else if (ranNum == 1) //move ninja left
				{
					grid[ninjas[i].getYPos()][ninjas[i].getXPos()].toggleIsNinjaOccupying();
					ninjas[i].changeXPos(-1);
					grid[ninjas[i].getYPos()][ninjas[i].getXPos()].toggleIsNinjaOccupying();
					++i;
				}
				else if (ranNum == 2) //move ninja down
				{
					grid[ninjas[i].getYPos()][ninjas[i].getXPos()].toggleIsNinjaOccupying();
					ninjas[i].changeYPos(1);
					grid[ninjas[i].getYPos()][ninjas[i].getXPos()].toggleIsNinjaOccupying();
					++i;
				}
				else if(ranNum == 3) // move ninja right
				{
					grid[ninjas[i].getYPos()][ninjas[i].getXPos()].toggleIsNinjaOccupying();
					ninjas[i].changeXPos(1);
					grid[ninjas[i].getYPos()][ninjas[i].getXPos()].toggleIsNinjaOccupying();
					++i;
				}
			
			}
			else //if ninja is dead
			{
				++i; //skip ninja 
			}
			++noMoveCounter; //add 1 to no moveCounter
			if(noMoveCounter>30) //if loop executed over 30 times for same ninja
			{
				++i; //skip ninja
			}
		}
	}

	//SET COORDINATE FUNCTIONS-------------------------------------------
	/**
	 * @param x
	 * @param y
	 * 
	 * set player to coordinates given
	 */
	public void setPlayerPos(int y, int x)
	{
		player.setPlayerPos(y,x);
		getGrid()[y][x].toggleIsPlayerOccupying();
	}
	
	/**
	 * @param y
	 * @param x
	 * @return ninja
	 * given an y,x coordinate returns a ninja at that coordinate (or null)
	 */
	public Ninja selectNinja (int y, int x)
	{
		for (int i=0; i<ninjas.length;++i)
		{
			if(ninjas[i].getYPos()==y&&ninjas[i].getXPos()==x) //if the ninja has the same y,x coordinates
			{
				return ninjas[i]; //return the ninja
			}
		}
		return null;
	}
	
	//OTHER-------------------------------------------------------------
	
	/**
	 * @return
	 * if there is a ninja above, below, to the right, or to the left of the player run die()
	 * return what die gives
	 * if there was not a ninja near the player return 0
	 */
	public int checkForDeath()
	{
		//if a ninja is above, below, to the left or the right of the player
		if((player.getYPos()!=8&&grid[player.getYPos()+1][player.getXPos()].getIsNinjaOccupying())||
		   (player.getYPos()!=0&&grid[player.getYPos()-1][player.getXPos()].getIsNinjaOccupying())||
		   (player.getXPos()!=8&&grid[player.getYPos()][player.getXPos()+1].getIsNinjaOccupying())||
		   (player.getXPos()!=0&&grid[player.getYPos()][player.getXPos()-1].getIsNinjaOccupying()))
		{
			return (die()); //run die
		}
		return 0; // if nothing happens return 0 (eventType meaning nothing)
	}
	
	/**
	* @param direction
	 * @return eventType
	 * 
	 * shoots in given direction, bullet kills ninja if hits ninja
	 * bullet stops after hitting one ninja or hitting room
	 * 
	 * return -4 if wall hit, 2 if ninja was hit
	 */
	public int shoot (char direction)
	{
		player.shoot(); //empty player gun
		if(direction=='W')
		{
			for (int i = player.getYPos()-1; i>0; --i)//start bullet above player, moves hits edge
			{
				if(grid[i][player.getXPos()].getIsRoom())// if the bullet hits a room
				{
					i=0; //terminate for loop
					return -4; //return -4 (eventType missed hit)
				}
				
				if(grid[i][player.getXPos()].getIsNinjaOccupying()) //if ninja is hit by bullet
				{
					grid[i][player.getXPos()].toggleIsNinjaOccupying(); //toggleNinja from that space
					selectNinja(i, player.getXPos()).kill();  //call kill() for that ninja
					i=0; //terminate for loop (stops bullet from continuing) 
					return 2; //return 2 (eventType meaning ninja died)
				}
			}
			return -4; //if did not hit room or ninja return -4 (eventType missed hit)
		}
		else if(direction=='A')
		{
			for (int i = player.getXPos()-1; i>0; --i)
			{
				if(grid[player.getYPos()][i].getIsRoom())
				{
					i=0;
					return -4;
				}
				
				if(grid[player.getYPos()][i].getIsNinjaOccupying())
				{
					grid[player.getYPos()][i].toggleIsNinjaOccupying();
					selectNinja(player.getYPos(), i).kill();
					i=0;
					return 2;
				}
			}
			return -4;
		}
		else if(direction=='S')
		{
			for (int i = player.getYPos()+1; i<8; ++i)
			{
				if(grid[i][player.getXPos()].getIsRoom())
				{
					i=8;
					return -4;
				}
				
				if(grid[i][player.getXPos()].getIsNinjaOccupying())
				{
					grid[i][player.getXPos()].toggleIsNinjaOccupying();
					selectNinja(i, player.getXPos()).kill();
					i=8;
					return 2;
				}
			}
			return -4;
		}
		else if(direction=='D')
		{
			for (int i = player.getXPos()+1; i<8; ++i)
			{
				if(grid[player.getYPos()][i].getIsRoom())
				{
					i=8;
					return -4;
				}
				
				
				if(grid[player.getYPos()][i].getIsNinjaOccupying())
				{
					grid[player.getYPos()][i].toggleIsNinjaOccupying();
					selectNinja(player.getYPos(), i).kill();
					i=8;
					return 2;
				}
			}
			return -4;
		}
		return 0;
	}

	/**
	 * @return eventType
	 * runs player.die() and sets eventType equal to that
	 * 
	 * if the player died (eventType== -3)
	 * then remove ninjas from grid and re randomize them
	 * 
	 * then return eventType
	 */
	public int die()
	{
		int eventType = player.die(); //sets eventType to player.die() (this calls the function player.die())
		if (eventType==-3) //if the eventType is -3 (Meaning player was stabbed without overshield)
		{
			removeNinjas(); //remove ninjas from grid
			randomizeNinjas(); // replace ninjas randomly 
		}
		return eventType; //return event type (could be -7 meaning overshield saved player)
	}

	/**
	 * removes ninjas from grid
	 */
	public void removeNinjas()
	{
		for (int i=0; i<ninjas.length;++i)
		{
			if(ninjas[i].getIsAlive())
			{
				//remove ninja from the grid if ninja is alive
				grid[ninjas[i].getYPos()][ninjas[i].getXPos()].toggleIsNinjaOccupying();
			}
		}
	}
	
	/**
	 * chooses a random number between 0-80
	 * 
	 * each stands for a spot on the grid
	 * if the ninja is dead skip him in the for loop by adding 1 to i
	 * else
	 * if the room that random number represents does not contain a ninja, is not a room, and is at least
	 * 3 away from player, put the ninja there, else do not ++i so the loop runs with that ninja again
	 */
	public void randomizeNinjas()
	{
		int ranNum; 
		
		for (int i=0; i<ninjas.length;) //run until i>=ninjas.length
		{
			ranNum=(int)(Math.random()*81); // ranNum = number between 0-80 (each number represents 1 room)
			if(!ninjas[i].getIsAlive()) //if ninjas[i] is dead 
			{
				++i; //skip ninja
			}
				//else if ninja is not occupying that space, that space is not a room or that space is at least
			    //3 away from player put ninja there
			else if (!getGrid()[(int)(ranNum/9)][ranNum%9].getIsNinjaOccupying() &&
					 !getGrid()[(int)(ranNum/9)][ranNum%9].getIsRoom() &&
				    (ranNum%9>2 || ((int)(ranNum/9)<6))) 
			{
				getGrid()[(int)(ranNum/9)][ranNum%9].toggleIsNinjaOccupying(); //put ninja at his spot on grid
				ninjas[i].setPos((int)(ranNum/9), ranNum%9); //update ninja's classes coordinates
				++i; // add 1 to i to go to next ninja in for loop
			}
		}
	}
	
	
}
