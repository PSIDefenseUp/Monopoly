// Programmer:  Noah Angeles, Dennis McNamara, Tim Werkheiser, & Wenkan Zhu
// Assignment:  Project: Monopoly
// Date:        
// Description: 

import java.awt.Color;

public class Utility extends Property
{
    
///////////////////////////////////////////////////////////////////////////////////////////////////
    public  Utility()
    // POST: Creates Utility Object with position = 0, blank name, cost = 0,
    //       while Color set to Black, & owner set to null 
    {
        super();
    }
    
///////////////////////////////////////////////////////////////////////////////////////////////////
    public  Utility(String name, int position, int cost)
    // PRE:  name initialized &&  position >= 0 && position < 40 && cost >= 0
    // POST: creates a Utility Object with the object variables set to the corresponding
    //       parameters, while Color set to Black, & owner set to null 
    {
        super(name, position, cost, Color.WHITE);
    }
    
///////////////////////////////////////////////////////////////////////////////////////////////////
    public int getRent()
    // PRE:  1 <= diceRoll <= 12 diceRoll is amount player rolled owner = Player who owns Utility
    //       assumes player has at least 1 utility and player can only have up to 2 utility
    // POST: FCTVAL == rent player owes 
    {
        int numberOwned = 0;    // keeps track of how many utility is owned
        Property[] property = owner.getProperties();  // properties owned by the player
        
        for (int i = 0; i < property.length; i++)   // counts how many utility is owned
        {
            if (property[i] instanceof Utility)
                numberOwned++;
        }
        
        if (numberOwned == 2)
            return owner.getRoll() * 10;
        else if (numberOwned == 1)
            return owner.getRoll() * 4;
        else
            return 0;
    }
        
///////////////////////////////////////////////////////////////////////////////////////////////////
    public void onLand(Player player)
    // POST: if user buys instance: owner = player, && player loses money equivalent to Object cost
    //       if user uses instance: owner loses appropriate rent, & owner gains appropriate rent
    {
        int option;  // option the player chooses
        int rent; // amount to use the Object
        
        option = ActionsMenu.runActionsMenu(getPossibleActions(player));  // ask player for option
        
        if(owner == null && option == 1)  // if player wants to buy utility
        {
            player.changeMoney(-1 * super.getCost());  // take money and buy it
            super.setOwner(player);
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
        String[] notOwned = {"End Turn", "Buy"};
        String[] owned = {"Pay Rent"};
        
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
