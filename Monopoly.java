import java.awt.Color;

// Programmer:  Noah Angeles, Dennis McNamara, Tim Werkheiser, & Wenkan Zhu
// Assignment:  Project: Monopoly
// Date:        October 2015
// Description: Main class containing all game elements

public class Monopoly 
{
	private static Player[] players;
	private static int currentPlayer;
	private static BoardLoc[] board;
	private static boolean gameOver;
	private static int roll;
	
	public static void init()
	// POST: Initializes all game elements, such as the board and players in the game
	{
		// Set up board
		setBoard(board);
		
		players = new Player[4];
		for(Player p : players)
		{
			p = new Player();
		}
		
		// Prepare game for start
		currentPlayer = 0;
		gameOver = false;
	}
	
	public static void setBoard(BoardLoc[] board)
	{
		// TODO: For the time being, utilities are Color.WHITE and railroads Color.BLACK, but they really don't need a color at all.		
		// TODO: Put all locations on the board
		
		board = new BoardLoc[15];
		
		// Implement first 15 board spaces for Deliverable #2 (Completed)		
		board[0] = new CornerSquare("GO", 0);
		board[1] = new Lot("MEDITERRANEAN AVE", 1, 60, new Color(102, 51, 153), 50, new int[]{2, 10, 30, 90, 160, 230});
		board[2] = new CardSquare("Community Chest", 2);
		board[3] = new Lot("BALTIC AVE", 2, 60, new Color(102, 51, 153), 50, new int[]{4, 20, 60, 180, 320, 460});
		board[4] = new TaxSquare("Income Tax", 4);
		board[5] = new Railroad("READING RAILROAD", 5, 200);
		board[6] = new Lot("ORIENTAL AVE.", 6, 100, Color.CYAN, 50, new int[]{6, 30, 90, 270, 400, 550});
		board[7] = new CardSquare("Chance", 7);
		board[8] = new Lot("VERMONT AVE.", 8, 100, Color.CYAN, 50, new int[]{6, 30, 90, 270, 400, 550});
		board[9] = new Lot("CONNECTICUT AVE.", 9, 120, Color.CYAN, 50, new int[]{8, 40, 100, 300, 450, 600});
		board[10] = new CornerSquare("Jail", 10);
		board[11] = new Lot("ST. CHARLES PLACE", 11, 140, Color.MAGENTA, 100, new int[]{10, 50, 150, 450, 625, 750});
		board[12] = new Utility("ELECTRIC COMPANY", 12, 150, Color.WHITE);
		board[13] = new Lot("STATES AVE.", 13, 140, Color.MAGENTA, 100, new int[]{10, 50, 150, 450, 625, 750});
		board[14] = new Lot("VIRGINIA AVE.", 14, 160, Color.MAGENTA, 100, new int[]{12, 60, 180, 500, 700, 900});
	}
	
	public static void main(String[] args)
	{
		init();
		
		// 
		while(!gameOver)
		{
			// TODO: Implement
			
			// If current player is still in the game
			//     Let current player take an action or roll
			//     After roll, resolve landing on the player's new location
			//     ?? Check if player is bankrupt ??
			// Increment currentPlayer to progress to next player's turn
			
			currentPlayer = (currentPlayer+1)%players.length; 
		}
	}
	
	public static Player getCurrentPlayer()
	{
		return players[currentPlayer];
	}
	
	public static Player getPlayer(int playerID)
	{
		return players[playerID];
	}
}
