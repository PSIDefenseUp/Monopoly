// Programmer:  Noah Angeles, Dennis McNamara, Tim Werkheiser, & Wenkan Zhu
// Assignment:  Project: Monopoly
// Date:        
// Description: 

public class CornerSquare extends BoardLoc
{
    public CornerSquare() 
    // POST: 
    {
        super();
    }
    
///////////////////////////////////////////////////////////////////////////////////////////////////
    public CornerSquare(String name, int position)
    // PRE:  
    // POST: 
    {
        super(name, position);
    }
        
///////////////////////////////////////////////////////////////////////////////////////////////////
    public int getRent()
    // POST: 
    {
        return 0;
    }
        
///////////////////////////////////////////////////////////////////////////////////////////////////
    public void onLand()
    // POST: 
    {
        return;
    }
}