// Programmer:  Noah Angeles, Dennis McNamara, Tim Werkheiser, & Wenkan Zhu
// Assignment:  Project: Monopoly
// Date:        
// Description: 

import java.awt.Color;

public class Lot extends Property
{
    private int upgradeCost;  // cost to upgrade Object (i.e., add house or hotel)
    private int rent[];  // 6 valued array with cost to rent for each upgrade amount
    private int upgradeCount;  // value representing amount of upgrades
    
///////////////////////////////////////////////////////////////////////////////////////////////////
    public Lot()
    // POST: Creates Lot Object with position = 0, blank name, cost = 0, upgradeCost = 0, 
    //       upgradeCount = 0, rent = {0,0,0,0,0}. while Color set to White, & owner set to null 
    {
        super();
        upgradeCost = 0;
        rent = new int[] { 0, 0, 0, 0, 0, 0};
        upgradeCount = 0;
    }
    
///////////////////////////////////////////////////////////////////////////////////////////////////
    public Lot(String name, int position, int cost, Color color, int upgradeCost, int rent[])
    // PRE:  name initialized &&  position >= 0 && position < 40 && cost >= 0 && upgradeCount = 0
    //       upgradeCost > 0 && all rent values > 0 && color == Color.CYAN, Color.MAGENTA,
    //       Color.ORANGE, Color.RED, Color.YELLOW, Color.GREEN, Color.BLUE, or ________________
    // POST: creates a Railroad Object with the object variables set to the corresponding
    //       parameters, while owner set to null 
    {
        super(name, position, cost, color);
        this.upgradeCost = upgradeCost;
        this.rent = rent;
        this.upgradeCount = 0;
    }
    
///////////////////////////////////////////////////////////////////////////////////////////////////
    public int getRent()
    // POST: FCTVAL == absolute rent (based on upgradeCount)
    {
        if(owner == null)
            return 0;
        else
            return this.rent[upgradeCount]; // requires an algorithm
    }
    
///////////////////////////////////////////////////////////////////////////////////////////////////
    public int getUpgradeCost()
    // POST: FCTVAL == absolute cost to upgrade Object
    {
        if(upgradeCount == 5)  // if completely upgraded
            return 0;
        else  // if there's room to upgrade
            return upgradeCost;
    }
    
///////////////////////////////////////////////////////////////////////////////////////////////////
    public int getUpgradeCount()
    // POST: FCTVAL == amount of upgrades on Object
    {
        return upgradeCount;
    }
    
///////////////////////////////////////////////////////////////////////////////////////////////////
    public void upgrade()
    // PRE:  upgradeCount < 5 
    // POST: 
    {
        if(upgradeCount == 5) // if Object has been completely upgraded
            return;
        else  // if there's room to upgrade
            upgradeCount++;
    }
        
    public void onLand(Player player)
    // POST: see below
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
    
    public String[] getPossibleActions(Player player)
    // PRE:  player is initialized
    // POST: FCTVAL = array of options player has upon landing on this space, 
    //       to be used in a menu in a user interface
    {
        String[] notOwned = {"End Turn", "Buy"};
        String[] owned = {"Pay Rent"};
        
        if(owner == null)  // if the lot isn't owned,
            return notOwned;
        else  // else it must be owned
            return owned;
    }
        
///////////////////////////////////////////////////////////////////////////////////////////////////
    public String toString()
    // POST: FCTVAL = a String of the name of the loacitons, and it's position from start
    {
        String retStr;  // String to be returned
        
        retStr = super.toString() + ", Rent: " + rent[upgradeCount] + ", Color:, ";
        
        if(Color.CYAN.equals(color))        // add correct color of object to return string
            retStr += "Cyan";
        else if(Color.MAGENTA.equals(color))
            retStr += "Magenta";
        else if(Color.ORANGE.equals(color))
            retStr += "Orange";
        else if(Color.RED.equals(color))
            retStr += "Red";
        else if(Color.YELLOW.equals(color))
            retStr += "Yellow";
        else if(Color.GREEN.equals(color))
            retStr += "Green";
        else if(Color.BLUE.equals(color))
            retStr += "Blue";
        else
            retStr += "Brown";
        
        switch(upgradeCount)  // add upgrade-count and upgrade-cost to return string
        {
            case 1:   retStr += ", One House, Upgrade Cost: " + upgradeCost;
                      break;
            case 2:   retStr += ", Two Houses, Upgrade Cost: " + upgradeCost;
                      break;
            case 3:   retStr += ", Three Houses, Upgrade Cost: " + upgradeCost;
                      break;
            case 4:   retStr += ", Four Houses, Upgrade Cost: " + upgradeCost;
                      break;
            case 5:   retStr += ", One Hotel, Cannot Upgrade";
                      break;
            default:  retStr += ", No Upgrades, Upgrade Cost: " + upgradeCost;
        }
        
        return retStr;
    }
}
