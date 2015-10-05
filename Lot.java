// Programmer:  Noah Angeles, Dennis McNamara, Tim Werkheiser, & Wenkan Zhu
// Assignment:  Project: Monopoly
// Date:        
// Description: 

import java.awt.Color;

public class Lot extends Property
{
    private int upgradeCost;
    private int rent;
    private int upgradeCount;
    
///////////////////////////////////////////////////////////////////////////////////////////////////
    public Lot()
    // PRE:  
    // POST: 
    {
        super();
        upgradeCost = 0;
        rent = 0;
        upgradeCount = 0;
    }
    
///////////////////////////////////////////////////////////////////////////////////////////////////
    public Lot(String name, int position, int cost, Color color, int upgradeCost, int rent)
    // PRE:  
    // POST: 
    {
        super(name, position, cost, color);
        this.upgradeCost = upgradeCost;
        this.rent = rent;
        this.upgradeCount = 0;
    }
    
///////////////////////////////////////////////////////////////////////////////////////////////////
    public int getRent()
    // PRE:  
    // POST: 
    {
        return // requires an algorithm
    }
    
///////////////////////////////////////////////////////////////////////////////////////////////////
    public int getUpgradeCost()
    // PRE:  
    // POST: 
    {
        return upgradeCost;
    }
    
///////////////////////////////////////////////////////////////////////////////////////////////////
    public int getUpgradeCount()
    // PRE:  
    // POST: 
    {
        return upgradeCount;
    }
    
///////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean upgrade()
    // PRE:  
    // POST: 
    {
        if(owner.changeMoney(-upgradeCost))
        {
            ++upgradeCount;
            return true;
        }
        else
            return false;
    }
}