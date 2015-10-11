// Programmer:  Noah Angeles, Dennis McNamara, Tim Werkheiser, & Wenkan Zhu
// Assignment:  Project: Monopoly
// Date:        
// Description: 

import java.awt.Color;

public class Railroad extends Property
{
    public Railroad()
    // POST: Creates Railroad Object with position = 0, blank name, cost = 0,
    //       while Color set to Black, & owner set to null 
    {
        super();
        color = Color.BLACK;
    }
    
///////////////////////////////////////////////////////////////////////////////////////////////////
    public Railroad(String name, int position, int cost)
    // PRE:  name initialized &&  position >= 0 && position < 40 && cost >= 0
    // POST: creates a Railroad Object with the object variables set to the corresponding
    //       parameters, while Color set to Black, & owner set to null 
    {
        super(name, position, cost, Color.BLACK);
    }
    
///////////////////////////////////////////////////////////////////////////////////////////////////
    public int getRent()
    // POST: FCTVAl == rent amount (based on on how many railroads owned
    {
        int numRailroads = 0;
        
        // find number of railroads owner has
        for(Property i : owner.getProperties())
        {
            if(Color.BLACK.equals(i.getColor()))  // Railroads are set to black,
                ++numRailroads;                   // if the color is black, it must be a railroad
        }
        
        switch(numRailroads)  // return the correct amount
        {
            case 1:  return 25;
            case 2:  return 50;
            case 3:  return 100;
            case 4:  return 200;
            default: return 0;
        }
    }
        
///////////////////////////////////////////////////////////////////////////////////////////////////
    public void onLand(Player player)
    // POST: see below
    {
        int option;  // option the player chooses
        
        option = ActionsMenu.runActionsMenu(getPossibleActions(player));  // ask player for option
        
        switch(option)  // take appropriate actions 
        {
            case 1:  player.changeMoney(-1 * super.getCost());  // buy railroad
                     super.setOwner(player);
                     break;
            default: player.changeMoney(-1 * getRent());  // use railroad
                     owner.changeMoney(getRent());
        }
        
        return;
    }
        
///////////////////////////////////////////////////////////////////////////////////////////////////
    public String[] getPossibleActions(Player player)
    // PRE:  player is initialized
    // POST: FCTVAL = array of options player has upon landing on this space, 
    //       to be used in a menu in a user interface
    {
        String[] notOwned = {"End Turn", "Buy"};
        String[] owned = {"Pay for Ticket"};
        
        if(owner == null)  // if the railroad isn't owned,
            return notOwned;
        else  // else it must be owned
            return owned;
    }
}
