// Programmer:  Noah Angeles, Dennis McNamara, Tim Werkheiser, & Wenkan Zhu
// Assignment:  Project: Monopoly
// Date:        
// Description: 

import java.util.ArrayList;

public class Player
{
    private int money;
    private int position;
  //  private Image token;  // I don't know how to treat this
    private ArrayList<Property> properties;  // List of properties owned by this player
    
///////////////////////////////////////////////////////////////////////////////////////////////////
    public Player()  
    // POST: 
    {
        money = 1500;
        position = 0;
        properties = new ArrayList<Property>();
        //token = null;
    }
    
///////////////////////////////////////////////////////////////////////////////////////////////////
    public int getMoney()
    // POST: 
    {
        return money;
    }
    
///////////////////////////////////////////////////////////////////////////////////////////////////
  /*  public Image getToken()
    // POST: 
    {
        return token;
    }
  */  
///////////////////////////////////////////////////////////////////////////////////////////////////
    public ArrayList<Property> getProperties()
    // POST: FCTVAL == properties
    {
        return properties;
    }
    
///////////////////////////////////////////////////////////////////////////////////////////////////
    public int getPosition()
    // POST: 
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
        properties.add(property);
    }
    
///////////////////////////////////////////////////////////////////////////////////////////////////
    public void removeProperty(Property property)
    // PRE:  property is initialized
    // POST: property is removed from player property list
    {
        properties.remove(property);
    }
    
///////////////////////////////////////////////////////////////////////////////////////////////////
    public String toString()
    // POST: 
    {
        return "";
    }
}