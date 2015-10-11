// Programmer:  Noah Angeles, Dennis McNamara, Tim Werkheiser, & Wenkan Zhu
// Assignment:  Project: Monopoly
// Date:        October 2015
// Description: Class to implement the Card Squares for the Fall 2015 CS342 Monopoly project

public class CardSquare extends BoardLoc
{
    public CardSquare()
    // POST: CardSquare created without name or position
    {
        super();
    }
    
///////////////////////////////////////////////////////////////////////////////////////////////////
    public CardSquare(String name, int position)
    // PRE:  name == "Chance" || name == "Community Chest",
    //       position <= 0 && position > 40
    // POST: creates a CardSquare Object with the object variables set to their corresponding
    //       parameters
    {
        super(name, position);
    }
        
///////////////////////////////////////////////////////////////////////////////////////////////////
    public int getRent()
    // POST: FCTVAL >= -200 && FCTVAL <= 200
    {
        if(Math.random() >= 0.5)  // randomly choose if value will be positive
            return (int)(Math.random()*201);  
        else  // else it must be negative
            return (-1)*(int)(Math.random()*201);
    }
        
///////////////////////////////////////////////////////////////////////////////////////////////////
    public void onLand(Player player)
    // POST: automatically charges or gives player appropriate amount of money
    {
        runActionsMenu(getPossibleActions(player));  // display options to player
        player.changeMoney(getRent());  // tax the player
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
    // POST: FCTVAL = a String of the name of the locaitons, and it's position from start
    {
        return super.toString();
    }
}