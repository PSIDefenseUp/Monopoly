// Programmer:  Noah Angeles, Dennis McNamara, Tim Werkheiser, & Wenkan Zhu
// Assignment:  Project: Monopoly
// Date: October 2015        
// Description: Class to represent the players in a Monopoly game


public class Player
{
    private int money;  // amount of money a Player Object has
    private int position;  // Objects position <on board> from GO
    private Property[] properties;  // List of properties owned by this player
    
    public Player()  
    // POST: Creates Player instance with money = $1500, position = 0, no properties
    {
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
        
        arr = new Property[properties.length + 1];  // initialize list for one more property
        
        for (int i = 0; i < properties.length; i++)  // copy over the properties
        {
            arr[i] = properties[i];
        }
        arr[properties.length] = property;  // add the newest property
        
        properties = arr;  // point players properties to new list
    }
    
    public void removeProperty(Property property)
    // PRE:  property is initialized
    // POST: property is removed from instances' property list
    {
        int j;  // iterator for next element
        Property[] arr;  // new list of poperties for player
        
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
    
    public String toString()
    // POST: string representation of money, position, and properties
    {
        String s = "Money: " + money + " Postition: " + position + " Properties: ";
        
        for (int i = 0; i < properties.length; i++)
        {
            s = s + properties[i].getName() + " ";
        }
        return s;
    }
}
