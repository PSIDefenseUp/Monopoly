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
    private static Player[] players;  // array of all players in game
    private static int currentPlayer; // the current player who has control
    private static BoardLoc[] board;  // array of all board locations in the game
    private static boolean gameOver;  // Whether or not the game is over
    private static int roll;          // total value of the dice roll
    private JButton[] buttons;        // buttons for choices
    private JButton showProperty;     // button for properties of each player
    private JButton[] gameMode;       // buttons for demo or fresh start
    private JPanel panel;             // panels for buttons
    private boolean rolled;           // has the player rolled this turn?
    private boolean upgrade;          // is upgrading

    public static final int UIPADDING = 150; // Padding for upper UI area

    public void init()
    // POST: Initializes all game elements, such as the board and players in the game
    {
        setLayout(new FlowLayout());

        // Set up board
        setBoard();

        // Initialize players
        players = new Player[4];
        for (int i = 0; i < players.length; i++)
        {
            players[i] = new Player("Player " + (i + 1));
        }

        // Adding UI elements
        showProperty = new JButton("Show Player Properties");
        showProperty.addActionListener(this);

        gameMode = new JButton[2];
        gameMode[0] = new JButton("Start New Game");
        gameMode[1] = new JButton("Demo Mode");
        gameMode[0].addActionListener(this);
        gameMode[1].addActionListener(this);

        panel = new JPanel();

        // Adding UI
        add(gameMode[0]);
        add(gameMode[1]);
        //add(showProperty);
        add(panel);

        //play();
    }

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);        
        drawBoard(g);
        drawPlayerPanels(g);
        String potString = "Free Parking Pot: $" + TaxSquare.getPot();
        int i = g.getFontMetrics().stringWidth(potString);
        g.drawString(potString, (getWidth()/2) - i/2, getHeight()/2);
    }

    public void addButtons(String[] options)
    // PRE:  options = string[] of options to be turned into buttons
    // POST: replace previous buttons and puts new buttons
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

    public void drawPlayerPanels(Graphics g)
    {
        int padding;
        int width;
        int height;        
        int x;
        int y;        

        padding = 10;
        width = (UIPADDING - 100) * 3;
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
    //PRE:  Applet size >= 600x550 must be a square with +50 in height for buttons
    //      g = object to draw on
    //POST: Draw a board representing the game and players
    {
        int startX = 0;                       // start x of board
        int startY = UIPADDING;                      // start y of board
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
        board[3] = new Lot("BALTIC AVE", 3, 60, purple, 
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

    public void play()
    {
        // If current player is still in the game
        //     Let current player take an action or roll
        //     After roll, resolve landing on the player's new location
        //     ?? Check if player is bankrupt ??
        // Increment currentPlayer to progress to next player's turn
        // System.out.println("Player " + (currentPlayer+1) + "'s turn.");
        //  System.out.println(players[currentPlayer].toString());

        if(players[currentPlayer].getMoney() > 0) // if player is still in game
        {   
            addButtons(new String[] {"Roll dice", "Upgrade a property", "Sell a property"});              
        }
        else
            endTurn();
    }

    public void rollDice()
    {
        roll = ((int)(Math.random() * 6) + 1) + ((int)(Math.random() * 6) + 1);

        // Move the player
        players[currentPlayer].move(roll);

        // Get actions for current player's new location and display them
        addButtons(board[players[currentPlayer].getPosition()].getPossibleActions(players[currentPlayer]));      
    }

    public void endTurn()
    {
        // Check bankruptcy for current player
        if(players[currentPlayer].getMoney() <= 0)
        {
            if(board[players[currentPlayer].getPosition()] instanceof Property)
                players[currentPlayer].bankrupt(((Property)board[players[currentPlayer].getPosition()]).getOwner());
            else
                players[currentPlayer].bankrupt(null);
        }

        // Move to the next player's turn
        currentPlayer = (currentPlayer + 1) % players.length; 
        rolled = false;
        play();
    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == gameMode[0])    // Start button lets players choose tokens and amount of players with error checking
        {            
            remove(gameMode[0]);
            remove(gameMode[1]);
            add(showProperty);
            int num = 0;
            String s = "";
            do {
                s = JOptionPane.showInputDialog("How many players? (Between 2 to 4)");
                num = Integer.parseInt(s);
            }while (num > 4 || num < 2);
            // Initialize players
            players = new Player[num];
            for (int i = 0; i < num; i++)
            {
                int temp;
                players[i] = new Player("Player " + (i + 1));
                do {
                    s = JOptionPane.showInputDialog("Pick token for Player "+ (i+1) +" : (Between 1 to 4)");
                    temp = Integer.parseInt(s);
                    s = "token" + s + ".png";
                    players[i].setToken(s);
                }while (temp > 4 || temp < 1);
            }
            // Prepare game for start
            currentPlayer = (int)Math.random()*players.length;
            gameOver = false;
            roll = 1;
            play();
        }
        if(e.getSource() == gameMode[1])    // Demo Button
        {
            remove(gameMode[0]);
            remove(gameMode[1]);
            add(showProperty);
            // Initialize players
            players = new Player[2];
            for (int i = 0; i < players.length; i++)
            {
                players[i] = new Player("Player " + (i + 1));
            }
            players[0].setToken("token1.png");
            players[1].setToken("token2.png");
            
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

            // Prepare game for start
            currentPlayer = 0;
            gameOver = false;
            roll = 1;
            play();
        }
        if(e.getSource() == showProperty)   // Button for showing property
        {
            String s[] = {"Player 1", "Player 2", "Player 3", "Player 4"};
            String s2[];
            Property[][] temp = new Property[players.length][];
            int option = JOptionPane.showOptionDialog(null, "Choose player", "PROPERTIES",JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE,null, s, s[0]);
            for (int i = 0; i < players.length; i++)
            {
                temp[i] = players[i].getProperties();
            }
            if (option == 0)
            {
                s2 = new String[temp[0].length];
                for (int j = 0; j < temp[0].length; j++)
                {
                    s2[j] = temp[0][j].getName();
                }
                JList list = new JList(s2);
                JOptionPane.showMessageDialog(null, list);
            }
            if (option == 1)
            {
                s2 = new String[temp[1].length];
                for (int j = 0; j < temp[1].length; j++)
                {
                    s2[j] = temp[1][j].getName();
                }
                JList list = new JList(s2);
                JOptionPane.showMessageDialog(null, list);
            }
            if (option == 2)
            {
                s2 = new String[temp[2].length];
                for (int j = 0; j < temp[2].length; j++)
                {
                    s2[j] = temp[2][j].getName();
                }   
                JList list = new JList(s2);
                JOptionPane.showMessageDialog(null, list);
            }
            if (option == 3)
            {
                s2 = new String[temp[2].length];
                for (int j = 0; j < temp[2].length; j++)
                {
                    s2[j] = temp[2][j].getName();
                }    
                JList list = new JList(s2);
                JOptionPane.showMessageDialog(null, list);
            }
        }
        for(int i = 0; i < buttons.length; i++) // Handles buttons that deal with player options
        {
            if(e.getSource() == buttons[i])
            {
                if(!rolled)
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
                else
                {
                    board[players[currentPlayer].getPosition()].onLand(players[currentPlayer], i);
                    if (board[players[currentPlayer].getPosition()].getName() == "Free Parking")
                    {
                        players[currentPlayer].changeMoney(TaxSquare.getPot());
                        TaxSquare.resetPot();
                    }
                    endTurn();
                }
            }
        }        
        repaint();
    }    

    public static Player getCurrentPlayer()
    // POST: FCTVAL = Current Player instance
    {
        return players[currentPlayer];
    }

    public static Player getPlayer(int playerIndex)
    {
        return players[playerIndex];
    }

    public static Player[] getPlayers()
    {
        return players;
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
