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
	
	/**
	 * changes isVisible to its opposite 
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
	
	public boolean getIsNinjaOccupying()
	{
		return isNinjaOccupying;
	}
	public boolean getIsBriefcaseOccupying()
	{
		return isBriefcaseOccupying;
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
	public String returnString(boolean isDebugModeOn)
	{	
		if(isPlayerOccupying==false)
		{
			if (isVisible==true||isDebugModeOn)
			{
				if(isBriefcaseOccupying==true)
				{
					return"[b]";
				}
				if(isNinjaOccupying==false)
				{
					if(powerUp!=null)
					{
						switch (powerUp.getType())
						{
							case "bullet":
								return "[a]";
			
							case "shield":
								return "[s]";
								
							case "radar":
								return "[r]";
								
							default:
								return "[ ]";
						}
					}
					else 
					{
						return "[ ]";
					}
				}
				else
				{
					return"[N]";
				}
			}
			else
			{ 
				if(isRoom==true)
				{
					return "[R]";
				}
				return "[*]";
			}
		}
		else
		{
			if(isNinjaOccupying)
			{
				return "[!]";
			}
			return "[P]";
		}
	}

	
}
