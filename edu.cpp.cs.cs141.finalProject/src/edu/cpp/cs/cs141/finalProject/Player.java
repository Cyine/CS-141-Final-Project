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
	private int overShieldCharge;
	private boolean hasRadar;
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
		setPlayerPos(8,0);
		hasAmmo=true;
		overShieldCharge=0;
		hasRadar=false;
		lives=3;
	}
	
	
	
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
	public int getOvershieldCharge()
	{
		return overShieldCharge;
	}
	
	/**
	 * @return hasRadar
	 */
	public boolean getHasRadar()
	{
		return hasRadar;
	}
	
	/**------------------------------------------------ 
	 * MUTATOR METHODS
	 * ------------------------------------------------ 
	 */
	
	/**
	 * sets hasAmmo to true
	 */
	public void fillAmmo()
	{
		hasAmmo=true;
	}
	
	/**
	 * sets overShield to 6
	 */
	public void fillOverShield()
	{
		overShieldCharge=6;
	}
	
	/**
	 * sets hasRadar to true
	 */
	public void pickUpRadar()
	{
		hasRadar=true;
	}
	
	/**
	 * @param y
	 * @param x
	 * sets player to y,x
	 */
	public void setPlayerPos(int y, int x)
	{
		xPos=x;
		yPos=y;
	}
	
	/**
	 * @param x
	 * adds x to xPos
	 */
	public void changeXPos(int x)
	{
		xPos=xPos+x;
	}
	
	/**
	 * @param y
	 * adds y to yPos
	 */
	public void changeYPos(int y)
	{
		yPos=yPos+y;
	}
	
	/**
	 * sets overShieldCharge to 0
	 */
	public void useOverShield()
	{
		overShieldCharge = 0;
	}
	
	public void decayOverShield()
	{
		if(overShieldCharge>0)
		{
			overShieldCharge = overShieldCharge - 1;
		}
		
	}
	
	/**
	 * sets has ammo to false
	 */
	public void shoot()
	{
		hasAmmo=false;
	}
	
	/**
	 * subtracts one life from lives return -3 if not overshield
	 * unless player has overshield
	 * if player has overshield then use overshield return -7
	 */
	public int die()
	{
		if (overShieldCharge<=0)
		{
			lives=lives-1;
			return -3;
		}
		else
		{
			useOverShield();
			return -7;
		}	
	}
	
}
