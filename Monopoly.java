// Programmer:  Noah Angeles, Dennis McNamara, Tim Werkheiser, & Wenkan Zhu
// Assignment:  Project: Monopoly
// Date:        October 2015
// Description: Main class containing all game elements

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import javax.swing.*;

public class Monopoly extends JApplet
{
    private static Player[] players;  // array of all players in game
    private static int currentPlayer; // the current player who has control
    private static BoardLoc[] board;  // array of all board locations in the game
    private static boolean gameOver;  // Whether or not the game is over
    private static int roll;          // total value of the dice roll
    private JButton[] buttons;        // buttons for choices
    private JPanel panel;             // panels for buttons
    
    public void init()
    // POST: Initializes all game elements, such as the board and players in the game
    {
        setLayout(new FlowLayout());
        
        // Set up board
        setBoard();
        
        // Initialize players
        players = new Player[2];
        for (int i = 0; i < players.length; i++)
        {
             players[i] = new Player("Player " + (i + 1));
        }
        // Prepare game for start
        currentPlayer = 0;
        gameOver = false;
        roll = 1;
        buttons = new JButton[1];
        buttons[0] = new JButton("Start");
        panel = new JPanel();
        panel.add(buttons[0]);
        add(panel);
    }
    
        @Override
    public void paint(Graphics g)                 // Display results
    {
        super.paint(g);
        drawBoard(g);
    }
    
        public void addButtons(String[] options)
    {
        remove(panel);
        panel = new JPanel();
        buttons = new JButton[options.length];
        for (int i = 0; i < options.length; i++)
        {
            buttons[i] = new JButton(options[i]);
            panel.add(buttons[i]);
        }
        add(panel);
        validate();
    }
    
    public void drawBoard(Graphics g)
    //PRE:  Applet size >= 600x550 must be a square with +50 in height for buttons
    //      g = object to draw on
    //POST: Draw a board representing the game
    {
        int width = getWidth();         // Width of applet
        int height = getHeight()-50;    // Height of applet - 30 for button space
        int startX = 0;                 // Start of board x
        int startY = 50;                // Start of board y
        int sizeSqr = width/11;         // Size of each square

        for (int i = 0; i < 40; i++)    // Drawing the board
        {
            if (i >= 0 && i < 10)
            {
                if (board[i] instanceof Lot)
                {
                    g.setColor(((Lot)board[i]).getColor());
                }
                g.drawRect(startX, startY, sizeSqr, sizeSqr);
                if (board[i].getName().length() > 5)
                {
                    g.drawString(board[i].getName().substring(0,5), startX+1, startY+sizeSqr/2);
                }
                else
                {
                    g.drawString(board[i].getName(), startX+1, startY+sizeSqr/2);
                }
                if (board[i] instanceof Property)
                {
                    g.drawString("$"+((Property)board[i]).getCost()+"", startX+1, startY+sizeSqr/4*3);
                }
                startX = startX + sizeSqr;
                g.setColor(Color.BLACK);
            }
            if (i >= 10 && i < 20)
            {
                if (board[i] instanceof Lot)
                {
                    g.setColor(((Lot)board[i]).getColor());
                }
                g.drawRect(startX, startY, sizeSqr, sizeSqr);
                if (board[i].getName().length() > 5)
                {
                    g.drawString(board[i].getName().substring(0,5), startX+1, startY+sizeSqr/2);
                }
                else
                {
                    g.drawString(board[i].getName(), startX+1, startY+sizeSqr/2);
                }
                if (board[i] instanceof Property)
                {
                    g.drawString("$"+((Property)board[i]).getCost()+"", startX+1, startY+sizeSqr/4*3);
                }
                startY = startY + sizeSqr;
                g.setColor(Color.BLACK);
            }
            if (i >= 20 && i < 30)
            {
                if (board[i] instanceof Lot)
                {
                    g.setColor(((Lot)board[i]).getColor());
                }
                g.drawRect(startX, startY, sizeSqr, sizeSqr);
                if (board[i].getName().length() > 5)
                {
                    g.drawString(board[i].getName().substring(0,5), startX+1, startY+sizeSqr/2);
                }
                else
                {
                    g.drawString(board[i].getName(), startX+1, startY+sizeSqr/2);
                }
                if (board[i] instanceof Property)
                {
                    g.drawString("$"+((Property)board[i]).getCost()+"", startX+1, startY+sizeSqr/4*3);
                }
                startX = startX - sizeSqr;
                g.setColor(Color.BLACK);
            }
            if (i >= 30 && i < 40)
            {
                if (board[i] instanceof Lot)
                {
                    g.setColor(((Lot)board[i]).getColor());
                }
                g.drawRect(startX, startY, sizeSqr, sizeSqr);
                if (board[i].getName().length() > 5)
                {
                    g.drawString(board[i].getName().substring(0,5), startX+1, startY+sizeSqr/2);
                }
                else
                {
                    g.drawString(board[i].getName(), startX+1, startY+sizeSqr/2);
                }
                if (board[i] instanceof Property)
                {
                    g.drawString("$"+((Property)board[i]).getCost()+"", startX+1, startY+sizeSqr/4*3);
                }
                startY = startY - sizeSqr;
                g.setColor(Color.BLACK);
            }
        }
    }
    
    public static void init2()
    // POST: Initializes all game elements, such as the board and players in the game
    {
        // Set up board
        setBoard();
        
        // Initialize players
        players = new Player[2];
        for (int i = 0; i < players.length; i++)
        {
             players[i] = new Player("Player " + (i + 1));
        }
        
        Color purple; // the color purple          
        purple = new Color(102, 51, 153);  // Initialize the color of purple
        
        // Give player 1 some properties
        Property prop = new Lot("MEDITERRANEAN AVE", 1, 60, purple, 
                           50, new int[]{2, 10, 30, 90, 160, 230});
        Property prop2 = new Lot("BALTIC AVE", 2, 60, purple, 
                           50, new int[]{4, 20, 60, 180, 320, 460});
        Property prop3 = new Railroad("READING RAILROAD", 5, 200);
        prop.setOwner(players[0]);
        prop2.setOwner(players[0]);
        prop3.setOwner(players[0]);
        players[0].addProperty(prop);
        players[0].addProperty(prop2);
        players[0].addProperty(prop3);
        board[1] = prop;
        board[3] = prop2;
        board[5] = prop3;
        
        // Give player 2 some properties
        prop = new Lot("VERMONT AVE.", 8, 100, Color.CYAN, 
                           50, new int[]{6, 30, 90, 270, 400, 550});
        prop2 = new Lot("CONNECTICUT AVE.", 9, 120, Color.CYAN, 
                           50, new int[]{8, 40, 100, 300, 450, 600});
        prop3 = new Lot("ST. CHARLES PLACE", 11, 140, Color.MAGENTA, 
                            100, new int[]{10, 50, 150, 450, 625, 750});
        prop.setOwner(players[1]);
        prop2.setOwner(players[1]);
        prop3.setOwner(players[1]);
        players[1].addProperty(prop);
        players[1].addProperty(prop2);
        players[1].addProperty(prop3);
        board[8] = prop;
        board[9] = prop2;
        board[11] = prop3;
        
        // Prepare game for start
        currentPlayer = 0;
        gameOver = false;
        roll = 1;
    }
    
    public static void setBoard()
    {   
        Color purple; // the color purple
            
        purple = new Color(102, 51, 153);  // Initialize the color of purple
        board = new BoardLoc[40];  // Initialize array of board locations
                
        // Implement board spaces      
        board[0] = new CornerSquare("GO", 0);
        board[1] = new Lot("MEDITERRANEAN AVE", 1, 60, purple, 
                           50, new int[]{2, 10, 30, 90, 160, 230});
        board[2] = new CardSquare("Community Chest", 2);
        board[3] = new Lot("BALTIC AVE", 2, 60, purple, 
                           50, new int[]{4, 20, 60, 180, 320, 460});
        board[4] = new TaxSquare("Income Tax", 4);
        board[5] = new Railroad("READING RAILROAD", 5, 200);
        board[6] = new Lot("ORIENTAL AVE.", 6, 100, Color.CYAN,
                           50, new int[]{6, 30, 90, 270, 400, 550});
        board[7] = new CardSquare("Chance", 7);
        board[8] = new Lot("VERMONT AVE.", 8, 100, Color.CYAN, 
                           50, new int[]{6, 30, 90, 270, 400, 550});
        board[9] = new Lot("CONNECTICUT AVE.", 9, 120, Color.CYAN, 
                           50, new int[]{8, 40, 100, 300, 450, 600});
        board[10] = new CornerSquare("Jail", 10);
        board[11] = new Lot("ST. CHARLES PLACE", 11, 140, Color.MAGENTA, 
                            100, new int[]{10, 50, 150, 450, 625, 750});
        board[12] = new Utility("ELECTRIC COMPANY", 12, 150);
        board[13] = new Lot("STATES AVE.", 13, 140, Color.MAGENTA, 
                            100, new int[]{10, 50, 150, 450, 625, 750});
        board[14] = new Lot("VIRGINIA AVE.", 14, 160, Color.MAGENTA, 
                            100, new int[]{12, 60, 180, 500, 700, 900});
        board[15] = new Railroad("PENNSYLVANIA RAILROAD", 15, 200);
        board[16] = new Lot("ST. JAMES PLACE", 16, 180, Color.ORANGE, 
                            100, new int[]{14, 70, 200, 550, 750, 950});
        board[17] = new CardSquare("Community Chest", 17);
        board[18] = new Lot("TENNESSEE AVE.", 18, 180, Color.ORANGE, 
                            100, new int[]{14, 70, 200, 550, 750, 950});
        board[19] = new Lot("NEW YORK AVE.", 19, 200, Color.ORANGE, 
                            100, new int[]{16, 80, 220, 600, 800, 1000});
        board[20] = new CornerSquare("Free Parking", 20);
        board[21] = new Lot("KENTUCKY AVE.", 21, 220, Color.RED, 
                            150, new int[]{18, 90, 250, 700, 875, 1050});
        board[22] = new CardSquare("Chance", 22);
        board[23] = new Lot("INDIANA AVE.", 23, 220, Color.RED, 
                            150, new int[]{18, 90, 250, 700, 875, 1050});
        board[24] = new Lot("ILLINOIS AVE.", 24, 240, Color.RED, 
                            150, new int[]{20, 100, 300, 750, 925, 1100});
        board[25] = new Railroad("B & O RAILROAD", 25, 200);
        board[26] = new Lot("ATLANTIC AVE.", 26, 260, Color.YELLOW, 
                            150, new int[]{22, 110, 330, 800, 975, 1150});
        board[27] = new Lot("VENTNOR AVE.", 27, 260, Color.YELLOW, 
                            150, new int[]{22, 110, 330, 800, 975, 1150});
        board[28] = new Utility("WATER WORKS", 28, 150);
        board[29] = new Lot("MARVIN GARDENS", 29, 280, Color.YELLOW, 
                            150, new int[]{24, 120, 360, 850, 1025, 1200});
        board[30] = new CornerSquare("Go To Jail", 30);
        board[31] = new Lot("PACIFIC AVE.", 31, 300, Color.GREEN, 
                            200, new int[]{26, 130, 390, 900, 1100, 1275});
        board[32] = new Lot("NO. CAROLINA AVE.", 32, 300, Color.GREEN, 
                            200, new int[]{26, 130, 390, 900, 1100, 1275});
        board[33] = new CardSquare("Community Chest", 33);
        board[34] = new Lot("PENNSYLVANIA AVE.", 34, 320, Color.GREEN, 
                            200, new int[]{28, 150, 450, 1000, 1200, 1400});
        board[35] = new Railroad("SHORT LINE RAILROAD", 35, 200);
        board[36] = new CardSquare("Chance", 36);
        board[37] = new Lot("PARK PLACE", 37, 350, Color.BLUE, 
                            200, new int[]{35, 175, 500, 1100, 1300, 1500});
        board[38] = new TaxSquare("Luxury Tax", 38);
        board[39] = new Lot("BOARDWALK", 39, 400, Color.BLUE, 
                            200, new int[]{50, 200, 600, 1400, 1700, 2000});
    }
    
    public static void main(String[] args)
    {
        int actionOptions;  //  Actions the player can take during turn

        //init();  // initiaize new game 
        init2();  // initialize game with players having properties

        // Checking if board initialized correctly
        for (int i = 0; i < board.length; i++)
        {
            System.out.println(board[i].toString());
        }

        // Starting game
        System.out.println();
        System.out.println("Starting Game...");
        currentPlayer = (int) Math.random() * players.length;  // Random first player
        
        while(!gameOver)  // continue game while it isn't over
        {            
            // If current player is still in the game
            //     Let current player take an action or roll
            //     After roll, resolve landing on the player's new location
            //     ?? Check if player is bankrupt ??
            // Increment currentPlayer to progress to next player's turn
           // System.out.println("Player " + (currentPlayer+1) + "'s turn.");
          //  System.out.println(players[currentPlayer].toString());
            
            if(players[currentPlayer].getMoney() >= 0) // if player is still in game
            {
                String[] performAction;  // string of actions the Player can perform
                
                performAction = new String[] {"Roll dice", "Upgrade a property", "Sell a property"};
                
                // Get player's action of choice until they decide to roll the dice
                do
                {
                    System.out.println("Player " + (currentPlayer+1) + "'s turn.");
                    System.out.println(players[currentPlayer].toString());
                    
                    actionOptions = ActionsMenu.runActionsMenu(performAction);
                    switch(actionOptions)
                    {
                        case 1: upgrade();
                                break; 
                        case 2: sell();
                                break;
                        default: // Continue to dice roll
                    }
                }
                while(actionOptions != 0);

                // Roll two dice
                roll = ((int)(Math.random() * 6) + 1) + ((int)(Math.random() * 6) + 1);
                System.out.println("Roll: " + roll);
                // Move the player
                players[currentPlayer].move(roll);
                 // Run onLand for the player's new position
                board[players[currentPlayer].getPosition()].onLand(players[currentPlayer]);

                if(players[currentPlayer].getMoney() < 0)
                {
                    bankruptcy();
                    System.out.println(" You've lost the game, you're bankrupt");
                }
                
                System.out.println();
            }

            // Move to the next player's turn
            currentPlayer = (currentPlayer + 1) % players.length; 
        }
    }
    
    public static Lot[] getUpgradeableLots(Property[] properties)
    // PRE:  properties is intialized, and owned by a single owner
    // POST: FCTVAL a list of properties <owned by player> that can be upgraded
    {
        Lot[] retVal;  // the value to return;1
        Property[] lots;  // the lots the play can upgrade
        int i;  // iterator for the previous variable
        int j;  // iterator for the color
        int max;  // maximum upgarde count for color-group
        int min;  // minimum upgarde count for color-group
        
        lots = new Property[22];
        i = 0;
        j = 0;
        min = 0;
        max = 0;
        
        for(Property p : properties)
        {
            if(p instanceof Lot)  // if instance is a Lot
            {
                if(j == 0) // if no colors have been found
                {
                    if(p.getOwner().getMoney() >= ((Lot)p).getUpgradeCost())  // if player can afford upgrade
                    {
                        lots[i] = p;  // add to lots
                        ++i;  //incriment pointers
                        ++j;
                        min = ((Lot)p).getUpgradeCount();  // set max and min upgrade counts
                        max = min;
                    }
                    else  // if owner can't afford upgrade
                    {
                        break;  // they won't be able to afford anything else
                    }
                }
                else if(j == 1)  // if one color has been found
                {
                    if(p.getColor().equals(lots[i-1].getColor()))  // if current color matches previous color
                    {
                        if(min > ((Lot)p).getUpgradeCount())  // if there's a new minimum <upgarde count>
                        {
                            min = ((Lot)p).getUpgradeCount();  // set the new minimum
                            lots[i-1] = p;  // prev lot position can't be upgraded until this one is
                        }
                        else if (max < ((Lot)p).getUpgradeCount())  // if there's a new max <upgarde count>
                        {
                            max = ((Lot)p).getUpgradeCount();  // set it, but don't push value onto lots
                        }
                        else  // if there are no new max or min <upgarde counts>
                        {
                            lots[i] = p;  // add to lots
                            ++i;  // incriment pointers
                        }
                        
                        // check if group has 3 or 2 members
                        if ((p.getPosition() > 3) && (p.getPosition() < 37))  // if a group of 3
                            ++j;  // let it search for the third color
                        else  // if it's a group of two
                        {
                            if((min == 5) && (i > 0)) // if they're fully upgraded, and still on lots
                            {
                                do  // remove them from lots
                                {
                                    --i;  
                                }
                                while((i > 0) && (((Lot)lots[i]).getUpgradeCount() == 5));
                            }
                            j = 0; // start search for new color
                        }
                    }
                    else  // if color didn't match prev
                    {
                        if(p.getOwner().getMoney() >= ((Lot)p).getUpgradeCost())  // if player can afford upgrade
                        {
                            lots[i-1] = p;  // prev lot wasn't complete group, overwrite it
                            min = ((Lot)p).getUpgradeCount();  // set the min and max <upgarde counts>
                            max = min;
                        }
                        else  // if the player can't afford an upgrade
                        {
                            break;  // they won't be able to afford anything else
                        }
                    }
                }
                else  //  (j == 2) if two colors have been found 
                {
                    if(p.getColor().equals(lots[i-1].getColor()))  // if current color matches prev color
                    {
                        if(min == max && min > ((Lot)p).getUpgradeCount())  // if current lot has a lower minimu <upgarde count>
                        {
                            min = ((Lot)p).getUpgradeCount();  // set the minimu
                            --i;  // decrease lots size by one
                            lots[i-1] = p;  // overwrite 1st of the group to be found
                        }
                        else if(min == ((Lot)p).getUpgradeCount())  // if current upgrade count equal to min
                        {
                            lots[i] = p;  // put on lots
                            ++i;  // increase lots size
                        }
                        
                        if((min == 5) && (i > 0)) // and group is fully upgraded, and still on lots
                            {
                                do  //remove them from lots
                                {
                                    --i;  
                                }
                                while((i > 0) && (((Lot)lots[i]).getUpgradeCount() == 5));
                            }
                        j = 0;  // start search for new color
                    }
                    else  // if color didn't match prev
                    {
                        if(p.getOwner().getMoney() >= ((Lot)p).getUpgradeCost())  // if player can afford upgrade
                        {
                            lots[i-2] = p;  // push it onto lots
                            --i;  // reduce lots size
                            j = 1;  // search with on color already found
                            min = ((Lot)p).getUpgradeCount();  // set min and max <upgarde count> values
                            max = min;
                        }
                        else  // if player can't afford upgrade
                        {
                            break;  // they won't be able to afford anything else
                        }
                    }
                }
            }
        }
        
        if(j == 1)  // if the last lot onto lots wasn't complete group
            --i;
        else if(j==2)  // if last lot onot lots wasn't complete group
            i -= 2;
        
        retVal = new Lot[i]; // create an exact size return array
        for(j=0 ; j<i ; ++j)  // copy values over
        {
            retVal[j] = (Lot)lots[j];
        }
        
        return retVal;
    }
    
    public static void upgrade()
    // PRE:  player must be willing to upgrade his lot(s)
    // POST: 
    {
        Player current;  // the current player
        Lot[] lots;  // the lots the play can upgrade
        int option;  // variable for choosing what to do
        String[] options;  // string of options
        
        current = players[currentPlayer];
        
        do
        {
            lots = getUpgradeableLots(current.getProperties());  // get a list of possible choice
            options = new String[lots.length + 1];  // get array of possible upgardes
            options[0] = "Return";
            for(int i=1 ; i<=lots.length ; ++i)  // add upgardes to options array
            {
                options[i] = "Upgrade " + lots[i-1].getName();
            }
            
            option = ActionsMenu.runActionsMenu(options);  // ask user for upgrade
            
            if(option != 0) // if player chose to upgrade
            {
                current.changeMoney(-1 * lots[option-1].getUpgradeCost());  // take their money
                lots[option-1].upgrade();
            }
        }
        while(option != 0);
    }
    
    public static Lot[] getDowngradeable(Property[] sellable)
    // PRE:  
    // POST: 
    {
        Property[] temp1;  // temporary array to find elements that aren't sellable
        int iT1;  // iterater for temp1  
        Property[] temp2;  // remporary array to find elements that aren't downgradable
        int iT2;  // iterater for temp2
        Color color;  // Color of the current group
        Lot[] retVal;  // returnable array
        int iS;  // iterator for the <incoming> sellable array
        int max;  // max and min upgrade counts
        int min;
        
        temp1 = new Property[players[currentPlayer].getProperties().length];
        iT1 = 0;
        iS = 0;
            
        //if the players property isn't sellable, it must be downgradeable
        for(Property p : players[currentPlayer].getProperties())
        {
            if(sellable[iS].getName() == p.getName())  // if an element of the sellable array, skip it
            {
                ++iS;
            }
            else
            {
                if(p instanceof Lot)  // there should only be Lots, but it doesn't hurt to make sure
                {
                    temp1[iT1] = p;
                    ++iT1;
                }
            }
        }
        
        temp2 = new Property[iT1];  // create 2nd temp array
        iT2 = 0;  // initialize needed elements
        min = 0;
        max = 0;
        color = Color.WHITE;  // set 
        
        for(int i=0 ; i<iT1 ; ++i)
        {
            if( !(color.equals(temp1[i].getColor())) )  // if we're in a new group
            {
                temp2[iT2] = temp1[i];
                ++iT2;
                color = temp1[i].getColor();
                max = ((Lot)temp1[i]).getUpgradeCount();
                min = max;
            }
            else if(min == max && max < ((Lot)temp1[i]).getUpgradeCount())  // if there's a new maximum upgrade count
            {
                max = ((Lot)temp1[i]).getUpgradeCount();  // set the new max value
                while(iT2 > 0 && color.equals(temp2[iT2].getColor()))  // remove any of the same group (they must have too low of an upgrade count)
                {
                    --iT2;
                }
                temp2[iT2] = temp1[i];  // push element onto temp2
                ++iT2;
            }
            else if(min == max && min > ((Lot)temp1[i]).getUpgradeCount())  // if there's a new minimum
            {
                min = ((Lot)temp1[i]).getUpgradeCount();  // set minimum
            }
            else if(max == ((Lot)temp1[i]).getUpgradeCount())  // if p has a high enough upgrade count
            {
                temp2[iT2] = temp1[i];
                ++iT2;
            }
            // else the element must have too high of an upgrade count (so don't include it)
        }
        
        retVal = new Lot[iT2];  // create the return array
        
        for(int j=0 ; j<iT2 ; ++j)  // fill the return array
        {
            retVal[j] = (Lot)temp2[j];
        }
        
        return retVal;
    }
    
    public static Property[] getSellable()
    // PRE:  
    // POST: 
    {
        Property[] temp;  // temporary array to save properties to
        Property[] retVal;  // array to return
        Color bad;  // color of the group that isn't to be saved
        int i; // iterater for an array
        
        temp = new Property[players[currentPlayer].getProperties().length];
        i = 0;
        bad = Color.WHITE;
        
        for(Property p : players[currentPlayer].getProperties())  // go through players properties
        {
            if( !(p instanceof Lot) ) // if the property isn't a lot, include it
            {
                temp[i] = p;
                ++i;
            }
            else if(((Lot)p).getUpgradeCount() == 0)  // if the lot hasn't been upgrded, include it
            {
                if( !bad.equals(p.getColor()) )  // if one of the lot's group has been upgraded, don't include it
                {
                    temp[i] = p;
                    ++i;
                }
            }
            else  // else it's upgraded, 
            {
                bad = p.getColor();  // save off the color of the upgraded 
                
                while(i > 0 && bad.equals(temp[i].getColor()))  // remove any lots of the same color
                {
                    --i;
                }
            }
        }
        
        retVal = new Property[i];  // create an exact sized array
        for(int j=0 ; j<i ; ++j)  // copy over properties
        {
            retVal[j] = temp[j];
        }
        
        return retVal;
    }
        
    public static void sell()
    // PRE:  
    // POST: 
    {
        Property[] sellable;  // array for sellable properties
        Lot[] downgradeable;  // array for downgradeable properties
        String[] options;  // array of options
        int option;  // flag that shows which option the user chose
        
        sellable = getSellable();
        downgradeable = getDowngradeable(sellable);
        
        do
        {
            options = new String[] {"Return", "Sell Improvements", "Sell Properties"};
            option = ActionsMenu.runActionsMenu(options);
            
            if(option == 0)
                return;
            else if(option == 1)
            {
                do
                {
                    options = new String[downgradeable.length + 1];
                    options[0] = "Return";
                    for(int i=0 ; i<downgradeable.length ; ++i)
                    {
                        options[i+1] = "Downgrade " + downgradeable[i].getName();
                    }
                    option = ActionsMenu.runActionsMenu(options);
                    if( option != 0)
                    {
                        downgradeable[option-1].downgrade();
                        players[currentPlayer].changeMoney(downgradeable[option-1].getUpgradeCost()/2);
                        sellable = getSellable();
                        downgradeable = getDowngradeable(sellable);
                    }
                }
                while(option != 0);
            }
            else if (option == 2)
            {
                do
                {
                    options = new String[sellable.length + 1];
                    options[0] = "Return";
                    for(int i=0 ; i<sellable.length ; ++i)
                    {
                        options[i+1] = "Sell " + sellable[i].getName();
                    }
                    option = ActionsMenu.runActionsMenu(options);
                    if( option != 0)
                    {
                        players[currentPlayer].changeMoney(sellable[option-1].getCost());
                        players[currentPlayer].removeProperty(sellable[option-1]);
                        sellable[option-1].setOwner(null);
                        sellable = getSellable();
                    }
                }
                while(option != 0);
            }
        }
        while(true);
    }
    
    public static void bankruptcy()
    // PRE:  player must have spent too much money, and have lost the game
    // POST: the current player's property is handed over the the owner of the position, 
    //       and all of their money is handed over the the owner, and their money is set to -1
    {
        Player current;  // current player
        Player owner;  // owner of the position <that the player is located on>
        Property[] properties; // list of properties owned by current;
        int money;  // self explainatory
        
        current = players[currentPlayer];  // set the current player
        properties = current.getProperties();
        
        // set the owner
        if(board[current.getPosition()] instanceof Property)  // if a property
            owner = ((Property) board[current.getPosition()]).getOwner();  
        else  // if not a property
            owner = null;
        
        for(Property p : current.getProperties()) // hand players properties over to owner
        {
            if(p instanceof Lot)  // if the property is a lot, remove the upgrades
            {
                for(int j=((Lot)p).getUpgradeCount() ; j!=0 ; --j)  // remove upgrades
                {
                    current.changeMoney(((Lot)p).getUpgradeCost() / 2);
                    ((Lot)p).downgrade();
                }
            }
            
            current.removeProperty(p);
            if(owner != null)  // if there is any owner
                owner.addProperty(p);
            p.setOwner(owner);  // transfer ownership
        }
        
        money = current.getMoney();
        if(owner != null)  // if there is an owner, set his money to what it actually would be
            owner.changeMoney(money);
        current.changeMoney((-1 * money) - 1);  // set players money to -1
    }
   
    public static Player getCurrentPlayer()
    // POST: FCTVAL = Current Player instance
    {
        return players[currentPlayer];
    }
    
    public static Player getPlayer(int playerID)
    // PRE:  playerID >= 0 
    // POST: FCTVAL = Player instance
    {
        return players[playerID];
    }

    public static int getBoardLength()
    // POST: FCTVAL = number of positions on board
    {
        return board.length;
    }

    public static int getCurrentRoll()
    // POST: FCTVAL = amount of dice roll
    {
        return roll;
    }
}
