// Programmer:  Noah Angeles, Dennis McNamara, Tim Werkheiser, & Wenkan Zhu
// Assignment:  Project: Monopoly
// Date:        
// Description: 

import java.awt.Color;

public class Railroad extends Property
{
    public Railroad()
    // POST: Creates Railroad Object with position = 0, blank name, cost = 0,
    //       while Color set to White, & owner set to null 
    {
        super();
    }
    
///////////////////////////////////////////////////////////////////////////////////////////////////
    public Railroad(String name, int position, int cost)
    // PRE:  name initialized &&  position >= 0 && position < 40 && cost >= 0
    // POST: creates a Railroad Object with the object variables set to the corresponding
    //       parameters, while Color set to White, & owner set to null 
    {
        super(name, position, cost, Color.WHITE);
    }
    
///////////////////////////////////////////////////////////////////////////////////////////////////
    public int getRent()
    // POST: FCTVAl == absolute rent (based on on how many railroads owned)
    {
        int numOwned = 0;  // keeps track of how many Railroads is owned
        
        // find number of railroads owner has
        for(Property i : owner.getProperties())
        {
            if (i instanceof Railroad)  // if this instance is a railroad
                numOwned++;
        }
        
        switch(numOwned)  // return the correct amount
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
    // POST: if user buys instance: owner = player, && player loses money equivalent to Object cost
    //       if user uses instance: owner loses appropriate rent, & owner gains appropriate rent
    {
        int option;  // option the player chooses
        int rent; // amount to use the Object
        
        option = ActionsMenu.runActionsMenu(getPossibleActions(player));  // ask player for option

        if(owner == null && option == 1)  // if player wants to buy railroad
        {
            player.changeMoney(-1 * super.getCost());  // take money and buy it
            super.setOwner(player);
        }
        else if (owner != null && option == 0)  // if player has to use railroad
        {
            rent = getRent();
            player.changeMoney(-1 * rent);  // take money, and give it to owner
            owner.changeMoney(rent);
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
        
///////////////////////////////////////////////////////////////////////////////////////////////////
    public String toString()
    // POST: FCTVAL = a String of the name, positions, cost, and owner
    {
        return super.toString();
    }
}
