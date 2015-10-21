// Programmer:  Noah Angeles, Dennis McNamara, Tim Werkheiser, & Wenkan Zhu
// Assignment:  Project: Monopoly
// Date:        October 2015
// Description: Class to implement the Utility Squares for the Fall 2015 CS342 Monopoly project

import java.awt.Color;

public class Utility extends Property
{    
    public  Utility()
    // POST: Creates Utility instance with position = 0, blank name, cost = 0,
    //       while Color set to White, & owner set to null 
    {
        super();
    }
    
    public  Utility(String name, int position, int cost)
    // PRE:  name initialized && 0 <= position < 40 && cost >= 0
    // POST: creates a Utility instance with each object variable set to the corresponding
    //       <incoming> parameter, and owner set to null (bank)
    {
        super(name, position, cost, Color.WHITE);
    }
    
    public int getRent()
    // PRE:  1 <= diceRoll <= 12
    // POST: FCTVAL == rent player owes 
    {
        int numberOwned;        // keeps track of how many utility is owned
        Property[] property;    // properties owned by the player
        int roll;               // dice roll that landed 
        
        numberOwned = 0;
        roll = Monopoly.getCurrentRoll();
        
        if(owner != null)  // if utility is owned
        {
            property = owner.getProperties();
        
            for (int i = 0; i < property.length; i++)   // counts how many utility is owned
            {
                if (property[i] instanceof Utility)  // if player owns utility
                {
                    numberOwned++;
                }
            }
        }
        
        switch(numberOwned)
        {
            case 2: return roll * 10; // if owner has both utilities
            case 1: return roll * 4;  // if owner has one utility
            default: return 0;        // utility wasn't owned
        }
    }
        
    public void onLand(Player player, int option)
    // POST: if user buys instance: owner = player, && player loses money equivalent to Object cost
    //       if user uses instance: owner loses appropriate rent, & owner gains appropriate rent
    {
        int rent; // amount to use the Object
                
        if(owner == null && option == 1)  // if player wants to buy utility
        {
            player.changeMoney(-1 * super.getCost());  // take money and buy it
            super.setOwner(player);
            player.addProperty(this);
        }
        else if (owner != null && option == 0)  // if player has to use utility
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
         if(owner == null && player.getMoney() > cost) // if tile isn't owned, and player has enough money
             return new String[] {"End Turn", "Buy"};
         else if(owner == player || (owner == null && player.getMoney() <= cost)) // if player owns tile, or if player can't buy tile
             return new String[] {"End Turn"};
         else                                            // it is owned by another player
             return new String[] {"Pay Rent"};
    }
        
    public String toString()
    // POST: FCTVAL = a String of the name, positions, cost, and owner
    {
        return super.toString() + ", Rent: " + getRent();
    }
}
