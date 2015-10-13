// Programmer:  Noah Angeles, Dennis McNamara, Tim Werkheiser, & Wenkan Zhu
// Assignment:  Project: Monopoly
// Date:        October 2015
// Description: Class to implement the Corner Squares for the Fall 2015 CS342 Monopoly project

public class CornerSquare extends BoardLoc
{
    public CornerSquare() 
    // POST: CornerSquare instance created without name or position
    {
        super();
    }
    
///////////////////////////////////////////////////////////////////////////////////////////////////
    public CornerSquare(String name, int position)
    // PRE:  name == "GO" || name == "Jail" || name == "Free Parking" || name == "Go To Jail",
    //       position <= 0 && position > 40
    // POST: creates a CornerSquare instance with each object variable set to the corresponding
    //       <incoming> parameter
    {
        super(name, position);
    }
        
///////////////////////////////////////////////////////////////////////////////////////////////////
    public int getRent()
    // POST: FCTVAL == 0
    {
        return 0;
    }
        
///////////////////////////////////////////////////////////////////////////////////////////////////
    public void onLand(Player player)
    // POST: Displays users options on landing (which is nothing)
    {
        ActionsMenu.runActionsMenu(getPossibleActions(player));  // Allow player to end turn
    }
        
///////////////////////////////////////////////////////////////////////////////////////////////////
    public String[] getPossibleActions(Player player)
    // PRE:  player is initialized
    // POST: FCTVAL == array of options player has upon landing on this space, 
    //       to be used in a menu in a user interface
    {
        return new String[] {"End Turn"};
    }
        
///////////////////////////////////////////////////////////////////////////////////////////////////
    public String toString()
    // POST: FCTVAL == a String of the name of the locations, and its position from start
    {
        return super.toString();
    }
}
