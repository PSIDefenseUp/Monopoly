// Programmer:  Noah Angeles, Dennis McNamara, Tim Werkheiser, & Wenkan Zhu
// Assignment:  Project: Monopoly
// Date:        
// Description: 


public class Player
{
    private int money;
    private int position;
    private int roll;
  //  private Image token;  // I don't know how to treat this
    private Property[] properties;  // List of properties owned by this player
    
///////////////////////////////////////////////////////////////////////////////////////////////////
    public Player()  
    // POST: Creates Player with money = $1500, position = 0, no properties
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
    // POST: roll = rolled
    {
        roll = rolled;
    }
    
///////////////////////////////////////////////////////////////////////////////////////////////////
  /*  public Image getToken()
    // POST: 
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
        if(position >= Monopoly.getBoardLength())  // if positions moved outside of the array,
        {
            position %= Monopoly.getBoardLength();  // put it back on the board

            money += 200;  // add $200
        }
    }
    
///////////////////////////////////////////////////////////////////////////////////////////////////
    public void moveTo(int position)
    // PRE:  0 <= position < 40
    // POST: players position = position
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
    // POST: property is added to player property list
    {
        Property[] arr = new Property[properties.length+1];
        for (int i = 0; i < properties.length; i++)
        {
            arr[i] = properties[i];
        }
        arr[properties.length] = property;
        properties = arr;
    }
    
///////////////////////////////////////////////////////////////////////////////////////////////////
    public void removeProperty(Property property)
    // PRE:  property is initialized
    // POST: property is removed from player property list
    {
        Property[] arr = new Property[properties.length-1];
        int j = 0;
        for (int i = 0; i < properties.length; i++)
        {
            if (property.getName() != properties[i].getName())
            {
                arr[j] = properties[i];
                j++;
            }
        }
        properties = arr;
    }
    
///////////////////////////////////////////////////////////////////////////////////////////////////
    public String toString()
    // POST: 
    {
        return "";
    }
}
