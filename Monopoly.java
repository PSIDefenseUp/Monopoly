// Programmer:  Noah Angeles, Dennis McNamara, Tim Werkheiser, & Wenkan Zhu
// Assignment:  Project: Monopoly
// Date:        October 2015
// Description: Main class containing all game elements

import java.awt.Color;

public class Monopoly 
{
    private static Player[] players;  // array of all players in game
    private static int currentPlayer; // the current player who has control
    private static BoardLoc[] board;  // array of all board locations in the game
    private static boolean gameOver;  // Whether or not the game is over
    private static int roll;          // total value of the dice roll
    
    public static void init()
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
        // Prepare game for start
        currentPlayer = 0;
        gameOver = false;
        roll = 1;
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
            System.out.println("Player " + (currentPlayer+1) + "'s turn.");
            System.out.println(players[currentPlayer].toString());
            
            if(players[currentPlayer].getMoney() >= 0) // if player is still in game
            {
                String[] performAction;  // string of actions the Player can perform
                
                performAction = new String[] {"Roll dice", "Upgrade a property", "Sell a property"};
                
                // Get player's action of choice until they decide to roll the dice
                do
                {
                    actionOptions = ActionsMenu.runActionsMenu(performAction);
                    switch(actionOptions)
                    {
                        case 1: upgrade();
                                break; // TODO: Give user a list of their properties to updgrade 
                                       // (if they have the money and the property cab be upgraded)
                        case 2: break; // TODO: Give user a list of their properties + sell values,
                                       // ask which is to be sold (handle in its own method)
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
        
        for(int i=0 ; i < properties.length ; ++i)  // hand players properties over to owner
        {
            if(properties[i] instanceof Lot)  // if the property is a lot, remove the upgrades
            {
                for(int j=((Lot)properties[i]).getUpgradeCount() ; j!=0 ; --j)  // remove upgrades
                {
                    current.changeMoney(((Lot)properties[i]).getUpgradeCost() / 2);
                    ((Lot)properties[i]).downgrade();
                }
            }
            
            current.removeProperty(properties[i]);
            properties[i].setOwner(owner);  // transfer ownership
            if(owner != null)  // if there is any owner
                owner.addProperty(properties[i]);
        }
        
        money = current.getMoney();
        if(money > 0 && owner != null)  // if player has money left, transfer what remains
            owner.changeMoney(money);
        else if (money < 0 && owner != null)  // if owner took too much, remove some
            owner.changeMoney(-1 * money);
        current.changeMoney((-1 * money) - 1);  // set players money to -1
    }
    
    public static Lot[] limitProps(Property[] properties)
    // PRE:  properties is intialized, and owned by a single owner
    // POST: FCTVAL a list of properties <owned by player> that can be upgraded
    {
        Lot[] retVal;  // the value to return;1
        Property[] lots;  // the lots the play can upgrade
        int i;  // iterator for the previous variable
        int j;  // iterator for the color
        int max;
        int min;
        
        lots = new Property[22];
        i = 0;
        j = 0;
        min = 0;
        max = 0;
        
        for(Property p : properties)
        {
            if(p instanceof Lot)
            {
                if(j == 0) // if first color found
                {
                    if(p.getOwner().getMoney() >= ((Lot)p).getUpgradeCost())  // if player can afford upgrade
                    {
                        lots[i] = p;  // add to lots, and incriment pointers
                        ++i;
                        ++j;
                        min = ((Lot)p).getUpgradeCount();
                        max = min;
                    }
                    else
                        break;  // they won't be able to afford anything else
                }
                else if(j == 1)  // if the second color found
                {
                    if(p.getColor().equals(lots[i-1].getColor()))  // if it matches prev color
                    {
                        if(min > ((Lot)p).getUpgradeCount())  // if there's a new minimum
                        {
                            min = ((Lot)p).getUpgradeCount();  // set the new minimum
                            lots[i-1] = p;  // overwrite old minimum with new
                        }
                        else if (max < ((Lot)p).getUpgradeCount())  // if there's a new max
                        {
                            max = ((Lot)p).getUpgradeCount();  // set it
                        }
                        else
                        {
                            lots[i] = p;  // add to lots, and incriment pointers
                            ++i;
                        }
                        if ((p.getPosition() > 3) && (p.getPosition() < 37))  // if a group of 3
                            ++j;  // let it search for the third color
                        else  // if it's a group of two
                        {
                            if((min == 5) && (i > 0)) // and they're fully upgraded, and can reduce
                            {
                                do
                                {
                                    --i;
                                }
                                while((i > 0) && (((Lot)lots[i]).getUpgradeCount() == 5));
                            }
                            j = 0; // start new search
                        }
                    }
                    else  // if it didn't match prev
                    {
                        if(p.getOwner().getMoney() >= ((Lot)p).getUpgradeCost())  // if playercan afford upgrade
                        {
                            lots[i-1] = p;
                            min = ((Lot)p).getUpgradeCount();
                            max = min;
                        }
                        else
                            break;  // they won't be able to afford anything else
                    }
                }
                else  // if third color found
                {
                    if(p.getColor().equals(lots[i-1].getColor()))  // if color matches prev
                    {
                        if(min == max && min > ((Lot)p).getUpgradeCount())  // if new min
                        {
                            --i;  // set iterator back one
                            lots[i-1] = p;  // overwrite 1st value
                        }
                        else if(min == ((Lot)p).getUpgradeCount())  // if equal to min
                        {
                            lots[i] = p;  // mark it and inriment pointer,
                            ++i;
                        }
                        
                        if((min == 5) && (i > 0)) // and they're fully upgraded, and can reduce
                            {
                                do  // reduce i
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
                            lots[i-2] = p;
                            --i;  // start new search
                            j = 1;
                            min = ((Lot)p).getUpgradeCount();
                            max = min;
                        }
                        else
                            break;  // they won't be able to afford anything else
                    }
                }
                    
            }
        }
        if(j == 1)
            --i;
        else if(j==2)
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
        
        option = 0;  // not needed when following is implemented
        do
        {
            lots = limitProps(current.getProperties());  // get a list of possible choices
            
            // make an option string
            options = new String[lots.length + 1];
            options[0] = "Return";
            for(int i=1 ; i<=lots.length ; ++i)
            {
                options[i] = "Upgrade " + lots[i-1].getName();
            }
            
            option = ActionsMenu.runActionsMenu(options);
            
            if(option != 0) // if player chose to upgrade
            {
                current.changeMoney(-1 * lots[option-1].getUpgradeCost());  // take their money
                lots[option-1].upgrade();
            }
        }
        while(option != 0);
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
