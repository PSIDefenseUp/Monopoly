// Programmer:  Noah Angeles, Dennis McNamara, Tim Werkheiser, & Wenkan Zhu
// Assignment:  Project: Monopoly
// Date:        
// Description: 

import java.awt.Color;

public class Utility extends Property
{
    
///////////////////////////////////////////////////////////////////////////////////////////////////
    public  Utility()
    // POST: 
    {
        super();
    }
    
///////////////////////////////////////////////////////////////////////////////////////////////////
    public  Utility(String name, int position, int cost, Color color)
    // POST: 
    {
        super(name, position, cost, color);
    }
    
///////////////////////////////////////////////////////////////////////////////////////////////////
    public int getRent()
    // POST: FCTVAL == rent
    {
        // TODO: get how much should be paid when landing on a utility depending on what the owner has
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