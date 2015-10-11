// Programmer:  Noah Angeles, Dennis McNamara, Tim Werkheiser, & Wenkan Zhu
// Assignment:  Project: Monopoly
// Date:        October 2015
// Description: Higher Class to implement the Properties for the Fall 2015 CS342 Monopoly project

import java.awt.Color;

public abstract class Property extends BoardLoc
{
    protected int cost;  // cost of the property
    protected Player owner;  // owner of the property
    protected Color color;  // color of the property
    
///////////////////////////////////////////////////////////////////////////////////////////////////
    public Property()
    // POST: Creates Property Object with position = 0, blank name, cost = 0, Color set to White,
    //       & owner set to null
    {
        super();
        cost = 0;
        color = Color.WHITE;
        owner = null;
    }
    
///////////////////////////////////////////////////////////////////////////////////////////////////
    public Property(String name, int position, int cost, Color color)
    // PRE:  name initialized && position <= 0 && position > 40 && cost <= 0 && color == Color.CYAN, 
    //       Color.MAGENTA, Color.ORANGE, Color.RED, Color.YELLOW, Color.GREEN, Color.BLUE, 
    //       Color.BLACK, Color.GRAY, or some variation of Brown
    // POST: creates a Class Object with the object variables set to the corresponding parameters,
    //       while owner is set to null
    {
        super(name, position);
        this.cost = cost;
        this.color = color;
        this.owner = null;
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
    // POST: FCTVAL = owner
    {
        return owner;
    }
    
///////////////////////////////////////////////////////////////////////////////////////////////////
    public int getCost()
    // POST: FCTVAL = cost
    {
        return cost;
    }
    
///////////////////////////////////////////////////////////////////////////////////////////////////
    public Color getColor()
    // POST: FCTVAL = color
    {
        return color;
    }
        
///////////////////////////////////////////////////////////////////////////////////////////////////
    public String toString()
    // POST: FCTVAL = a String of the name, positions, cost, and owner
    {
        return super.toString() + ", Cost: " + cost + ", Owner: " + owner;
    }
}