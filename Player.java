// Programmer:  Noah Angeles, Dennis McNamara, Tim Werkheiser, & Wenkan Zhu
// Assignment:  Project: Monopoly
// Date:        
// Description: 

public class Player
{
    private int money;
    private int position;
    private Image token  // I don't know how to treat this
    private Property properties[]  // I don't know how to treat this
    
///////////////////////////////////////////////////////////////////////////////////////////////////
    public Player()
    // PRE:  
    // POST: 
    {
        money = 1500;
        position = 0;
        properties = ;
        token = ;
    }
    
///////////////////////////////////////////////////////////////////////////////////////////////////
    public int getMoney()
    // PRE:  
    // POST: 
    {
        return money;
    }
    
///////////////////////////////////////////////////////////////////////////////////////////////////
    public Image getToken()
    // PRE:  
    // POST: 
    {
        return token;
    }
    
///////////////////////////////////////////////////////////////////////////////////////////////////
    public Property[] getProperties()
    // PRE:  
    // POST: 
    {
        ?
    }
    
///////////////////////////////////////////////////////////////////////////////////////////////////
    public int getPosition()
    // PRE:  
    // POST: 
    {
        return position;
    }
    
///////////////////////////////////////////////////////////////////////////////////////////////////
    public void move(int moves)
    // PRE:  
    // POST: 
    {
        positions += moves;
        if(positions >= 40)  // if positions moved outside of the array,
        {
            positions = position - 40;  // put it back on the board

            // do we want to add $200 here, or do we want to do it elsewhere
        }
    }
    
///////////////////////////////////////////////////////////////////////////////////////////////////
    public void moveTo(int position)
    // PRE:  
    // POST: 
    {
        this.position = position;
    }
    
///////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean changeMoney(int money)
    // PRE:  
    // POST: 
    {
        this.money += money;
        if(this.money >= 0)  // if the user has enough funds, return true
            return true;
        else  // if the user doesn't have enough funds
            this.money -= money;  // undo the damage, and return false
        return false;
            
    }
    
///////////////////////////////////////////////////////////////////////////////////////////////////
    public void addProperty(Property property)
    // PRE:  
    // POST: 
    {
        // is there an easy way of doing this (e.g., Vector class in C++)
        // or do we need to write all of the neccesay code
        ?
    }
    
///////////////////////////////////////////////////////////////////////////////////////////////////
    public void removeProperty(Property property)
    // PRE:  
    // POST: 
    {
        // Similar issue as addProperty function
        ?
    }
    
///////////////////////////////////////////////////////////////////////////////////////////////////
    public String toString()
    // PRE:  
    // POST: 
    {
        ?
    }
}