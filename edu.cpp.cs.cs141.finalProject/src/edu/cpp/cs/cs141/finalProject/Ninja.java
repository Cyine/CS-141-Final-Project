package edu.cpp.cs.cs141.finalProject;

public class Ninja 
{
	/**
	 * xPos has ninja's x coordinate
	 * yPos has ninja's y coordinate
	 */
	private int xPos;
	private int yPos;
	private boolean isAlive;
	
	/**
	 * @param x
	 * @param y
	 * 
	 * sets ninja on grid
	 */
	public Ninja (int y, int x)
	{
		xPos=x;
		yPos=y;
		isAlive=true;
	}
	
	/**------------------------------------------
	 * GET/RETURN METHODS
	 * ------------------------------------------
	 */
	
	public int getXPos()
	{
		return xPos;
	}
	
	public int getYPos()
	{
		return yPos;
	}
	
	public boolean getIsAlive()
	{
		return isAlive;
	}
	
	/** ---------------------------------------------------
	 * MUTATOR METHODS
	 * ----------------------------------------------------
	 */
	
	public void changeXPos(int change)
	{
		xPos=xPos+change;
	}
	
	public void changeYPos(int change)
	{
		yPos=yPos+change;
	}
	
}
