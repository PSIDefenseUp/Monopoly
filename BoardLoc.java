// Programmer:  Noah Angeles, Dennis McNamara, Tim Werkheiser, & Wenkan Zhu
// Assignment:  Project: Monopoly
// Date:        
// Description: 

public abstract class BoardLoc
{
    protected int position;
    protected String name;
    
///////////////////////////////////////////////////////////////////////////////////////////////////
    public BoardLoc()
    // POST: 
    {
        position = 0;
        name = "";
    }
    
///////////////////////////////////////////////////////////////////////////////////////////////////
    public BoardLoc(String name, int position)
    // POST: 
    {
        this.name = "";
        this.position = position;
    }
    
///////////////////////////////////////////////////////////////////////////////////////////////////
    public String getName()
    // POST: 
    {
        return name;
    }
    
///////////////////////////////////////////////////////////////////////////////////////////////////
    public int getPosition()
    // POST: 
    {
        return position;
    }
    
    public abstract int getRent();
    public abstract void onLand();
}