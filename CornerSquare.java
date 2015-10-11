// Programmer:  Noah Angeles, Dennis McNamara, Tim Werkheiser, & Wenkan Zhu
// Assignment:  Project: Monopoly
// Date:        October 2015
// Description: Class to implement the Corner Squares for the Fall 2015 CS342 Monopoly project

public class CornerSquare extends BoardLoc
{
    public CornerSquare() 
    // POST: CornerSquare created without name or position
    {
        super();
    }
    
///////////////////////////////////////////////////////////////////////////////////////////////////
    public CornerSquare(String name, int position)
    // PRE:  name == "GO" || name == "Jail" || name == "Free Parking" || name == "Go To Jail",
    //       position <= 0 && position > 40
    // POST: creates a CornerSquare Object with the object variables set to their corresponding
    //       parameters
    {
        super(name, position);
    }
        
///////////////////////////////////////////////////////////////////////////////////////////////////
    public int getRent()
    // POST: FCTVAl = 0
    {
        return 0;
    }
        
///////////////////////////////////////////////////////////////////////////////////////////////////
    public void onLand(Player player)
    // POST: does nothing
    {
        ActionsMenu.runActionsMenu(getPossibleActions(player));  // give player option to move on
    }
        
///////////////////////////////////////////////////////////////////////////////////////////////////
    public String[] getPossibleActions(Player player)
    // PRE:  player is initialized
    // POST: FCTVAL = array of options player has upon landing on this space, 
    //       to be used in a menu in a user interface
    {
        String[] actions = {"Continue"};
        return actions;
    }
        
///////////////////////////////////////////////////////////////////////////////////////////////////
    public String toString()
    // POST: FCTVAL = a String of the name of the loacitons, and it's position from start
    {
        return super.toString();
    }
}
