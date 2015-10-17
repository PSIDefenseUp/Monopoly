// Programmer:  Noah Angeles, Dennis McNamara, Tim Werkheiser, & Wenkan Zhu
// Assignment:  Project: Monopoly
// Date:        October 2015
// Description: Class to implement the Lot Squares for the Fall 2015 CS342 Monopoly 

import java.awt.Color;

public class Lot extends Property
{
    private int upgradeCost;        // cost to upgrade the property (add a house or hotel)
    private int rent[];             // 6-value array with cost to rent for each upgrade amount
    private int upgradeCount;       // value representing amount of upgrades
    
    public Lot()
    // POST: Creates Lot instance with position = 0, blank name, cost = 0, upgradeCost = 0, 
    //       upgradeCount = 0, rent = {0,0,0,0,0}. while Color set to White, & owner set to null 
    {
        super();
        upgradeCost = 0;
        rent = new int[] { 0, 0, 0, 0, 0, 0};
        upgradeCount = 0;
    }
    
    public Lot(String name, int position, int cost, Color color, int upgradeCost, int rent[])
    // PRE:  name initialized && 0 <= position < 40 && cost >= 0 && upgradeCost > 0 
    //       && rent a 6-value array of costs in dollars && color initialized
    // POST: creates a Lot instance with each object variable set to the corresponding
    //       <incoming> parameter, and owner set to null (bank)
    {
        super(name, position, cost, color);
        this.upgradeCost = upgradeCost;
        this.rent = rent;
        this.upgradeCount = 0;
    }
    
    public int getRent()
    // POST: FCTVAL == absolute rent (based on upgradeCount)
    {
        return this.rent[upgradeCount];  // return appropriate value
    }

    public int getUpgradeCost()
    // POST: FCTVAL == cost to upgrade the lot
    {
        if(upgradeCount == 5)  // if completely upgraded
            return 0;  // player can't upgrade
        else  // if there's room to upgrade
            return upgradeCost;
    }
    
    public int getUpgradeCount()
    // POST: FCTVAL == amount of upgrades on the lot
    {
        return upgradeCount;
    }
    
    public void upgrade()
    // PRE:  upgradeCount < 5 
    // POST: this lot is upgraded by one level
    {
        upgradeCount++;
    }
    
    public void downgrade()
    // PRE:  upgradeCount > 0 
    // POST: this lot is downgraded by one level
    {
        upgradeCount--;
    }
    
    public void onLand(Player player)
    // POST: if player buys instance: owner = player, && player loses money equivalent to cost
    //       if player needs pay rent: player loses appropriate rent, & owner gains appropriate rent
    {
        int option;     // option the player chooses
        int rent;       // amount to use the Object
        
        option = ActionsMenu.runActionsMenu(getPossibleActions(player));  // ask player for option

        if(owner == null && option == 1)                // if player wants to buy railroad
        {
            player.changeMoney(-1 * super.getCost());   // take money and buy it
            super.setOwner(player);
            player.addProperty(this);
        }
        else if (owner != null && option == 0)          // if player has to use railroad
        {
            rent = getRent();
            player.changeMoney(-1 * rent);              // take money, and give it to owner
            owner.changeMoney(rent);
        }
        
        return;
    }
    
    public String[] getPossibleActions(Player player)
    // PRE:  player is initialized
    // POST: FCTVAL = array of options player has upon landing on this space, 
    //       to be used in a menu in a user interface
    {
         // if tile isn't owned, and player has enough money
         if(owner == null && player.getMoney() >= cost) 
             return new String[] {"End Turn", "Buy"};
         // if player owns tile, or if player can't buy tile
         else if(owner == player || (owner == null && player.getMoney() < cost)) 
             return new String[] {"End Turn"};
         else  // The lot is owned by someone else
             return new String[] {"Pay Rent"};
    }

    public String toString()
    // POST: FCTVAL = a String of the name of the locations, and its position from start
    {
        String retStr;  // String to be returned
        
        retStr = super.toString();
        
        if(owner != null)
            retStr += ", Rent: " + rent[upgradeCount];
        else
            retStr +=  ", Rent: 0";
            
        retStr += ", Color: ";
        
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
            retStr += "Purple";
        
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
