package edu.cpp.cs.cs141.finalProject;

public class Powerup 
{
	private String type;
	
	/**
	 * 
	 * @param pType
	 * sets powerup type
	 */
	public  Powerup (String pType)
	{
		type=pType;
	}
	
	/**
	 * returns powerup type
	 */
	public String getType()
	{
		return type;
	}

	/**
	 * Depending on powerup type does something to player (gives ammo/ overshield/shows where brief case is)
	 */
	public void usePowerup(Player player)
	{
		//CODE NOT IMPLEMENTED YET
	}
}
