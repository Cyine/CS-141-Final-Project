package edu.cpp.cs.cs141.finalProject;

public class GameEngine {
	/**
	 * g is a GameGrid
	 * isGameWon is used to keep track if the player won 
	 * isGameRunning tells if the game is over (either the player lost or won)
	 * lastDirectionLights is used to tell which lights to turn off after the player moves
	 */
	private GameGrid g;
	private boolean isGameWon;
	private boolean isGameRunning;
	private char lastDirectionLights;

	public GameEngine() 
	{
		
	}
	
	/**---------------------------------------------------------
	 * MUTATOR METHODS
	 * ---------------------------------------------------------
	 */
	
	/**
	 * makes a new GameGrid
	 * sets isGameRunning to true
	 */
	public void setupNewGame()
	{
		g = new GameGrid(); //make newGrid
		isGameRunning=true; 
	}
	
	/**
	 * @param direction
	 * @return eventType
	 * 
	 * First turns off the lights that were turned on
	 * moves player and sets returnNum to what it returns
	 * if eventType==1 then that means the player found the briefcase 
	 * 	set isGameWon to true and isGameRunning to false and return returnNum
	 * 
	 * checks for powerup where player is standing
	 * if there is one set eventType1 to 3
	 * 
	 * set eventType to what checkForDeath returns
	 * 
	 * if checkForDeath returns 0 return eventType1 else return eventType
	 * 
	 * 
	 */
	public int movePlayer(char direction)
	{
		g.toggleLightsOn(lastDirectionLights); //turn off lights
		int eventType1 = g.movePlayer(direction); //eventType1 gotten from running g.movePlayer()
		if(eventType1==1) //if eventType1 ==1 (meaning breifcaseFound)
		{
			isGameWon=true; //set game won to true
			isGameRunning=false; //set game running to false
			return eventType1; //return eventType1 (1)
		}
		if (checkForPowerUp()) //if player picks up powerup eventType1 = 3 (meaning something was picked up)
		{
			eventType1=3; 
		}
		
		int eventType =0; //make new eventType
		eventType = checkForDeath(); //set eventType to what checkForDeath() returns
		if(eventType!=0) // if the event type is not 0 (meaning nothing happened)
		{
			return eventType; //return eventType
		}
		return eventType1; //else eventType1 is more important, return eventType1
	}
	
	/**
	 * @return boolean 
	 * if there is a powerup where player is standing use the power up and return true
	 */
	public boolean checkForPowerUp()
	{
		//if there is a power up where player is standing
		if(g.getGrid()[g.getPlayer().getYPos()][g.getPlayer().getXPos()].getPowerup()!= null)
		{
			//use powerup in that grid
			g.getGrid()[g.getPlayer().getYPos()][g.getPlayer().getXPos()].getPowerup().usePowerup(g.getPlayer());
			return true; //return true
		}
		return false; //else return false
		
	}
	
	/**
	 * @param direction
	 * @return
	 * if player has ammo shoot and return what g.shoot returns
	 * else return -5
	 */
	public int shoot(char direction)
	{
		g.toggleLightsOn(lastDirectionLights);//turn lights off
		if (g.getPlayer().getHasAmmo()) //if player has ammo
		{
			return (g.shoot(direction));//return what g.shoot(direction) returns
		}
		return -5; //else return -5 (eventType meaning no bullet)
	}
	
	/**
	 * @return
	 * set eventType equal to what g.checkForDeath() returns
	 * if eventType is -3  reset player position and check if game is lost
	 * else return the eventType
	 * 
	 */
	public int checkForDeath()
	{
		int eventType;
		eventType=g.checkForDeath(); //set eventType to what g.checkForDeath() returns
		
		if(eventType==-3) //if its -3 (meaning player stabbed without shield)
		{
			//remove player from spot he is on
			g.getGrid()[g.getPlayer().getYPos()][g.getPlayer().getXPos()].toggleIsPlayerOccupying();
			g.setPlayerPos(8, 0); //set player back to start
			if(g.getPlayer().getLives()==0) //if player has no more lives
			{
				isGameRunning=false; // set isGameRunning to false
			}
			return -3; // return -3 (eventType meaning player stabbed without shield)
		}
		return eventType; //else return eventType (possibly -7) meaing stabbed with shield
	}
	
	/**
	 * @param direction
	 * toggles lights in direction inputed
	 * makes lastDirection=direction
	 */
	public void turnLightsOn(char direction)
	{
		g.toggleLightsOn(direction); //turn on lights
		lastDirectionLights=direction; //save last direction of lights
	}
	
	/**
	 * calls move ninjas and decays Player's overshield
	 */
	public void moveNinjas()
	{
		g.moveNinjas(); //move ninjas
		g.getPlayer().decayOverShield(); //decay player overShield because after ninja moves is when turn is over
	}
	
	/**---------------------------------------------------------
	 * GET FUNCTIONS
	 * ---------------------------------------------------------
	 */
	
	/**
	 * @return isGameWon
	 */
	public boolean getIsGameWon()
	{
		return (isGameWon);
	}
	
	/**
	 * @return isGameRunning
	 */
	public boolean getIsGameRunning()
	{
		return isGameRunning;
	}
	
	/**
	 * @return g (GameGrid)
	 */
	public GameGrid getGameGrid()
	{
		return g;
	}

}
