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
        setBoard(board);
        
        // Initialize players
        players = new Player[4];
        for(Player p : players)
        {
            p = new Player();
        }
        
        // Prepare game for start
        currentPlayer = 0;
        gameOver = false;
        roll = 1;
    }
    
    public static void setBoard(BoardLoc[] board)
    {   
        Color purple; // the color purple
            
        purple = new Color(102, 51, 153);  // Initialize the color of purple
        board = new BoardLoc[40];  // Initialize array of board locations
                
        // Implement first 15 board spaces for Deliverable #2      
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
        
        // The rest of the board's locations
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

        init();
        
        while(!gameOver)  // continue game while it isn't over
        {            
            // If current player is still in the game
            //     Let current player take an action or roll
            //     After roll, resolve landing on the player's new location
            //     ?? Check if player is bankrupt ??
            // Increment currentPlayer to progress to next player's turn
            
            if(players[currentPlayer].getMoney() >= 0) // if the player is still in the game
            {
                String[] performAction;  // string of actions the Player can perform
                
                performAction = new String[] {"Roll dice", "Upgrade a property", "Sell a property"};
                
                // Get player's action of choice until they decide to roll the dice
                do
                {
                    actionOptions = ActionsMenu.runActionsMenu(performAction);
                    switch(actionOptions)
                    {
                        case 1: break; // TODO: Give user a list of their properties to updgrade 
                                       // (if they have the money and the property cab be upgraded)
                        case 2: break; // TODO: Give user a list of their properties + sell values,
                                       // ask which is to be sold (handle in its own method)
                        default: // Continue to dice roll
                    }
                }
                while(actionOptions != 0);

                // Roll two dice
                roll = ((int)(Math.random() * 6) + 1) + ((int)(Math.random() * 6) + 1);
                 // Move the player
                players[currentPlayer].move(roll);
                 // Run onLand for the player's new position
                board[players[currentPlayer].getPosition()].onLand(players[currentPlayer]);
            }

            // TODO: ?? Check bankruptcy ??

            // Move to the next player's turn
            currentPlayer = (currentPlayer + 1) % players.length; 
        }
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
