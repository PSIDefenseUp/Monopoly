// Programmer:  Noah Angeles, Dennis McNamara, Tim Werkheiser, & Wenkan Zhu
// Assignment:  Project: Monopoly
// Date:        
// Description: 

public class Railroad extends Property
{
    
///////////////////////////////////////////////////////////////////////////////////////////////////
    public Railroad()
    // POST: 
    {
        super();
    }
    
///////////////////////////////////////////////////////////////////////////////////////////////////
    public Railroad(String name, int position, int cost, Color color)
    // POST: 
    {
        super(name, position, cost, color);
    }
    
///////////////////////////////////////////////////////////////////////////////////////////////////
    public int getRent()
    // POST: FCTVAl == rent amount (based on on how many railroads owned
    {
        // find number of railroads owner has
        // calculate and return appropriate rent
        return 0; // don't know the algorithm
    }
        
///////////////////////////////////////////////////////////////////////////////////////////////////
    public void onLand()
    // POST: see below
    {
        // if unowned
          // if player has enough money
            // offer purchase
        
        // else if owned <by other player> 
          // if player has enough funds
             // give owner funds
          // else player doesn't have enough
             // give owner all player's money
    }
}