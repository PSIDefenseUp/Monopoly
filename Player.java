// Programmer:  Noah Angeles, Dennis McNamara, Tim Werkheiser, & Wenkan Zhu
// Assignment:  Project: Monopoly
// Date: October 2015        
// Description: Class to represent the players in a Monopoly game

import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;

public class Player
{
    private int money;                      // amount of money a Player Object has
    private int position;                   // Objects position <on board> from GO
    private Property[] properties;          // List of properties owned by this player
    private String name;                    // name of the player
    private BufferedImage token;            // the token the player appears as

    public Player()
    // POST: Creates Player instance with money = $1500, position = 0, no properties,
    //        and a name set to "Not Named"
    {
        name = "Not Named";
        money = 1500;
        position = 0;
        properties = new Property[0];
    }

    public Player(String name)
    // PRE:  name must be initialized
    // POST: Creates Player instance with money = $1500, position = 0, no properties,
    //        and a name set to "name"
    {
        this.name = name;
        money = 1500;
        position = 0;
        properties = new Property[0];
    }

    public int getMoney()
    // POST: FCTVAL == money
    {
        return money;
    }

    public Property[] getProperties()
    // POST: FCTVAL == properties
    {
        return properties;
    }

    public int getPosition()
    // POST: FCTVAL == position
    {
        return position;
    }

    public void move(int moves)
    // PRE:  moves > 0
    // POST: moves player forward "moves" spaces
    {
        position += moves;
        if(position >= 40)  // if positions moved outside of the array,
        {
            position %= 40;  // put it back on the board
            money += 200;  // add $200
        }
    }

    public void moveTo(int position)
    // PRE:  0 <= position < 40
    // POST: player's position == position
    {
        this.position = position;
    }

    public void changeMoney(int money)
    // PRE:  money is initialized
    // POST: players money is changed by "money" 
    {
        this.money += money;            
    }

    public void addProperty(Property property)
    // PRE:  property is initialized
    // POST: property is added to instances' property list
    {        
        Property[] arr;  // new list of properties for the player
        int i;  // iterator for array manipulation

        property.setOwner(this);
        arr = new Property[properties.length + 1];  // initialize list for one more property
        i = properties.length;

        if(property instanceof Lot)  // if property is a Lot
        {
            // while the end is a Railroad or Utility, move ahead
            while((i > 0) && 
            ((properties[i-1] instanceof Utility) || (properties[i-1] instanceof Railroad)) )
            {
                arr[i] = properties[i-1];
                --i;
            }
            // while the end is a larger position, move ahead
            while((i > 0) && 
            (properties[i-1].getPosition() > property.getPosition()))
            {
                arr[i] = properties[i-1];
                --i;
            }
        }
        else if(property instanceof Railroad)  // if property is a Railroad
        {
            // while the end is a Utility, move ahead
            while((i > 0) && 
            (properties[i-1] instanceof Utility))
            {
                arr[i] = properties[i-1];
                --i;
            }
            // while the end is a Railroad and is a larger position, move ahead
            while((i > 0) && 
            (properties[i-1] instanceof Railroad) &&
            (properties[i-1].getPosition() > property.getPosition()))
            {
                arr[i] = properties[i-1];
                --i;
            }
        }
        else // property must be a Utility
        {
            // while the end is a Utility and is a larger postion, move ahead
            while((i > 0) && 
            (properties[i-1] instanceof Utility) &&
            (properties[i-1].getPosition() > property.getPosition()))
            {
                arr[i] = properties[i-1];
                --i;
            }
        }

        arr[i] = property;  // put property where it belongs
        --i;

        // copy over the rest of the array
        while(i >= 0)
        {
            arr[i] = properties[i];
            --i;
        }

        properties = arr;  // point players properties to new list
    }

    public void removeProperty(Property property)
    // PRE:  property is initialized
    // POST: property is removed from instances' property list and its owner is set to null
    {
        int j;  // iterator for next element
        Property[] arr;  // new list of poperties for player

        // Don't try to remove properties that this player doesn't own
        if(property.getOwner() != this)
            return;

        property.setOwner(null); // reset the owner of the property
        arr = new Property[properties.length - 1];  // initialize list for one less property

        j = 0;
        for (int i = 0; i < properties.length; i++) // copies original properties into new list
        {
            if (property.getName() != properties[i].getName())  // if current instance isn't the 
            // the one we're searching for
            {
                arr[j] = properties[i];  // copy it
                j++;
            }
        }
        properties = arr;  // point players properties to new list
    }

    public String getName()
    // POST: FCTVAL == name of the player
    {
        return name;
    }

    public String toString()
    // POST: string representation of money, position, and properties
    {
        String s = "Name: " + name + ", Money: " + money + " Postition: " + position;
        s = s + " Properties: ";

        for (int i = 0; i < properties.length; i++)
        {
            s = s + properties[i].getName();
            if(i < (properties.length - 1))  // print a comma between all of the properties
                s = s + ", ";
        }
        return s;
    }

    public BufferedImage getToken()
    {
        return this.token;
    }

    public void setToken(String name)
    {
        try
        {
            this.token = ImageIO.read(new File(name));
        }
        catch(Exception e){}
    }

    public void renderInfoPanel(Graphics g, int x, int y, int width, int height)
    {
        if(this.money > 0)
        {
            // Draw background
            g.setColor(Color.WHITE);
            g.fillRect(x, y, width, height);

            // Draw token
            g.drawImage(this.token, x, y, height, height, null);

            // Draw name
            g.setColor(Color.BLACK);
            g.drawString(this.name, x + height + 5, y + 20);

            // Draw current money
            g.drawString("$" + this.money, x + height + 5, y + 40);

            // Draw outline        
            g.drawRect(x, y, width, height);     
        }   
        else
        {
            // Draw background
            g.setColor(Color.BLACK);
            g.fillRect(x, y, width, height);

            // Draw token
            g.drawImage(this.token, x, y, height, height, null);

            // Draw name
            g.setColor(Color.RED);
            g.drawString(this.name, x + height + 5, y + 20);

            // Draw current money
            g.drawString("$" + this.money, x + height + 5, y + 40);

            // Draw outline        
            g.drawRect(x, y, width, height);    
        }
    }

    public Lot[] getUpgradeableLots(Property[] properties)
    // PRE:  properties is intialized, and owned by a single owner
    // POST: FCTVAL a list of properties <owned by player> that can be upgraded
    {
        Lot[] retVal;       // the value to return;1
        Property[] lots;    // the lots the play can upgrade
        int i;              // iterator for the previous variable
        int j;              // iterator for the color
        int max;            // maximum upgarde count for color-group
        int min;            // minimum upgarde count for color-group

        lots = new Property[22];
        i = 0;
        j = 0;
        min = 0;
        max = 0;

        for(Property p : properties)
        {
            if(p instanceof Lot)  // if instance is a Lot
            {
                if(j == 0) // if no colors have been found
                {
                    if(p.getOwner().getMoney() >= ((Lot)p).getUpgradeCost())  // if player can afford upgrade
                    {
                        lots[i] = p;  // add to lots
                        ++i;  //incriment pointers
                        ++j;
                        min = ((Lot)p).getUpgradeCount();  // set max and min upgrade counts
                        max = min;
                    }
                    else  // if owner can't afford upgrade
                    {
                        break;  // they won't be able to afford anything else
                    }
                }
                else if(j == 1)  // if one color has been found
                {
                    if(p.getColor().equals(lots[i-1].getColor()))  // if current color matches previous color
                    {
                        if(min > ((Lot)p).getUpgradeCount())  // if there's a new minimum <upgarde count>
                        {
                            min = ((Lot)p).getUpgradeCount();  // set the new minimum
                            lots[i-1] = p;  // prev lot position can't be upgraded until this one is
                        }
                        else if (max < ((Lot)p).getUpgradeCount())  // if there's a new max <upgarde count>
                        {
                            max = ((Lot)p).getUpgradeCount();  // set it, but don't push value onto lots
                        }
                        else  // if there are no new max or min <upgarde counts>
                        {
                            lots[i] = p;  // add to lots
                            ++i;  // incriment pointers
                        }

                        // check if group has 3 or 2 members
                        if ((p.getPosition() > 3) && (p.getPosition() < 37))  // if a group of 3
                            ++j;  // let it search for the third color
                        else  // if it's a group of two
                        {
                            if((min == 5) && (i > 0)) // if they're fully upgraded, and still on lots
                            {
                                do  // remove them from lots
                                {
                                    --i;  
                                }
                                while((i > 0) && (((Lot)lots[i]).getUpgradeCount() == 5));
                            }
                            j = 0; // start search for new color
                        }
                    }
                    else  // if color didn't match prev
                    {
                        if(p.getOwner().getMoney() >= ((Lot)p).getUpgradeCost())  // if player can afford upgrade
                        {
                            lots[i-1] = p;  // prev lot wasn't complete group, overwrite it
                            min = ((Lot)p).getUpgradeCount();  // set the min and max <upgarde counts>
                            max = min;
                        }
                        else  // if the player can't afford an upgrade
                        {
                            break;  // they won't be able to afford anything else
                        }
                    }
                }
                else  //  (j == 2) if two colors have been found 
                {
                    if(p.getColor().equals(lots[i-1].getColor()))  // if current color matches prev color
                    {
                        if(min == max && min > ((Lot)p).getUpgradeCount())  // if current lot has a lower minimu <upgarde count>
                        {
                            min = ((Lot)p).getUpgradeCount();  // set the minimu
                            --i;  // decrease lots size by one
                            lots[i-1] = p;  // overwrite 1st of the group to be found
                        }
                        else if(min == ((Lot)p).getUpgradeCount())  // if current upgrade count equal to min
                        {
                            lots[i] = p;  // put on lots
                            ++i;  // increase lots size
                        }

                        if((min == 5) && (i > 0)) // and group is fully upgraded, and still on lots
                        {
                            do  //remove them from lots
                            {
                                --i;  
                            }
                            while((i > 0) && (((Lot)lots[i]).getUpgradeCount() == 5));
                        }
                        j = 0;  // start search for new color
                    }
                    else  // if color didn't match prev
                    {
                        if(p.getOwner().getMoney() >= ((Lot)p).getUpgradeCost())  // if player can afford upgrade
                        {
                            lots[i-2] = p;  // push it onto lots
                            --i;  // reduce lots size
                            j = 1;  // search with on color already found
                            min = ((Lot)p).getUpgradeCount();  // set min and max <upgarde count> values
                            max = min;
                        }
                        else  // if player can't afford upgrade
                        {
                            break;  // they won't be able to afford anything else
                        }
                    }
                }
            }
        }

        if(j == 1)  // if the last lot onto lots wasn't complete group
            --i;
        else if(j==2)  // if last lot onot lots wasn't complete group
            i -= 2;

        retVal = new Lot[i]; // create an exact size return array
        for(j=0 ; j<i ; ++j)  // copy values over
        {
            retVal[j] = (Lot)lots[j];
        }

        return retVal;
    }

    public void upgrade()
    // PRE:  player must be willing to upgrade his lot(s)
    // POST: 
    {
        Lot[] lots;  // the lots the play can upgrade
        String[] options;  // string of options
        int option; // the option chosen by the user
        String optionString;

        lots = this.getUpgradeableLots(properties);
        options = new String[lots.length];
        option = -1;
        
        if (options.length != 0)
        {
            for(int i = 0; i < options.length; i++)
            {
                options[i] = lots[i].getName() + " - $" + lots[i].getUpgradeCost();
            }
    
            optionString = (String)JOptionPane.showInputDialog(null, "Select a property to upgrade", "UPGRADE", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
    
            if(optionString != null)
            {
                for(int i = 0; i < lots.length; i++)
                {
                    String name = optionString.substring(0, lots[i].getName().length());
                    if(name.equals(lots[i].getName()))
                    {
                        option = i;
                    }
                }
    
                this.changeMoney(-1 * lots[option].getUpgradeCost());  // take their money
                lots[option].upgrade();
            }
        }
    }

    public Lot[] getDowngradeable(Property[] sellable)
    // PRE:  
    // POST: 
    {
        Property[] temp1;  // temporary array to find elements that aren't sellable
        int iT1;  // iterater for temp1  
        Property[] temp2;  // remporary array to find elements that aren't downgradable
        int iT2;  // iterater for temp2
        Color color;  // Color of the current group
        Lot[] retVal;  // returnable array
        int iS;  // iterator for the <incoming> sellable array
        int max;  // max and min upgrade counts
        int min;

        temp1 = new Property[this.getProperties().length];
        iT1 = 0;
        iS = 0;

        //if the players property isn't sellable, it must be downgradeable
        for(Property p : this.getProperties())
        {
            if((sellable.length != 0) && sellable[iS].getName() == p.getName())  // if an element of the sellable array, skip it
            {
                ++iS;
            }
            else
            {
                if(p instanceof Lot)  // there should only be Lots, but it doesn't hurt to make sure
                {
                    temp1[iT1] = p;
                    ++iT1;
                }
            }
       }

        temp2 = new Property[iT1];  // create 2nd temp array
        iT2 = 0;  // initialize needed elements
        min = 0;
        max = 0;
        color = Color.WHITE;  // set 

        for(int i=0 ; i<iT1 ; ++i)
        {
            if( !(color.equals(temp1[i].getColor())) )  // if we're in a new group
            {
                temp2[iT2] = temp1[i];
                ++iT2;
                color = temp1[i].getColor();
                max = ((Lot)temp1[i]).getUpgradeCount();
                min = max;
            }
            else if(min == max && max < ((Lot)temp1[i]).getUpgradeCount())  // if there's a new maximum upgrade count
            {
                max = ((Lot)temp1[i]).getUpgradeCount();  // set the new max value
                while(iT2 > 0 && color.equals(temp2[iT2].getColor()))  // remove any of the same group (they must have too low of an upgrade count)
                {
                    --iT2;
                }
                temp2[iT2] = temp1[i];  // push element onto temp2
                ++iT2;
            }
            else if(min == max && min > ((Lot)temp1[i]).getUpgradeCount())  // if there's a new minimum
            {
                min = ((Lot)temp1[i]).getUpgradeCount();  // set minimum
            }
            else if(max == ((Lot)temp1[i]).getUpgradeCount())  // if p has a high enough upgrade count
            {
                temp2[iT2] = temp1[i];
                ++iT2;
            }
            // else the element must have too high of an upgrade count (so don't include it)
        }

        retVal = new Lot[iT2];  // create the return array

        for(int j=0 ; j<iT2 ; ++j)  // fill the return array
        {
            retVal[j] = (Lot)temp2[j];
        }

        return retVal;
    }

    public Property[] getSellable()
    // PRE:  
    // POST: 
    {
        Property[] temp;  // temporary array to save properties to
        Property[] retVal;  // array to return
        Color bad;  // color of the group that isn't to be saved
        int i; // iterater for an array

        temp = new Property[this.getProperties().length];
        i = 0;
        bad = Color.WHITE;

        for(Property p : this.getProperties())  // go through players properties
        {
            if( !(p instanceof Lot) ) // if the property isn't a lot, include it
            {
                temp[i] = p;
                ++i;
            }
            else if(((Lot)p).getUpgradeCount() == 0)  // if the lot hasn't been upgrded, include it
            {
                if( !bad.equals(p.getColor()) )  // if one of the lot's group has been upgraded, don't include it
                {
                    temp[i] = p;
                    ++i;
                }
            }
            else  // else it's upgraded, 
            {
                bad = p.getColor();  // save off the color of the upgraded 

                while(i > 0 && bad.equals(temp[i].getColor()))  // remove any lots of the same color
                {
                    --i;
                }
            }
        }

        retVal = new Property[i];  // create an exact sized array
        for(int j=0 ; j<i ; ++j)  // copy over properties
        {
            retVal[j] = temp[j];
        }

        return retVal;
    }

    public void sell()
    // PRE:  
    // POST: 
    {
        String[] options;  // array of options
        Property[] sellable;
        Lot[] downgradeable;
        int option = 0;
        String optionString;

        sellable = getSellable();
        downgradeable = getDowngradeable(sellable);

        options = new String[] {"Sell Improvements", "Sell Properties"};
        option = JOptionPane.showOptionDialog(null, "Choose what to sell", "SELLING",JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE,null, options, options[0]);
        if(option == 0)
        {
            options = new String[downgradeable.length];
            if (options.length != 0)
            {
                for(int i=0 ; i<downgradeable.length ; ++i)
                {
                    options[i] = downgradeable[i].getName();
                }
                optionString = (String)JOptionPane.showInputDialog(null, "Select a property to downgrade", "SELlING UPGRADES", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                if(optionString != null)
                {
                    for(int i = 0; i < downgradeable.length; i++)
                    {
                        String name = optionString.substring(0, downgradeable[i].getName().length());
                        if(name.equals(downgradeable[i].getName()))
                        {
                            option = i;
                        }
                    }
    
                    downgradeable[option].downgrade();
                    this.changeMoney(downgradeable[option].getUpgradeCost()/2);
                    sellable = getSellable();
                    downgradeable = getDowngradeable(sellable);
                }
            }
        }
        else if (option == 1)
        {
            options = new String[sellable.length];
            if (options.length != 0)
            {
                for(int i=0 ; i<sellable.length ; ++i)
                {
                    options[i] = sellable[i].getName();
                }
                optionString = (String)JOptionPane.showInputDialog(null, "Select a property to sell", "SELlING PROPERTIES", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                if(optionString != null)
                {
                    for(int i = 0; i < sellable.length; i++)
                    {
                        String name = optionString.substring(0, sellable[i].getName().length());
                        if(name.equals(sellable[i].getName()))
                        {
                            option = i;
                        }
                    }
                    this.changeMoney(sellable[option].getCost());
                    this.removeProperty(sellable[option]);
                    sellable[option].setOwner(null);
                    sellable = getSellable();
                }
            }
        }
    }

    public void bankrupt(Player other)
    // PRE:  player must have spent too much money, and have lost the game
    // POST: the current player's property is handed over to other (the bank if other is null) 
    //       all of their money is handed over to other, and their money is set to 0
    {
        int money;  // self explainatory

        for(Property p : properties) // hand players properties over to owner
        {
            if(other != null)
            {
                other.addProperty(p);
            }
            else
            {
                p.setOwner(null);
            }

            if(p instanceof Lot)  // if the property is a lot, remove the upgrades
            {
                if(other != null)
                {
                    other.changeMoney(((Lot)p).getUpgradeCount() * ((Lot)p).getUpgradeCost());
                }

                ((Lot)p).setUpgradeCount(0);
            }
        }

        this.properties = new Property[0];
        this.money = 0;
    }
}

