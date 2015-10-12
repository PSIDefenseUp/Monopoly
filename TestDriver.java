import java.awt.Color;
public class TestDriver
{
    public static void main(String[] args)
    {
        // Initialize array of board locations
        BoardLoc[] board = new BoardLoc[15];

        // Implement first 15 board spaces     
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
        board[12] = new Utility("ELECTRIC COMPANY", 12, 150);
        board[13] = new Lot("STATES AVE.", 13, 140, Color.MAGENTA, 100, new int[]{10, 50, 150, 450, 625, 750});
        board[14] = new Lot("VIRGINIA AVE.", 14, 160, Color.MAGENTA, 100, new int[]{12, 60, 180, 500, 700, 900});
        
        // Testing toString() for BoardLoc subclasses
        for (int i = 0; i < board.length; i++)
        {
            System.out.println(board[i].toString());
        }
        System.out.println();
        
        // Testing Player methods
        Player player = new Player();
        System.out.println("Testing Player class");
        System.out.println("Accessors");
        System.out.println("Money: " + player.getMoney() + " Postition: " + player.getPosition() + " Rolled: " + player.getRoll() + " Properties: " + player.getProperties());
        System.out.println("Mutators and toString()");
        player.changeRoll(2);
        player.move(50);
        System.out.println("toString() after player.move(30): " + player.toString());
        player.moveTo(5);
        System.out.println("toString() after player.moveTo(5): " + player.toString());
        player.changeMoney(500);
        player.addProperty(new Lot("testProperty", 0, 0, Color.WHITE, 0, new int[]{0}));
        System.out.println("toString() after addProperty(new Lot(\"testProperty\", 0, 0, Color.WHITE, 0, new int[]{0})): " + player.toString());
        player.removeProperty(new Lot("testProperty", 0, 0, Color.WHITE, 0, new int[]{0}));
        System.out.println("toString() after removeProperty(new Lot(\"testProperty\", 0, 0, Color.WHITE, 0, new int[]{0})): " + player.toString());
    }
}