// Programmer:  Noah Angeles, Dennis McNamara, Tim Werkheiser, & Wenkan Zhu
// Assignment:  Project: Monopoly
// Date:        
// Description: 

public class TaxSquare extends BoardLoc
{
    public TaxSquare()
    // POST: creates a tax tile with no name, and no position
    {
        super();
    }
    
///////////////////////////////////////////////////////////////////////////////////////////////////
    public TaxSquare(String name, int position)
    // PRE:  name == "Luxury Tax" || name == "Income Tax", and position is initialized
    // POST: Tax tile has bee created at position "position" with appropriate name
    {
        super();
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
}