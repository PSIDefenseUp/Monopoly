// Programmer:  Noah Angeles, Dennis McNamara, Tim Werkheiser, & Wenkan Zhu
// Assignment:  Project: Monopoly
// Date:        
// Description: Base BoardLoc class for Monopoly

public abstract class BoardLoc
{
    protected int position;
    protected String name;
    
///////////////////////////////////////////////////////////////////////////////////////////////////
    public BoardLoc()
    // POST: creats a BoardLoc with position = 0 and blank name
    {
        position = 0;
        name = "";
    }
    
///////////////////////////////////////////////////////////////////////////////////////////////////
    public BoardLoc(String name, int position)
    // PRE:  name, position are initialized && 0 < position < 40 
    // POST: creates a BoardLoc with name and position
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
    
    public String toString()
    // POST: String representation of BoardLoc
    {
        return "Name: " + name + " Position: " + position;
    }
    
    public abstract int getRent();
    // POST: FCTVAL = cost of rent when landing on property
    public abstract void onLand();
    // POST: Takes appropriate actions when players lands
    public abstract String[] getPossibleActions(Player player);
    // PRE:  player is initialized
    // POST: FCTVAL = array of options player has upon landing on this space, 
    //       to be used in a menu in a user interface
}
