// Programmer:  Noah Angeles, Dennis McNamara, Tim Werkheiser, & Wenkan Zhu
// Assignment:  Project: Monopoly
// Date:        October 2015
// Description: Higher Class to implement the Properties in the Fall 2015 CS342 Monopoly project

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
    //       or some variation of Brown
    // POST: Creates Property Object with position = "position", cost = "cost", color = "Color",
    //       color set to "Color", & owner set to null
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
    // POST: FCTVAL = a String of the name of the loacitons, and it's position from start
    {
        String retStr = super.toString() + ", Cost: " + cost + ", Owner: " + owner + ", Color:, ";
        
        if(Color.CYAN.equals(color))          // return string based on color of object
               return retStr + "Cyan";
        else if(Color.MAGENTA.equals(color))
               return retStr + "Magenta";
        else if(Color.ORANGE.equals(color))
               return retStr + "Orange";
        else if(Color.RED.equals(color))
               return retStr + "Red";
        else if(Color.YELLOW.equals(color))
               return retStr + "Yellow";
        else if(Color.GREEN.equals(color))
               return retStr + "Green";
        else if(Color.BLUE.equals(color))
               return retStr + "Blue";
        else
            return retStr + "Brown";
    }
}