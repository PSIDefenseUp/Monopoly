// Programmer:  Noah Angeles, Dennis McNamara, Tim Werkheiser, & Wenkan Zhu
// Assignment:  Project: Monopoly
// Date:        October 2015
// Description: Class to implement the Railroad Squares for the Fall 2015 CS342 Monopoly project

import java.awt.Color;

public class Railroad extends Property
{
    public Railroad()
    // POST: Creates Railroad instance with position = 0, blank name, cost = 0,
    //       color set to WHITE, and owner set to null (bank)
    {
        super();
    }
    
    public Railroad(String name, int position, int cost)
    // PRE:  name initialized && 0 <= position < 40 && cost >= 0
    // POST: creates a Railroad instance with each object variable set to the corresponding
    //       <incoming> parameter, color set to WHITE, and owner set to null (bank)
    {
        super(name, position, cost, Color.WHITE);
    }
    
    public int getRent()
    // POST: FCTVAl == rent (based on how many railroads owned)
    {
        int numOwned;  // counter for the number of railroads owned by this railroad's owner
        
        numOwned = 0;
        
        if(owner != null)  // if railroad has an owner
        {
            // find number of railroads owner has
            for(Property i : owner.getProperties())
            {
                if (i instanceof Railroad)  // if this instance is a railroad, increment count
                    numOwned++;
            }
        }
        
        switch(numOwned)  // return the correct rent based on number of railroads
        {
            case 1:  return 25;
            case 2:  return 50;
            case 3:  return 100;
            case 4:  return 200;
            default: return 0;
        }
    }
        
    public void onLand(Player player)
    // POST: if player buys instance: owner = player, && player loses money equivalent to cost
    //       if player needs pay rent: player loses appropriate rent, & owner gains appropriate rent
    {
        int option;  // option the player chooses
        int rent; // amount to use the Object
        
        option = ActionsMenu.runActionsMenu(getPossibleActions(player));  // ask player for option

        if(owner == null && option == 1)  // if player wants to buy railroad
        {
            player.changeMoney(-1 * super.getCost());  // take money and buy it
            super.setOwner(player);
            player.addProperty(this);
        }
        else if (owner != null && option == 0)  // if player has to use railroad
        {
            rent = getRent();
            player.changeMoney(-1 * rent);  // take money, and give it to owner
            owner.changeMoney(rent);
        }
        
        return;
    }
        
    public String[] getPossibleActions(Player player)
    // PRE:  player is initialized
    // POST: FCTVAL = array of options player has upon landing on this space, 
    //       to be used in a menu in a user interface
    {
        if(owner == null)                               // the railroad isn't owned,
            return new String[] {"End Turn", "Buy"};
        else if(owner == player)                        // player is the owner
            return new String[] {"End Turn"};
        else                                            // it is owned by another player
            return new String[] {"Pay For Ticket"};
    }
        
    public String toString()
    // POST: FCTVAL = a String of the name, positions, cost, and owner
    {
        return super.toString() + ", Rent: " + getRent();
    }
}
