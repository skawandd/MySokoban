package view;

import controller.PlayerGameController;
import java.util.List;
import modele.Grid;
import modele.Move;

/**
 * Display class prints content in the Console
 */
public class Display {
    private final PlayerGameController game;

    /**
     * A menu object shall be created to display in teh console
     * @param game GameController object we display for 
     */
    public Display(final PlayerGameController game) {
            this.game = game;
    }

    /**
     * Asks the user for his name
     */
    public void askPlayerName() {
            System.out.println("Enter name:");
    }

    /**
     * Prints possible actions in the console
     */
    public void showActions() {
            System.out.println("1.Up    3.Left  | 5.Restart\n2.Down  4.Right | 6.Exit\n=======================================\n");
    }

    /**
     * Prints infos about the current game in the console
     * @param time Elapsed time
     */
    public void showInfo(final String time) {
            System.out.println("=======================================");
            System.out.println("Lvl"+ game.getNbMoves()+" | Moves: "+game.getNbMoves()+" | Timer: "+time+" | "+game.getPlayerName());
    }

    /**
     * Displays Victory Informations
     * @param playerName The player's name
     * @param time elapsed time
     * @param moves Number of moves
     */
    public void showVictory(final String playerName, final String time, final int moves) {
            System.out.println("Congrats " + playerName + " you won!\nYou made " + moves + " moves in " + time+"\n");
    }

    /**
     * Asks the user to restart
     */
    public void showRestart() {
            System.out.println("Restart?\n1.Yes 2.No\n");
    }
        
    /**
     * Print a Grid in the console
     * @param grid the Grid Object you want to print
     */
    public static void printBoard(Grid grid){
        int p_X = grid.getX(),
                p_Y = grid.getY();
        byte[][] p_Board = grid.getGrid();
        char[] symbols = {'.','#','x','X','o','m','M'};

        for(int y=0; y < p_Y; y++){
            for(int x = 0; x < p_X; x++){
                int valBoard = p_Board[x][y];
                System.out.print(symbols[valBoard]);                
            }
            System.out.println();
        }
    }
    
    /**
     * Returns the list of move as a String
     * @param moveSequence The list you want to print
     * @return  String
     */
    public static String moveSequenceAsString(List<Move> moveSequence){
        String sequence = new String();
        if(moveSequence.size()>0){
            for(Move move : moveSequence){
                sequence+= move;
                sequence+=" ";
            }
        }
        return sequence;
    }
    
    /**
     * Display time in "88:88" format
     * @param seconds number of second
     * @return String time in "88:88" format
     */
    public static String getStopWatch(int seconds) {
        int euclide = seconds / 60;
        int rest = seconds % 60;
        String buffer;
        if (euclide < 10) {
            buffer = "0" + euclide;
        } else {
            buffer = "" + euclide;
        }
        if (rest < 10) {
            buffer += ":0" + rest;
        } else {
            buffer += ":" + rest;
        }
        return buffer;
    }
	
}