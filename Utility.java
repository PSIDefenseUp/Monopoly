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
    // PRE:  1 <= diceRoll <= 12 diceRoll is amount player rolled owner = Player who owns Utility
    //       assumes player has at least 1 utility and player can only have up to 2 utility
    // POST: FCTVAL == rent player owes 
    {
        int numberOwned = 0;    // keeps track of how many utility is owned
        Property[] property = owner.getProperties();  // properties owned by the player
        
        for (int i = 0; i < property.length; i++)   // counts how many utility is owned
        {
            if (property[i] instanceof Utility)
            {
                numberOwned++;
            }            
        }
        
        if (numberOwned == 2)
        {
            return owner.getRoll() * 10;
        }
        else
        {
            return owner.getRoll() * 4;
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
            case 1:  player.changeMoney(-1 * super.getCost());  // buy utility
                     super.setOwner(player);
                     break;
            default: player.changeMoney(-1 * getRent());  // use utility
                     owner.changeMoney(getRent());
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
}
