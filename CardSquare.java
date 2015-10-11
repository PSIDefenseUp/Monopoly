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
        
///////////////////////////////////////////////////////////////////////////////////////////////////
    public String getPossibleActions(Player player)
    // PRE:  player is initialized
    // POST: FCTVAL = array of options player has upon landing on this space, 
    //       to be used in a menu in a user interface
    {
        return "Click to continue.";
    }
        
///////////////////////////////////////////////////////////////////////////////////////////////////
    public String toString()
    // POST: FCTVAL = a String of the name of the loacitons, and it's position from start
    {
        return name + ", Postion: " + position;
    }
}