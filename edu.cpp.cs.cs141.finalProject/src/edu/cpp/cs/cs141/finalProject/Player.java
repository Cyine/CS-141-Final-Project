package edu.cpp.cs.cs141.finalProject;

public class Player 
{  
	/**
	 * xPos is the player's x coordinate on the grid (0-8)
	 * yPos is the player's y coordinate on the grid (0-8)
	 * hasAmmo defines if the player has a bullet or not
	 * hasOvershield defines if the player has an overshield or not
	 * lives starts at 3. Player loses when it gets to 0
	 * 
	 */
	private int xPos;
	private int yPos;
	private boolean hasAmmo;
	private boolean hasOvershield;
	private int lives;
	
	/**
	 * Constructor. sets player coordinates
	 * to bottom left of map
	 * sets lives to three
	 * gives player ammo
	 * makes hasOvershield false
	 */
	public Player ()
	{
		setPlayerPos(1,1);
		hasAmmo=true;
		hasOvershield=false;
		lives=3;
	}
	
	/**
	 * removes ammo from player
	 */
	
	/**------------------------ ------------------------ 
	 * GET METHODS
	 * ------------------------ ------------------------ 
	 */
	
	/**
	 * @return lives
	 */
	public int getLives()
	{
		return lives;
	}
	
	/**
	 * @return xPos
	 */
	public int getXPos()
	{
		return xPos;
	}
	
	/**
	 * @return yPos
	 */
	public int getYPos()
	{
		return yPos;
	}
	
	/**
	 * @return hasAmmo
	 */
	public boolean getHasAmmo()
	{
		return hasAmmo;
	}
	
	/**
	 * @return hasOversheild
	 */
	public boolean getHasOvershield()
	{
		return hasOvershield;
	}
	
	
	/**------------------------ ------------------------ 
	 * MUTATOR METHODS
	 * ------------------------ ------------------------ 
	 */
	
	/**
	 * Fills player ammo
	 */
	public void fillAmmo()
	{
		hasAmmo=true;
	}
	
	/**
	 * gives player and overshield
	 */
	public void fillOvershield()
	{
		hasOvershield=true;
	}
	
	public void setPlayerPos(int x, int y)
	{
		xPos=x;
		yPos=y;
	}
	
	/**
	 * takes away player's overshield
	 */
	public void useOversheild()
	{
		hasOvershield = false;
	}
	
	/**
	 * takes away player's bullet
	 */
	public void shoot()
	{
		hasAmmo=false;
	}
	
	/**
	 * subtracts one life from player
	 */
	public void die()
	{
		lives=lives-1;
	}
	
}
