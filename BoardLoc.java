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
    
    public abstract void onLand(Player player, int option);
    // PRE:  Player must be initialized, and option must be one of the options from 
    //       the following getPossibleActions() function
    // POST: Takes appropriate actions when players land on the board location, 
    //       given the selected option
    
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
    // PRE: g is initialized
    // POST: A board space is drawn for this boardloc at (x, y) with dimensions width x height 
    {
        int charcount;                      // Number of characters that can be fit into this tile
        int playerSize;                     // Width + height to draw a player at on this space

        // Draw background
        g.setColor(Color.WHITE);
        g.fillRect(x, y, width, height);

        // Draw name
        g.setColor(Color.BLACK);
        charcount = this.name.length();
        while(g.getFontMetrics().stringWidth(this.name.substring(0, charcount)) >= width - 4)
        {
            charcount--;
        }
        g.drawString(this.name.substring(0, charcount), x + 2, y + height/2);

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

        // Draw outline
        g.drawRect(x, y, width, height);         
    }
}
