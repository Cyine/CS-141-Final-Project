package edu.cpp.cs.cs141.finalProject;

public class GameEngine {
	private GameGrid g;
	private boolean isGameRunning;
	private char lastDirectionLights;

	public GameEngine() 
	{
		
	}
	
	public void setupNewGame()
	{
		g = new GameGrid();
		isGameRunning=true;
	}
	public GameGrid getGameGrid()
	{
		return g;
	}
	
	public void movePlayer(char direction)
	{
		g.toggleLightsOn(lastDirectionLights);
		g.movePlayer(direction);
		if(getIsGameWon())
		{
			isGameRunning=false;
		}
		checkForDeath();
	}
	
	public void shoot(char direction)
	{
		g.toggleLightsOn(lastDirectionLights);
		if (g.getPlayer().getHasAmmo())
		{
			g.getPlayer().shoot();
			g.shoot(direction);
		}
	}
	
	public void checkForDeath()
	{
		if(g.getGrid()[g.getPlayer().getYPos()][g.getPlayer().getXPos()].getIsNinjaOccupying())
		{
			g.getPlayer().die();
			g.getGrid()[g.getPlayer().getYPos()][g.getPlayer().getXPos()].toggleIsPlayerOccupying();
			g.setPlayerPos(8, 0);
			if(g.getPlayer().getLives()==0)
			{
				isGameRunning=false;
			}
		}
			
	}
	
	public void turnLightsOn(char direction)
	{
		g.toggleLightsOn(direction);
		lastDirectionLights=direction;
	}
	
	public void moveNinjas()
	{
		g.moveNinjas();
	}
	
	public boolean getIsGameWon()
	{
		return (g.getGrid()[g.getPlayer().getYPos()][g.getPlayer().getXPos()].getIsBriefcaseOccupying());
	}
	
	public boolean getIsGameRunning()
	{
		return isGameRunning;
	}
	

}
