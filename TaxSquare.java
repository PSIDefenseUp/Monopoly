// Programmer:  Noah Angeles, Dennis McNamara, Tim Werkheiser, & Wenkan Zhu
// Assignment:  Project: Monopoly
// Date:        
// Description: 

public class TaxSquare extends BoardLoc
{
    public TaxSquare()
    // PRE:  
    // POST: 
    {
        super();
    }
    
///////////////////////////////////////////////////////////////////////////////////////////////////
    public TaxSquare(String name, int position)
    // PRE:  
    // POST: 
    {
        super(name, position);
    }

///////////////////////////////////////////////////////////////////////////////////////////////////
    public ? payTax(Player player) // we can either return an amount, or take neccesary actions
    // PRE:  
    // POST: 
    {        
        // prompt user to select $200 or 10% of worth
        
        if(<$200>)  // if player chosee $200 and they don't have it
        {
            // return an int <to be handled elsewhere>
            // handle funciton here
            
        }
        else if(<10%>)  // if player chose 10% of worth
        {
            // calculate 10% of worth
            // if user has funds, take them
        }
    }
}