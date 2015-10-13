// Programmer:  Noah Angeles, Dennis McNamara, Tim Werkheiser, & Wenkan Zhu
// Assignment:  Project: Monopoly
// Date:        October 2015
// Description: Class to implement the Card Squares for the Fall 2015 CS342 Monopoly project

public class CardSquare extends BoardLoc
{
    public CardSquare()
    // POST: CardSquare instance created with blank name and no position
    {
        super();
    }
    
///////////////////////////////////////////////////////////////////////////////////////////////////
    public CardSquare(String name, int position)
    // PRE:  name == "Chance" || name == "Community Chest",
    //       position <= 0 && position > 40
    // POST: creates a CardSquare instance with the each object variable set to the corresponding
    //       <incoming> parameter
    {
        super(name, position);
    }
        
///////////////////////////////////////////////////////////////////////////////////////////////////
    public int getRent()
    // POST: FCTVAL >= -200 && FCTVAL <= 200
    {
        return (int)(Math.random()*401) - 200;
    }
        
///////////////////////////////////////////////////////////////////////////////////////////////////
    public void onLand(Player player)
    // POST: automatically charges or gives player appropriate amount of money
    {
        ActionsMenu.runActionsMenu(getPossibleActions(player));  // display options to player
        player.changeMoney(getRent());  // tax the player
    }
        
///////////////////////////////////////////////////////////////////////////////////////////////////
    public String[] getPossibleActions(Player player)
    // PRE:  player is initialized
    // POST: FCTVAL == array of options player has upon landing on this space, 
    //       to be used in a menu in a user interface
    {
        return new String[] {"Continue"};
    }
        
///////////////////////////////////////////////////////////////////////////////////////////////////
    public String toString()
    // POST: FCTVAL == a String of the name of the locations, and its position from start
    {
        return super.toString();
    }
}
