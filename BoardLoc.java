// Programmer:  Noah Angeles, Dennis McNamara, Tim Werkheiser, & Wenkan Zhu
// Assignment:  Project: Monopoly
// Date:        
// Description: 

public class BoardLoc
{
    protected int position;
    protected String name;
    
///////////////////////////////////////////////////////////////////////////////////////////////////
    public BoardLoc()
    // PRE:  
    // POST: 
    {
        position = 0;
        name = "";
    }
    
///////////////////////////////////////////////////////////////////////////////////////////////////
    public BoardLoc(String name, int position)
    // PRE:  
    // POST: 
    {
        this.name = "";
        this.position = position;
    }
    
///////////////////////////////////////////////////////////////////////////////////////////////////
    public String getName()
    // PRE:  
    // POST: 
    {
        return name;
    }
    
///////////////////////////////////////////////////////////////////////////////////////////////////
    public int getPosition()
    // PRE:  
    // POST: 
    {
        return position;
    }
}