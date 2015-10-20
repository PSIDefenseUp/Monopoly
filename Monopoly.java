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
        if (players.length == 2)    // assumes there are at least 2 players
        {
            g.drawString("Player 1 is blue. Player 2 is red.", getWidth()/3, (getHeight()+50)/2);
        }
        if (players.length == 3)
        {
            g.drawString("Player 1 is blue. Player 2 is red.", getWidth()/3, (getHeight()+50)/2);
            g.drawString("Player 3 is green.", getWidth()/3, (getHeight()+50)/2+15);
        }
        if (players.length == 4)    // at most 4
        {
            g.drawString("Player 1 is blue. Player 2 is red.", getWidth()/3, (getHeight()+50)/2);
            g.drawString("Player 3 is green. Player 4 is black.", getWidth()/3, (getHeight()+50)/2+15);
        }
        drawBoard(g);
    }
    
    public void addButtons(String[] options)
    // PRE:  options = string[] of options to be turned into buttons
    // POST: replace previous buttons and puts new buttons
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
    
    public void drawPlayer(Graphics g, int size, int x, int y, int index)
    //PRE:  g = object this, size = size of oval, x = x postition, y = y position, 0 < index < 4
    //POST: player 1 as blue oval, player 2 as red oval, player 3 as green, player 4 as black
    //      at specific position in square
    {
        if (index == 0) // player 1 
        {
            g.setColor(Color.BLUE);
            g.fillOval(x+size, y+size, size, size);
        }
        if (index == 1) // player 2
        {
            g.setColor(Color.RED);
            g.fillOval(x+size*2, y+size, size, size);
        }
        if (index == 2) // player 3
        {
            g.setColor(Color.GREEN);
            g.fillOval(x+size, y+size*2, size, size);
        }
        if (index == 3) // player 4
        {
            g.setColor(Color.BLACK);
            g.fillOval(x+size*2, y+size*2, size, size);
        }
        g.setColor(Color.BLACK);    // setting color back to default
    }
    
    public void drawBoard(Graphics g)
    //PRE:  Applet size >= 600x550 must be a square with +50 in height for buttons
    //      g = object to draw on
    //POST: Draw a board representing the game and players
    {
        int startX = 0;                       // start x of board
        int startY = 50;                      // start y of board
        int width = getWidth();               // width of board
        int height = getHeight() - startY;    // height of board        
        int tileWidth = width/11;             // width of each tile
        int tileHeight = height/11;           // height of each tile
        
        for (int i = 0; i < 40; i++)
        {
            board[i].render(g, startX, startY, tileWidth, tileHeight);

            if (i >= 0 && i < 10)
            {                
                startX = startX + tileWidth;
            }
            if (i >= 10 && i < 20)
            {                
                startY = startY + tileHeight;
            }
            if (i >= 20 && i < 30)
            {                
                startX = startX - tileWidth;
            }
            if (i >= 30 && i < 40)
            {                
                startY = startY - tileHeight;
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
                        case 1: players[currentPlayer].upgrade();
                                break; 
                        case 2: players[currentPlayer].sell();
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
