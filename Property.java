// Programmer:  Noah Angeles, Dennis McNamara, Tim Werkheiser, & Wenkan Zhu
// Assignment:  Project: Monopoly
// Date:        October 2015
// Description: Higher Class to implement the Properties for the Fall 2015 CS342 Monopoly project

import java.awt.Color;
import java.awt.Graphics;

public abstract class Property extends BoardLoc
{
    protected int cost;                             // cost of the property
    protected Player owner;                         // owner of the property
    protected Color color;                          // color of the property
    
    public Property()
    // POST: Creates Property instance with position = 0, blank name, cost = 0, Color set to White,
    //       & owner set to null
    {
        super();
        cost = 0;
        color = Color.WHITE;
        owner = null;
    }
    
    public Property(String name, int position, int cost, Color color)
    // PRE:  name initialized && 0 <= position < 40 && cost >= 0 && upgradeCost > 0 
    //       && rent a 6-value array of costs in dollars && color initialized
    // POST: creates a Property instance with each object variable set to the corresponding
    //       <incoming> parameter, and owner set to null (bank)
    {
        super(name, position);
        this.cost = cost;
        this.color = color;
        this.owner = null;
    }
    
    public void setOwner(Player owner)
    // PRE:  owner is initialized
    // POST: the property is now owned by owner
    {
        this.owner = owner;
    }
    
    public Player getOwner()
    // POST: FCTVAL = owner
    {
        return owner;
    }
    
    public int getCost()
    // POST: FCTVAL = cost
    {
        return cost;
    }
    
    public Color getColor()
    // POST: FCTVAL = color
    {
        return color;
    }
        
    public String toString()
    // POST: FCTVAL = a String of the name, positions, cost, and owner
    {
        if(owner != null)                                                   // if there is an owner
        {
            return super.toString() + ", Cost: " + cost + ", Owner: " + owner.getName();
        }
        else                                                                // if there isn't an owner
        {
            return super.toString() + ", Cost: " + cost + ", Owner: Not Owned";
        }
    }

    public void render(Graphics g, int x, int y, int width, int height)    
    // PRE: g is initialized
    // POST: A board space is drawn for this property at (x, y) with dimensions width x height 
    {
        String costString;                  // String containing the cost of this property
        int charcount;                      // Number of characters that can be fit into this tile
        int playerSize;                     // Width + height to draw a player at on this space

        // Draw background
        g.setColor(Color.WHITE);
        g.fillRect(x, y, width, height);

        if(Monopoly.getPlayers() != null)
        {
            // Draw any players on this space
            playerSize = height / 3;
            for(int i = 0; i < Monopoly.getPlayers().length; i++)
            {
                if(Monopoly.getPlayer(i).getPosition() == this.position)
                {
                    g.drawImage(Monopoly.getPlayer(i).getToken(), x + 1 + (i * playerSize), 
                        y + 1 + height - playerSize, playerSize, playerSize, null);
                }
            }
        }

        // Draw name
        g.setColor(Color.BLACK);
        charcount = this.name.length();
        while(g.getFontMetrics().stringWidth(this.name.substring(0, charcount)) >= width - 4)
        {
            charcount--;
        }
        g.drawString(this.name.substring(0, charcount), x + 2, y + height/2);        

        // Draw cost or rent + owner
        if(owner == null)
        {
            // Draw cost
            costString = "$" + this.cost;
            g.drawString(costString, x + 2, y + height - height/4);
        }
        else
        {
            // Draw owner
            g.drawImage(owner.getToken(), x + 1, y + 1, height/4, height/4, null);
        }

        // Draw outline
        g.drawRect(x, y, width, height);        
    }
}