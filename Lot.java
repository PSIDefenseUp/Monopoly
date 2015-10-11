// Programmer:  Noah Angeles, Dennis McNamara, Tim Werkheiser, & Wenkan Zhu
// Assignment:  Project: Monopoly
// Date:        
// Description: 

import java.awt.Color;

public class Lot extends Property
{
    private int upgradeCost;
    private int rent[];
    private int upgradeCount;
    
///////////////////////////////////////////////////////////////////////////////////////////////////
    public Lot()
    // POST: 
    {
        super();
        upgradeCost = 0;
        rent = new int[] { 0, 0, 0, 0, 0, 0};
        upgradeCount = 0;
    }
    
///////////////////////////////////////////////////////////////////////////////////////////////////
    public Lot(String name, int position, int cost, Color color, int upgradeCost, int rent[])
    // POST: 
    {
        super(name, position, cost, color);
        this.upgradeCost = upgradeCost;
        this.rent = rent;
        this.upgradeCount = 0;
    }
    
///////////////////////////////////////////////////////////////////////////////////////////////////
    public int getRent()
    // POST: 
    {
        return this.rent[upgradeCount]; // requires an algorithm
    }
    
///////////////////////////////////////////////////////////////////////////////////////////////////
    public int getUpgradeCost()
    // POST: 
    {
        return upgradeCost;
    }
    
///////////////////////////////////////////////////////////////////////////////////////////////////
    public int getUpgradeCount()
    // POST: 
    {
        return upgradeCount;
    }
    
///////////////////////////////////////////////////////////////////////////////////////////////////
    public void upgrade()
    // PRE:  upgradeCount < 5 
    // POST: 
    {
        upgradeCount++;
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
        
///////////////////////////////////////////////////////////////////////////////////////////////////
    public String toString()
    // POST: FCTVAL = a String of the name of the loacitons, and it's position from start
    {
        String retStr = super.toString() + ", Rent: " + rent[upgradeCount] + ", Color:, ";
        
        if(Color.CYAN.equals(color))          // return string based on color of object
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
        
        switch(upgradeCount)
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