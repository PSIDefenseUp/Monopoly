// Programmer:  Noah Angeles, Dennis McNamara, Tim Werkheiser, & Wenkan Zhu
// Assignment:  Project: Monopoly
// Date:        October 2015
// Description: Main class containing all game elements

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import javax.swing.*;
import java.awt.event.*;

public class Monopoly extends JApplet implements ActionListener
{
    public static final int UIPADDING = 150;           // Padding for upper UI area

    private static Player[] players;                   // array of all players in game
    private static int currentPlayer;                  // the current player who has control
    private static BoardLoc[] board;                   // array of all board locations in the game
    private static int roll;                           // total value of the dice roll

    private int die1;                                  // the value rolled on the first die
    private int die2;                                  // the value rolled on the second die    
    private JButton[] buttons;                         // buttons for choices
    private JButton showProperty;                      // button for properties of each player
    private JButton end;                               // Ends the game
    private JButton[] gameMode;                        // buttons for demo or fresh start
    private JPanel panel;                              // panel for buttons that take player actions
    private boolean rolled;                            // has the player rolled this turn?
    private boolean upgrade;                           // is upgrading    
    private boolean gameOver;                          // is the game over?

    @Override
    public void init()
    {
        setLayout(new FlowLayout());

        // Set up players
        players = new Player[4];
        for(int i = 0; i < players.length; i++)
        {
            players[i] = new Player("Player " + (i + 1));
        }

        // Set up board
        setBoard();

        // Set gameOver to true while game has yet to start
        gameOver = true;

        // Adding UI elements
        showProperty = new JButton("Show Player Properties");
        showProperty.addActionListener(this);

        // Add end button
        end = new JButton("End Game");
        end.addActionListener(this);

        // Add mode selection buttons
        gameMode = new JButton[2];
        gameMode[0] = new JButton("Start New Game");
        gameMode[1] = new JButton("Demo Mode");
        gameMode[0].addActionListener(this);
        gameMode[1].addActionListener(this);

        panel = new JPanel();

        // Adding UI
        add(gameMode[0]);
        add(gameMode[1]);
        add(panel);
    }

    @Override
    public void paint(Graphics g)
    // PRE: g is initiialized, Applet dimensions >= 600x550
    // POST: draws the board, players, player info, and free parking pot size on the screen
    {
        super.paint(g);   

        // Draw board and players
        drawBoard(g);

        // Draw player info panels
        drawPlayerPanels(g);

        // Draw free parking pot
        g.setColor(Color.BLACK);
        String potString = "Free Parking Pot: $" + TaxSquare.getPot();
        int i = g.getFontMetrics().stringWidth(potString);
        g.drawString(potString, (getWidth()/2) - i/2, getHeight()/2);
    }

    public void actionPerformed(ActionEvent e)
    {
        int option;                              // The user's chosen option in a pop-up window
        String[] options;                        // The options to display in a pop-up window

        if(e.getSource() == gameMode[0])         // Start button lets players choose tokens
        {                                        //   and amount of players with error checking
            setup();
        }

        if(e.getSource() == gameMode[1])         // Demo Button loads a game with players
        {                                        //   that already own some properties
            setupDemo();
        }

        if(e.getSource() == showProperty)        // Button for showing players' properties
        {
            // Select a player to view the properties of
            options = new String[players.length];
            for(int i = 0; i < options.length; i++)
            {
                options[i] = players[i].getName();
            }

            option = JOptionPane.showOptionDialog(this, "Choose a player", "VIEWING PROPERTIES",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            
            // If a player was selected, show their properties
            if(option >= 0)
            {
                // Load string array of the player's properties
                options = new String[players[option].getProperties().length];
                for(int i = 0; i < options.length; i++)
                {
                    options[i] = players[option].getProperties()[i].getName();
                }

                // Display this player's properties
                JOptionPane.showMessageDialog(this, new JList(options));            
            }
        }

        // If the END button is pressed, end the game
        if(e.getSource() == end)
        {            
            // Make sure we want to end the game
            option = JOptionPane.showConfirmDialog(this, "Do you really want to end the game?");

            if(option == 0)
            {
                // END THE GAME
                gameOver = true;
                this.remove(panel);
                panel = new JPanel();
                this.remove(end);
                this.validate();        
            }
        }

        for(int i = 0; i < buttons.length; i++) // Handles buttons that deal with player turn options
        {
            if(e.getSource() == buttons[i])
            {
                if(!rolled)                     // If the player hasn't rolled yet, let them
                {
                    switch(i)
                    {
                        case 0: rolled = true; rollDice(); 
                        break;
                        case 1: players[currentPlayer].upgrade(); 
                        break;
                        case 2: players[currentPlayer].sell(); 
                        break;
                        default: break;
                    }                    
                }                                
                else                            // If the player has rolled, give them their options
                {                               //   for the space they are on.

                    board[players[currentPlayer].getPosition()].onLand(players[currentPlayer], i);                    
                    endTurn();
                }
            }
        } 

        repaint();
    }

    public void addButtons(String[] options)
    // PRE:  options = string[] of options to be turned into buttons
    // POST: replaces previous buttons on UI and puts new buttons
    {
        this.remove(panel);
        panel = new JPanel();
        buttons = new JButton[options.length];
        for (int i = 0; i < options.length; i++)
        {
            buttons[i] = new JButton(options[i]);
            buttons[i].addActionListener(this);
            panel.add(buttons[i]);
        }
        this.add(panel);
        this.validate();
    }

    public void setBoard()
    // POST: The board is filled out with the 40 board locations of a standard Monopoly game
    {   
        Color purple;                      // the color purple

        purple = new Color(102, 51, 153);  // Initialize the color of purple
        board = new BoardLoc[40];          // Initialize array of board locations

        // Implement board locations    
        board[0] = new CornerSquare("GO", 0);
        board[1] = new Lot("MEDITERRANEAN AVE.", 1, 60, purple, 
            50, new int[]{2, 10, 30, 90, 160, 230});
        board[2] = new CardSquare("Community Chest", 2);
        board[3] = new Lot("BALTIC AVE.", 3, 60, purple, 
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

    public void setup()
    // POST: A normal game is started with the specified player count, 
    //   with all players starting evenly-matched and ready at GO
    {
        int playerCount;                // The amount of players in the game
        int chosenToken;                // The number of the token chosen by a player
        String choice;                  // The option chosen by the user

        // Start game
        gameOver = false;

        // Change base UI
        remove(gameMode[0]);
        remove(gameMode[1]);
        add(end);
        add(showProperty);
        
        // Get player count
        playerCount = 0;

        do 
        {
            choice = JOptionPane.showInputDialog(this, "How many players?" +
                " (Between 2 to 4)");

            if(choice != null)
            {
                playerCount = Integer.parseInt(choice);
            }
        } 
        while (playerCount > 4 || playerCount < 2);

        // Initialize players
        players = new Player[playerCount];
        for (int i = 0; i < playerCount; i++)
        {
            players[i] = new Player("Player " + (i + 1));

            // Get the player's chosen token
            chosenToken = 0;

            do 
            {
                choice = JOptionPane.showInputDialog(this, "Pick a token for "
                    + "Player " + (i+1) + " : (Between 1 to 4)");

                if(choice != null)
                {
                    chosenToken = Integer.parseInt(choice);

                    switch(chosenToken)
                    {
                        case 1: players[i].setToken("token1.png"); break;       // token 1
                        case 2: players[i].setToken("token2.png"); break;       // token 2
                        case 3: players[i].setToken("token3.png"); break;       // token 3
                        case 4: players[i].setToken("token4.png"); break;       // token 4
                        default: break;
                    }
                }                
            }
            while (chosenToken > 4 || chosenToken < 1);
        }

        // Start the game
        currentPlayer = (int)Math.random()*players.length;
        roll = 1;
        play();
    }

    public void setupDemo()
    // POST: The game is started in a demo mode, with 2 players that already own various properties on the board
    {
        // Start game
        gameOver = false;

        // Change base UI
        remove(gameMode[0]);
        remove(gameMode[1]);
        add(end);
        add(showProperty);

        // Initialize players
        players = new Player[2];
        for (int i = 0; i < players.length; i++)
        {
            players[i] = new Player("Player " + (i + 1));
        }
        players[0].setToken("token1.png");
        players[1].setToken("token2.png");

        // Give players some properties
        players[0].addProperty((Property)board[1]);
        players[0].addProperty((Property)board[3]);
        players[0].addProperty((Property)board[5]);
        players[0].addProperty((Property)board[6]);
        players[0].addProperty((Property)board[8]);
        players[0].addProperty((Property)board[9]);
        players[0].addProperty((Property)board[11]);
        players[0].addProperty((Property)board[12]);
        
        players[1].addProperty((Property)board[13]);
        players[1].addProperty((Property)board[14]);
        players[1].addProperty((Property)board[15]);
        players[1].addProperty((Property)board[16]);
        players[1].addProperty((Property)board[18]);
        players[1].addProperty((Property)board[19]);
        players[1].addProperty((Property)board[21]);
        players[1].addProperty((Property)board[23]);

        // Start the game
        currentPlayer = (int)Math.random()*players.length;
        roll = 1;
        play();
    }        

    public void play()
    // POST: Starts a turn for player players[currentPlayer] if not bankrupt, setting up the initial menu
    {
        if(players[currentPlayer].getMoney() > 0) // If the player isn't bankrupt, start their turn
        {   
            addButtons(new String[] {"Roll dice", "Upgrade a property", "Sell a property"});              
        }
        else                                      // If the player is bankrupt, skip them
        {
            endTurn();
        }
    }

    public void rollDice()
    // POST: The current player is moved ahead on the board based on their dice roll,
    //   and buttons are added to the UI for the possible actions available at the
    //   player's new location
    {
        // Roll the dice
        die1 = (int)(Math.random() * 6) + 1;
        die2 = (int)(Math.random() * 6) + 1;
        roll = die1 + die2;
        JOptionPane.showMessageDialog(this, "You rolled " + die1 + " + " + die2 + " = " + roll);

        // Move the player
        players[currentPlayer].move(roll);

        // Get actions for current player's new location and display them
        addButtons(board[players[currentPlayer].getPosition()].getPossibleActions(players[currentPlayer]));  
    }

    public void endTurn()
    // POST: If a player is bankrupt, bankruptcy is processed. The turn is given to the next player.
    {
        // Check bankruptcy for current player
        if(players[currentPlayer].getMoney() <= 0)
        {
            if(board[players[currentPlayer].getPosition()] instanceof Property) // The player was
            {                                                                   // bankrupted by
                                                                                // another player
                players[currentPlayer].bankrupt
                    (((Property)board[players[currentPlayer].getPosition()]).getOwner());
            }
            else                                             // The player was bankrupted by the bank 
            {
                players[currentPlayer].bankrupt(null);
            }
        }

        // Progress to the next turn
        if(die1 != die2)                // If the player did not roll doubles, the next player goes
        {
            currentPlayer = (currentPlayer + 1) % players.length; 
        }
        rolled = false;
        play();
    }

    public void drawPlayerPanels(Graphics g)
    // PRE: g is initiialized
    // POST: draws player info panels at the top of the window for all players in the game
    {
        int padding;    // The padding between player panels in pixels
        int width;      // The width of a player panel in pixels
        int height;     // The height of a player panel in pixels
        int x;          // The x-location of a player panel
        int y;          // The y-location of a player panel

        padding = 10;
        width = (UIPADDING - 100) * 2;
        height = UIPADDING - 100;
        x = this.getWidth()/2 - (players.length * width + (players.length - 1) * padding)/2;
        y = 75;

        for(int i = 0; i < players.length; i++)
        {
            // Indicate current player
            if(i == currentPlayer)
            {
                g.setColor(Color.GREEN);
                g.fillRect(x - padding/2, y - padding/2, width + padding, height + padding);
            }

            // Draw info panel
            players[i].renderInfoPanel(g, x, y, width, height);
            x += width + padding;
        }
    }

    public void drawBoard(Graphics g)
    //PRE: g is initialized
    //POST: Draws all tiles on the game board, along with the players on them
    {
        int startX = 0;                       // start x of board
        int startY = UIPADDING;               // start y of board
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

    public static Player getCurrentPlayer()
    // POST: FCTVAL = Current Player instance
    {
        return players[currentPlayer];
    }

    public static Player getPlayer(int playerIndex)
    // POST: FCTVAL = The player at index playerIndex
    {
        return players[playerIndex];
    }

    public static Player[] getPlayers()
    // POST: FCTVAL = The list of players
    {
        return players;
    }

    public static int getBoardLength()
    // POST: FCTVAL = number of positions on the board
    {
        return board.length;
    }

    public static int getCurrentRoll()
    // POST: FCTVAL = the current total dice roll
    {
        return roll;
    }

    public static void printBoardInfo()
    // POST: Prints all information about each location the board to the command line
    {
        for(BoardLoc b : board)
        {
            System.out.println(b.toString());
        }
    }
}
