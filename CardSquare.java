// Programmer:  Noah Angeles, Dennis McNamara, Tim Werkheiser, & Wenkan Zhu
// Assignment:  Project: Monopoly
// Date:        
// Description: Determines Go

public class CardSquare extends BoardLoc
{
    public CardSquare()
    // POST: 
    {
        super();
    }
    
///////////////////////////////////////////////////////////////////////////////////////////////////
    public CardSquare(String name, int position)
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
    // POST: see below
    {
        // get current player
        // give current player $$$$$
    }
}