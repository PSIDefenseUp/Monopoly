// Programmer:  Noah Angeles, Dennis McNamara, Tim Werkheiser, & Wenkan Zhu
// Assignment:  Project: Monopoly
// Date:        October 2015
// Description: Class to implement the Tax Squares for the Fall 2015 CS342 Monopoly project

public class TaxSquare extends BoardLoc
{
    public static int pot = 0;                          // Pot for free parking in dollars
    
    public TaxSquare()
    // POST: creates a TaxSquare instance with no name, and no position
    {
        super();
    }
    
    public TaxSquare(String name, int position)
    // PRE:  name == "Luxury Tax" || name == "Income Tax",
    //       position <= 0 && position > 40
    // POST: creates a TaxSquare instance with each object variable set to the corresponding
    //       <incoming> parameter
    {
        super(name, position);
    }
    
    public static int getPot()
    // POST: FCTVAL = Amount of money in the free parking pot (in dollars)
    {
        return pot;
    }
    
    public static void resetPot()
    // POST: The free parking pot is emptied
    {
        pot = 0;
    }
    
    public int getRent()
    // POST: FCTVAL = -200 if income tax tile || FCTVAL = -75 if luxury tax tile
    {
        if(this.name == "Income Tax")               // if this is the income tax tile
        {    
            pot = pot + 200;
            return -200;
        }
        else if(this.name == "Luxury Tax")          // else it could be the luxury tax
        {
            pot = pot + 75;
            return -75;
        }
        else                                        // else there is no defined tax amount
        {
            return 0;
        }
    }
        
    public void onLand(Player player, int option)
    // POST: automatically charges player appropriate amount of money
    {
        player.changeMoney(getRent());              // tax the player
    }
        
    public String[] getPossibleActions(Player player)
    // PRE:  player is initialized
    // POST: FCTVAL = array of options player has upon landing on this space, 
    //       to be used in a menu in a user interface
    {
        return new String[] {"Pay Tax"};
    }
        
    public String toString()
    // POST: FCTVAL = a String of the name of the loacitons, and it's position from start
    {
        return super.toString();
    }
}
