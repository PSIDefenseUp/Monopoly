// Programmer:  Noah Angeles, Dennis McNamara, Tim Werkheiser, & Wenkan Zhu
// Assignment:  Project: Monopoly
// Date:        October 2015
// Description: Class to implement the Tax Squares in the Fall 2015 CS342 Monopoly project

public class TaxSquare extends BoardLoc
{
    public TaxSquare()
    // POST: creates a tax tile with no name, and no position
    {
        super();
    }
    
///////////////////////////////////////////////////////////////////////////////////////////////////
    public TaxSquare(String name, int position)
    // PRE:  name == "Luxury Tax" || name == "Income Tax",
    //       position <= 0 && position > 40
    // POST: Tax tile has bee created at position "position" with appropriate name
    {
        super(name, position);
    }
        
///////////////////////////////////////////////////////////////////////////////////////////////////
    public int getRent()
    // POST: FCTVAL = -200 if income tax tile || FCTVAL = -75 if luxury tax tile
    {
        if(this.name == "Income Tax")  // if this is the income tax tile
            return -200;
        else if(this.name == "Luxury Tax") // else it could be the luxury tax
            return -75;
        else  // else the tile has not been initialized
            return 0;
    }
        
///////////////////////////////////////////////////////////////////////////////////////////////////
    public void onLand()
    // POST: automatically charges player appropriate amount of money
    {
        Player player;  // used to determine
        
        //player = ??;  // get the player
        
        //player.changeMoney(getRent());  // tax the player
    }
        
///////////////////////////////////////////////////////////////////////////////////////////////////
    public String[] getPossibleActions(Player player)
    // PRE:  player is initialized
    // POST: FCTVAL = array of options player has upon landing on this space, 
    //       to be used in a menu in a user interface
    {
        String[] actions = {"Get Tax"};
        return actions;
    }
        
///////////////////////////////////////////////////////////////////////////////////////////////////
    public String toString()
    // POST: FCTVAL = a String of the name of the loacitons, and it's position from start
    {
        return super.toString();
    }
}