// Programmer:  Noah Angeles, Dennis McNamara, Tim Werkheiser, & Wenkan Zhu
// Assignment:  Project: Monopoly
// Date:        
// Description: 

import java.awt.Color;

public abstract class Property extends BoardLoc
{
    protected int cost;
    protected Player owner;
    protected Color color;
    
///////////////////////////////////////////////////////////////////////////////////////////////////
    public Property()
    // POST: 
    {
        super();
        cost = 0;
        color = Color.WHITE;
        owner = null;
    }
    
///////////////////////////////////////////////////////////////////////////////////////////////////
    public Property(String name, int position, int cost, Color color)
    // POST: 
    {
        super(name, position);
        this.cost = cost;
        this.color = color;
        this.owner = null; // what would this be
    }
    
///////////////////////////////////////////////////////////////////////////////////////////////////
    public void setOwner(Player owner)
    // PRE:  owner is initiallized
    // POST: propertiy position = "owner"
    {
        this.owner = owner;
    }
    
///////////////////////////////////////////////////////////////////////////////////////////////////
    public Player getOwner()
    // POST: 
    {
        return owner;
    }
    
///////////////////////////////////////////////////////////////////////////////////////////////////
    public int getCost()
    // POST: 
    {
        return cost;
    }
    
///////////////////////////////////////////////////////////////////////////////////////////////////
    public Color getColor()
    // POST: 
    {
        return color;
    }
}