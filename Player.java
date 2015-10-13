// Programmer:  Noah Angeles, Dennis McNamara, Tim Werkheiser, & Wenkan Zhu
// Assignment:  Project: Monopoly
// Date:        
// Description: 


public class Player
{
    private int money;  // amount of money a Player Object has
    private int position;  // Objects position <on board> from GO
    private int roll;  // The total value <on the dice> the player rolled
  //  private Image token;  // To be implemented when the front end is made
    private Property[] properties;  // List of properties owned by this player
    
///////////////////////////////////////////////////////////////////////////////////////////////////
    public Player()  
    // POST: Creates Player instance with money = $1500, position = 0, no properties
    {
        money = 1500;
        position = 0;
        properties = new Property[0];
        //token = null;
    }
    
///////////////////////////////////////////////////////////////////////////////////////////////////
    public int getMoney()
    // POST: FCTVAL == money
    {
        return money;
    }
///////////////////////////////////////////////////////////////////////////////////////////////////    
    public int getRoll()
    // POST: FCTVAL == roll
    {
        return roll;
    }
    
///////////////////////////////////////////////////////////////////////////////////////////////////
    public void changeRoll(int rolled)
    // PRE:  1 <= rolled <= 12
    // POST: roll == rolled
    {
        roll = rolled;
    }
    
///////////////////////////////////////////////////////////////////////////////////////////////////
  /*  public Image getToken()
    // POST: FCTVAL == token
    {
        return token;
    }
  */  
///////////////////////////////////////////////////////////////////////////////////////////////////
    public Property[] getProperties()
    // POST: FCTVAL == properties
    {
        return properties;
    }
    
///////////////////////////////////////////////////////////////////////////////////////////////////
    public int getPosition()
    // POST: FCTVAL == position
    {
        return position;
    }
    
///////////////////////////////////////////////////////////////////////////////////////////////////
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
    
///////////////////////////////////////////////////////////////////////////////////////////////////
    public void moveTo(int position)
    // PRE:  0 <= position < 40
    // POST: player's position == position
    {
        this.position = position;
    }
    
///////////////////////////////////////////////////////////////////////////////////////////////////
    public void changeMoney(int money)
    // PRE:  money is initialized
    // POST: players money is changed by "money" 
    {
        this.money += money;            
    }
    
///////////////////////////////////////////////////////////////////////////////////////////////////
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
    
///////////////////////////////////////////////////////////////////////////////////////////////////
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
    
///////////////////////////////////////////////////////////////////////////////////////////////////
    public String toString()
    // POST: string representation of money, position, roll, and properties
    {
        String s = "Money: " + money + " Postition: " + position; 
        s += " Rolled: " + roll + " Properties: ";
        
        for (int i = 0; i < properties.length; i++)
        {
            s = s + properties[i].getName() + " ";
        }
        return s;
    }
}
