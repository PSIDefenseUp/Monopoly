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
}