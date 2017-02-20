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
	private boolean isDebugModeOn;
	private Space[][] grid;

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
		randomizeNinjas();
		isDebugModeOn=false;
		
	}
	
	/**-----------------------------
	 * RETURN/GET METHODS
	 * -----------------------------
	 */
	public Player getPlayer()
	{
		return player;
	}
	
	public Ninja[] getNinjas()
	{
		return ninjas;
	}
	
	public boolean getDebugMode()
	{
		return isDebugModeOn;
	}
	
	
	/**----------------------------------
	 * MUTATOR METHODS
	 * ----------------------------------
	 */
	
	/**
	 * @param direction
	 * Takes direction W for up S for down A for left and D for right
	 * 
	 * moves player respective direction as long as it is not at edge of grid
	 * Does this by changing player position and changing the spaces that have isPlayerOccupying==true
	 */
	public void toggleLightsOn(char direction)
	{
		if (direction == 'W')
		{
			if(player.getYPos()>0)
				grid[player.getYPos()-1][player.getXPos()].toggleIsVisible();
			if(player.getYPos()>1)
			grid[player.getYPos()-2][player.getXPos()].toggleIsVisible();
		}
		else if (direction == 'A')
		{
			if(player.getXPos()>0)
				grid[player.getYPos()][player.getXPos()-1].toggleIsVisible();
			if(player.getXPos()>1)
				grid[player.getYPos()][player.getXPos()-2].toggleIsVisible();
		}
		else if (direction == 'S'&&player.getYPos()!=8)
		{
			if(player.getYPos()<8)
				grid[player.getYPos()+1][player.getXPos()].toggleIsVisible();
			if(player.getYPos()<7)
				grid[player.getYPos()+2][player.getXPos()].toggleIsVisible();
		}
		else if(direction == 'D'&&player.getXPos()!=8)
		{
			if(player.getXPos()<8)
				grid[player.getYPos()][player.getXPos()+1].toggleIsVisible();
			if(player.getXPos()<7)
				grid[player.getYPos()][player.getXPos()+2].toggleIsVisible();
		}
		
	}
	
	
	public void movePlayer(char direction)
	{ 
		if (direction == 'W'&&player.getYPos()!=0)
		{
			grid[player.getYPos()][player.getXPos()].toggleIsPlayerOccupying();
			player.changeYPos(-1);
			grid[player.getYPos()][player.getXPos()].toggleIsPlayerOccupying();
		}
		else if (direction == 'A'&&player.getXPos()!=0)
		{
			grid[player.getYPos()][player.getXPos()].toggleIsPlayerOccupying();
			player.changeXPos(-1);
			grid[player.getYPos()][player.getXPos()].toggleIsPlayerOccupying();
		}
		else if (direction == 'S'&&player.getYPos()!=8)
		{
			grid[player.getYPos()][player.getXPos()].toggleIsPlayerOccupying();
			player.changeYPos(1);
			grid[player.getYPos()][player.getXPos()].toggleIsPlayerOccupying();
		}
		else if(direction == 'D'&&player.getXPos()!=8)
		{
			grid[player.getYPos()][player.getXPos()].toggleIsPlayerOccupying();
			player.changeXPos(1);
			grid[player.getYPos()][player.getXPos()].toggleIsPlayerOccupying();
		}
	}
	
	public int getNinjaDirection(Ninja ninja)
	{
		if(ninja.getYPos()!=0&&
				grid[ninja.getYPos()-1][ninja.getXPos()].getIsPlayerOccuyping()&&
				!grid[ninja.getYPos()-1][ninja.getXPos()].getIsNinjaOccupying())
		{
			return 0;
		}
		else if(ninja.getXPos()!=0&&
				grid[ninja.getYPos()][ninja.getXPos()-1].getIsPlayerOccuyping()&&
				!grid[ninja.getYPos()][ninja.getXPos()-1].getIsNinjaOccupying())
		{
			return 1;
		}
		else if(ninja.getYPos()!=8&&
				grid[ninja.getYPos()+1][ninja.getXPos()].getIsPlayerOccuyping()&&
				!grid[ninja.getYPos()+1][ninja.getXPos()].getIsNinjaOccupying())
		{
			return 2;
		}
		else if(ninja.getXPos()!=8&&
				grid[ninja.getYPos()][ninja.getXPos()+1].getIsPlayerOccuyping()&&
				!grid[ninja.getYPos()][ninja.getXPos()+1].getIsNinjaOccupying())
		{
			return 3;
		}
		return ((int)(Math.random()*4));
	}
	public void moveNinjas()
	{
		System.out.println("moveNinjas");
		int ranNum;
		
		
		for(int i=0; i<ninjas.length;)
		{
			if(ninjas[i].getIsAlive())
			{
			ranNum = getNinjaDirection(ninjas[i]);
			
			if (ranNum == 0&&ninjas[i].getYPos()!=0&&
				!grid[ninjas[i].getYPos()-1][ninjas[i].getXPos()].getIsBriefcaseOccupying()&&
				!grid[ninjas[i].getYPos()-1][ninjas[i].getXPos()].getIsNinjaOccupying())
			{
				grid[ninjas[i].getYPos()][ninjas[i].getXPos()].toggleIsNinjaOccupying();
				ninjas[i].changeYPos(-1);
				grid[ninjas[i].getYPos()][ninjas[i].getXPos()].toggleIsNinjaOccupying();
				++i;
			}
			else if (ranNum == 1&&ninjas[i].getXPos()!=0&&
					!grid[ninjas[i].getYPos()][ninjas[i].getXPos()-1].getIsBriefcaseOccupying()&&
					!grid[ninjas[i].getYPos()][ninjas[i].getXPos()-1].getIsNinjaOccupying())
			{
				grid[ninjas[i].getYPos()][ninjas[i].getXPos()].toggleIsNinjaOccupying();
				ninjas[i].changeXPos(-1);
				grid[ninjas[i].getYPos()][ninjas[i].getXPos()].toggleIsNinjaOccupying();
				++i;
			}
			else if (ranNum == 2&&ninjas[i].getYPos()!=8&&
					!grid[ninjas[i].getYPos()+1][ninjas[i].getXPos()].getIsBriefcaseOccupying()&&
					!grid[ninjas[i].getYPos()+1][ninjas[i].getXPos()].getIsNinjaOccupying())
			{
				grid[ninjas[i].getYPos()][ninjas[i].getXPos()].toggleIsNinjaOccupying();
				ninjas[i].changeYPos(1);
				grid[ninjas[i].getYPos()][ninjas[i].getXPos()].toggleIsNinjaOccupying();
				++i;
			}
			else if(ranNum == 3&&ninjas[i].getXPos()!=8&&
					!grid[ninjas[i].getYPos()][ninjas[i].getXPos()+1].getIsBriefcaseOccupying()&&
					!grid[ninjas[i].getYPos()][ninjas[i].getXPos()+1].getIsNinjaOccupying())
			{
				grid[ninjas[i].getYPos()][ninjas[i].getXPos()].toggleIsNinjaOccupying();
				ninjas[i].changeXPos(1);
				grid[ninjas[i].getYPos()][ninjas[i].getXPos()].toggleIsNinjaOccupying();
				++i;
			}
			}
			else 
			{
				++i;
			}

		}
	}
	
	
	/**
	 * sets debug mode to its opposite value
	 */
	public void toggleDebugMode()
	{
		isDebugModeOn= !isDebugModeOn;
	}
	
	
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
	 * sets ninjas to a random location on the grid provided that:
	 * There is no ninja already in that space
	 * There is no briefcase in that space
	 * The player is not 2 squares away from ninjas
	 */
	
	public void shoot (char direction)
	{
		if(direction=='W')
		{
			for (int i = player.getYPos()-1; i>0; --i)
			{
				if(grid[i][player.getXPos()].getIsNinjaOccupying())
				{
					grid[i][player.getXPos()].toggleIsNinjaOccupying();
				}
			}
		}
		else if(direction=='A')
		{
			for (int i = player.getXPos()-1; i>0; --i)
			{
				if(grid[player.getYPos()][i].getIsNinjaOccupying())
				{
					grid[player.getYPos()][i].toggleIsNinjaOccupying();
				}
			}
		}
		else if(direction=='S')
		{
			for (int i = player.getYPos()+1; i<8; ++i)
			{
				if(grid[i][player.getXPos()].getIsNinjaOccupying())
				{
					grid[i][player.getXPos()].toggleIsNinjaOccupying();
				}
			}
		}
		else if(direction=='D')
		{
			for (int i = player.getXPos()+1; i<8; ++i)
			{
				if(grid[player.getYPos()][i].getIsNinjaOccupying())
				{
					grid[player.getYPos()][i].toggleIsNinjaOccupying();
				}
			}
		}
	}
	public void randomizeNinjas()
	{
		int ranNum;
		
		for (int i=0; i<ninjas.length;)
		{
			ranNum=(int)(Math.random()*81);
			if (getGrid()[(int)(ranNum/9)][ranNum%9].getIsNinjaOccupying()==false &&
				getGrid()[(int)(ranNum/9)][ranNum%9].getIsRoom()==false &&
			    (ranNum%9>2 || ((int)(ranNum/9)<6))) 
			{
				getGrid()[(int)(ranNum/9)][ranNum%9].toggleIsNinjaOccupying();
				ninjas[i] = new Ninja((int)(ranNum/9), ranNum%9);
				++i;
			}
		}
	}
	
	/**
	 * makes every part of the grid an space except for 9 rooms
	 * then sets briefcase
	 */
	public void defineGrid()
	{
		for (int i=0; i<getGrid().length;++i)
		{
			for(int j=0; j<getGrid()[0].length; ++j)
			{
				getGrid()[i][j]= new Space(i, j);
				
				if ((i+1)%3==0 && (j+1)%3==0)
				{
					getGrid()[i-1][j-1].toggleIsRoom();
				}
			}
		}
		setBriefcase();
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
	 */
	public void setPowerUp(String powerUp) //pass the power up to place on grid (See setAllPowerUps)
	{
		int ranNum;         //create an int 
		boolean done=false; // create a boolean for the do/while loop
		do                  //do/while loop, do once but continue while done==false
		{ 
			//the %9 operator gives the
			ranNum=(int)(Math.random()*81); //creates a random int between 0-80 (1 for each spot on grid)
			if(getGrid()[(int)(ranNum/9)][ranNum%9].getPowerup()==null   //makes sure there is no power up on spot
				&& getGrid()[(int)(ranNum/9)][ranNum%9].getIsBriefcaseOccupying()==false //makes sure the spot does not have the briefcase on it
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
		int ranNum=(int)(Math.random()*9)+1;
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

	public Space[][] getGrid() {
		return grid;
	}

	public void setGrid(Space[][] theGrid) {
		grid = theGrid;
	}
	
}
