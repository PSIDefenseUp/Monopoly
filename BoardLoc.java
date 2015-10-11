// Programmer:  Noah Angeles, Dennis McNamara, Tim Werkheiser, & Wenkan Zhu
// Assignment:  Project: Monopoly
// Date:        October 2015
// Description: Higher Class to implement Board Locations for the Fall 2015 CS342 Monopoly project

public abstract class BoardLoc
{
    protected int position;  // Board Locations postion from GO
    protected String name;  // name of the Board Location
    
    public abstract int getRent();
    // POST: FCTVAL = cost of rent when landing on property
    
    public abstract void onLand();
    // POST: Takes appropriate actions when players lands
    
    public abstract String[] getPossibleActions(Player player);
    // PRE:  player is initialized
    // POST: FCTVAL = array of options player has upon landing on this space, 
    //       to be used in a menu in a user interface
    
///////////////////////////////////////////////////////////////////////////////////////////////////
    public BoardLoc()
    // POST: creats a BoardLoc with position = 0 and blank name
    {
        position = 0;
        name = "";
    }
    
///////////////////////////////////////////////////////////////////////////////////////////////////
    public BoardLoc(String name, int position)
    // PRE:  name initialized &&  position >= 0 && position < 40 
    // POST: creates a BoardLoc Object with the object variables set to their corresponding
    //       parameters
    {
        this.name = name;
        this.position = position;
    }
    
///////////////////////////////////////////////////////////////////////////////////////////////////
    public String getName()
    // POST: FCTVAL = name
    {
        return name;
    }
    
///////////////////////////////////////////////////////////////////////////////////////////////////
    public int getPosition()
    // POST: FCTVAL = position
    {
        return position;
    }
    
///////////////////////////////////////////////////////////////////////////////////////////////////
    public String toString()
    // POST: String representation of BoardLoc
    {
        return "Name: " + name + ", Position: " + position;
    }
}
