// Programmer:  Noah Angeles, Dennis McNamara, Tim Werkheiser, & Wenkan Zhu
// Assignment:  Project: Monopoly
// Date:        
// Description: Determines Go

public class CardSquare extends BoardLoc
{
    public CardSquare()
    // POST: CardSquare created without name or position
    {
        super();
    }
    
///////////////////////////////////////////////////////////////////////////////////////////////////
    public CardSquare(String name, int position)
    // PRE:  name == "Chance" || name == "Community Chest"
    // POST: CardSquare created with name = "Name" && position = "position"
    {
        super(name, position);
    }
        
///////////////////////////////////////////////////////////////////////////////////////////////////
    public int getRent()
    // POST: FCTVAL >= -200 && FCTVAL <= 200
    {
        if(Math.random() >= 0.5)
            return (int)(Math.random()*201);
        else
            return (-1)*(int)(Math.random()*201);
    }
        
///////////////////////////////////////////////////////////////////////////////////////////////////
    public void onLand()
    // POST: automatically charges or gives player appropriate amount of money
    {
        Player player;  // used to determine
        
        //player = ??;  // get the player
        
        //player.changeMoney(getRent());  // tax the player
    }
}