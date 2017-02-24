package edu.cpp.cs.cs141.finalProject;
import java.io.Serializable;
public class Ninja implements Serializable
{
	/**
	 * xPos has ninja's x coordinate
	 * yPos has ninja's y coordinate
	 * isAlive tells gameGrid if the Ninja is alive (moves and is shown)
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
	public Ninja ()
	{
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
	/**
	 * @param y
	 * @param x
	 * sets player pos to y, x
	 */
	public void setPos(int y, int x)
	{
		yPos=y;
		xPos=x;
	}
	
	/**
	 * @param change
	 * add change to xPos
	 */
	public void changeXPos(int change)
	{
		xPos=xPos+change;
	}
	
	/**
	 * @param change
	 * adds change to yPos
	 */
	public void changeYPos(int change)
	{
		yPos=yPos+change;
	}
	
	/**
	 * sets isAlive to false
	 */
	public void kill()
	{
		isAlive=false;
	}
	
}
