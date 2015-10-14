// Programmer:  Noah Angeles, Dennis McNamara, Tim Werkheiser, & Wenkan Zhu
// Assignment:  Project: Monopoly
// Date:        October 2015
// Description: Class to test Player and Property classes for the Fall 2015 CS342 Monopoly project

import java.awt.Color;

public class TestDriver
{
    public static void main(String[] args)
    {
        Color purple;  // Color variable for purple

        purple = new Color(102, 51, 153);    
        
        // Set up overarching game    
        Monopoly.init();

        // Initialize array of board locations
        BoardLoc[] board = new BoardLoc[15];

        // Implement first 15 board spaces     
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
        System.out.println("Money: " + player.getMoney() + " Postition: " 
                               + player.getPosition() + " Properties: " 
                               + player.getProperties());
        System.out.println("Mutators and toString()");
        player.move(50);
        System.out.println("toString() after player.move(30): " + player.toString());
        player.moveTo(5);
        System.out.println("toString() after player.moveTo(5): " + player.toString());
        player.changeMoney(500);
        player.addProperty(new Lot("testProperty", 0, 0, Color.WHITE, 0, new int[]{0}));
        System.out.println("toString() after addProperty(new Lot(\"testProperty\","
                               + " 0, 0, Color.WHITE, 0, new int[]{0})): " 
                               + player.toString());
        player.removeProperty(new Lot("testProperty", 0, 0, Color.WHITE, 0, new int[]{0}));
        System.out.println("toString() after removeProperty(new Lot(\"testProperty\","
                               + "0, 0, Color.WHITE, 0, new int[]{0})): " 
                               + player.toString());


        // Test board locations
        //     NOTE: Property, BoardLoc are abstract classes
    
        // Testing CardSquare using board[2]
        System.out.println();
        System.out.println("Testing CardSquare");
        int rent = board[2].getRent();
        System.out.println("getRent() for CardSquare: " + rent);
        Player player2 = new Player();
        System.out.println("Testing onLand()");
        board[2].onLand(player2);
        System.out.println("toString of default player2 after onLand(): " + player2.toString());
        String[] str = board[2].getPossibleActions(player2);
        System.out.print("Printing strings given by getPossibleActions(): ");
        for (int i = 0; i < str.length; i++)
        {
            System.out.print(str[i] + " ");
        }
        System.out.println();
        
        // Testing TaxSquare using board[4]
        System.out.println();
        System.out.println("Testing TaxSquare");
        rent = board[4].getRent();
        System.out.println("getRent() for TaxSquare: " + rent);
        System.out.println("Testing onLand()");
        board[4].onLand(player2);
        System.out.println("toString of default player2 after onLand(): " + player2.toString());
        str = board[4].getPossibleActions(player2);
        System.out.print("Printing strings given by getPossibleActions(): ");
        for (int i = 0; i < str.length; i++)
        {
            System.out.print(str[i] + " ");
        }
        System.out.println();
        
        // Testing CornerSquare using board[0]
        System.out.println();
        System.out.println("Testing CornerSquare");
        rent = board[0].getRent();
        System.out.println("getRent() for CornerSquare: " + rent);
        System.out.println("Testing onLand()");
        board[0].onLand(player2);
        System.out.println("toString of default player2 after onLand(): " + player2.toString());
        str = board[0].getPossibleActions(player2);
        System.out.print("Printing strings given by getPossibleActions(): ");
        for (int i = 0; i < str.length; i++)
        {
            System.out.print(str[i] + " ");
        }
        System.out.println();

        // Testing Lot using board[1]
        Lot lot = (Lot)board[1];
        System.out.println();
        System.out.println("Testing Lot");
        System.out.println("getCost(): " + lot.getCost());
        System.out.println("getRent(): " + lot.getRent());
        System.out.println("getUpgradeCost(): " + lot.getUpgradeCost());
        System.out.println("getUpgradeCount(): " + lot.getUpgradeCount());
        System.out.print("upgrade(): new upgradeCount = ");
        lot.upgrade();
        System.out.println(lot.getUpgradeCount());
        System.out.println("Testing onLand():");
        lot.onLand(player2);
        System.out.println("toString of player2 after onLand(): " + player2.toString());
        str = lot.getPossibleActions(player2);
        System.out.print("Printing strings given by getPossibleActions(): ");
        for(int i = 0; i < str.length; i++)
        {
            System.out.print(str[i] + " ");
        }
        System.out.println();

        // Testing Railroad using board[5]
        System.out.println();
        System.out.println("Testing Railroad");
        System.out.println("getRent(): " + board[5].getRent());
        System.out.println("Testing onLand()");
        board[5].onLand(player2);
        System.out.println("toString of player2 after onLand(): " + player2.toString());
        str = board[5].getPossibleActions(player2);
        System.out.print("Printing strings given by getPossibleActions(): ");
        for(int i = 0; i < str.length; i++)
        {
            System.out.print(str[i] + " ");
        }
        System.out.println();

        // Testing Utility using board[12]
        System.out.println();
        System.out.println("Testing Utility");
        System.out.println("getRent(): " + board[12].getRent());
        System.out.println("Testing onLand()");
        board[12].onLand(player2);
        System.out.println("toString of player2 after onLand(): " + player2.toString());
        str = board[12].getPossibleActions(player2);
        System.out.print("Printing strings given by getPossibleActions(): ");
        for(int i = 0; i < str.length; i++)
        {
            System.out.print(str[i] + " ");
        }        
        System.out.println();
    }
}
