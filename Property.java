// Programmer:  Noah Angeles, Dennis McNamara, Tim Werkheiser, & Wenkan Zhu
// Assignment:  Project: Monopoly
// Date:        
// Description: 

import java.awt.Color;

public class Property extends BoardLoc
{
    protected int cost;
    protected Player owner;
    protected Color color;
    
///////////////////////////////////////////////////////////////////////////////////////////////////
    public Property()
    // PRE:  
    // POST: 
    {
        super();
        cost = 0;
        color = Color.WHITE;
        owner = ;  // what would this be?
    }
    
///////////////////////////////////////////////////////////////////////////////////////////////////
    public Property(String name, int position, int cost, Color color)
    // PRE:  
    // POST: 
    {
        super(name, position);
        this.cost = cost;
        this.color = color;
        this.owner = ; // what would this be
    }
    
///////////////////////////////////////////////////////////////////////////////////////////////////
    public void setOwner(Player owner)
    // PRE:  
    // POST: 
    {
        this.owner = owner;
    }
    
///////////////////////////////////////////////////////////////////////////////////////////////////
    public Player getOwner()
    // PRE:  
    // POST: 
    {
        return owner;
    }
    
///////////////////////////////////////////////////////////////////////////////////////////////////
    public int getCost()
    // PRE:  
    // POST: 
    {
        return cost;
    }
    
///////////////////////////////////////////////////////////////////////////////////////////////////
    public Color getColor()
    // PRE:  
    // POST: 
    {
        return color;
    }
}