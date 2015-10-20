// Programmer:  Noah Angeles, Dennis McNamara, Tim Werkheiser, & Wenkan Zhu
// Assignment:  Project: Monopoly
// Date:        October 2015
// Description: Class to implement Board Locations for the Fall 2015 CS342 Monopoly project

import java.awt.Graphics;
import java.awt.Color;

public abstract class BoardLoc
{
    protected int position;  // The Object's position on the board from the GO position
    protected String name;   // name of the Object
    
    public abstract int getRent();
    // POST: FCTVAL == cost of rent when landing on property
    
    public abstract void onLand(Player player);
    // PRE:  player is initialized
    // POST: Takes appropriate actions when players lands on board location
    
    public abstract String[] getPossibleActions(Player player);
    // PRE:  player is initialized
    // POST: FCTVAL == array of options player has upon landing on this space, 
    //       to be used in a menu in a user interface
    
    public BoardLoc()
    // POST: creates a BoardLoc instance with position = 0 and blank name
    {
        position = 0;
        name = "";
    }
    
    public BoardLoc(String name, int position)
    // PRE:  name initialized &&  position >= 0 && position < 40 
    // POST: creates a BoardLoc instance with each object variable set to the corresponding
    //       <incoming> parameter
    {
        this.name = name;
        this.position = position;
    }
    
    public String getName()
    // POST: FCTVAL == name
    {
        return name;
    }
    
    public int getPosition()
    // POST: FCTVAL == position
    {
        return position;
    }
    
    public String toString()
    // POST: FCTVAL == String representation of BoardLoc
    {
        return "Name: " + name + ", Position: " + position;
    }

    public void render(Graphics g, int x, int y, int width, int height)
    {
        int charcount; // Number of characters that can be fit into this tile

        g.setColor(Color.WHITE);
        g.fillRect(x, y, width, height);

        g.setColor(Color.BLACK);
        charcount = this.name.length();
        while(g.getFontMetrics().stringWidth(this.name.substring(0, charcount)) >= width - 4)
        {
            charcount--;
        }

        g.drawRect(x, y, width, height);
        g.drawString(this.name.substring(0, charcount), x + 2, y + height/2);            
    }
}
