package edu.cpp.cs.cs141.finalProject;

public class Space 
{
	/**
	 * powerUp is the power up in that space
	 * isVisible tells if the lights are on or not
	 * isNinjaOccuypin tells if there is a ninja in that space or not
	 * isPlayerOccupying tells if the player is in that space or not
	 * isRoom tells if it is a room or not
	 * isBriefcaseOccupying tells if the briefcase is in that room or not
	 * 
	 * int xPos
	 * int yPos are the coordinates of the room (y starts at top and goes down) 
	 */
	private Powerup powerUp;
	private boolean isVisible;
	private boolean isNinjaOccupying;
	private boolean isPlayerOccupying;
	private boolean isRoom;
	private boolean isBriefcaseOccupying;
	private int xPos;
	private int yPos;
	
	public Space(int x, int y)
	{
		xPos=x;
		yPos=y;
		isNinjaOccupying=false;
		isVisible=false;
	}
	
	/**-----------------------------------------
	 * MUTATOR METHODS
	 * -----------------------------------------
	 */
	
	/**
	 * @param pType
	 * sets powerup type in room
	 */
	public void setPowerup(String pType)
	{
		powerUp = new Powerup(pType);
		
	}
	
	/**-----------------------------------------------------------------------
	 * Toggle Methods
	 * Toggles whatever is in the name of function 
	 * -----------------------------------------------------------------------
	 */
	public void toggleIsVisible()
	{
		isVisible = !isVisible;
	}
	
	public void toggleIsNinjaOccupying()
	{
		isNinjaOccupying= !isNinjaOccupying;
	}
	
	public void toggleIsPlayerOccupying()
	{
		isPlayerOccupying= !isPlayerOccupying;
	}
	
	public void toggleIsRoom()
	{
		isRoom= !isRoom;
	}
	
	public void toggleIsBriefcaseOccupying()
	{
		isBriefcaseOccupying= !isBriefcaseOccupying;
	}
	
	
	/** --------------------------------------------
	 * GET/RETURN METHODS 
	 * ---------------------------------------------
	 */
	
	public int getXPos()
	{
		return xPos;
	}
	
	public int getYPos()
	{
		return yPos;
	}
	
	public boolean getIsPlayerOccupying()
	{
		return isPlayerOccupying;
	}
	
	public boolean getIsNinjaOccupying()
	{
		return isNinjaOccupying;
	}
	
	public boolean getIsBriefcaseOccupying()
	{
		return isBriefcaseOccupying;
	}
	
	public boolean getIsRoom()
	{
		return isRoom;
	}
	
	public boolean getIsVisible()
	{
		return isVisible;
	}
	
	public Powerup getPowerup()
	{
		return powerUp;
	}
	
	/**
	 * @param isDebugModeOn
	 * @return what the room displays
	 * 
	 * takes debug mode as parameter to tell whether to show room or not
	 * 
	 * if lights are off (isVisible==false) then show a '*' unless it is a room, then show 'R'
	 * if the lights are on and
	 * there is a briefcase in the room show the briefcase
	 * else if there is ninja in room show ninja
	 * else show the powerup
	 * else show nothing "[ ]"
	 */
	public String returnString(boolean isDebugModeOn, boolean hasRadar)
	{	
		if(isPlayerOccupying==false) //if player is not occupying space
		{
			if(isBriefcaseOccupying==true&&hasRadar==true) //if briefcase is occupying and radar is true
			{
				return"[b]";
			}
			if (isVisible==true||isDebugModeOn) // if debug mode is on/ is visible
			{
				if(isBriefcaseOccupying==true&&isDebugModeOn) //if there is briefcase show b
				{
					return"[b]";
				}
				if(isNinjaOccupying==false) // if there is not a ninja in space
				{
					if(isRoom==true) //if is room
					{
						return"[R]"; //show R
					}
					
					if(powerUp!=null) //if there is a power up
					{
						switch (powerUp.getType())
						{
							case "bullet": //if bullet
								return "[a]"; //return a
			
							case "shield": //if shield
								return "[s]"; //return s
								
							case "radar": //if radar
								return "[r]"; //return r
								
							default:
								return "[ ]";
						}
					}
					else //if debug/visible but nothing in space
					{
						return "[ ]";  //show blank space
					}
				}
				else //if ninja is there
				{
					return"[N]"; //return N
				}
			}
			else //if debug mode is off
			{ 
				if(isRoom==true) //if is room
				{
					return "[R]";
				}
				return "[*]"; //if is not room
			}
		}
		else
		{
			if(isNinjaOccupying) //if player and ninja are in the same space
			{
				return "[!]";
			}
			return "[P]"; //else return player
		}
	}

	
}
