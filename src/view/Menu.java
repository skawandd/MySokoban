package view;

import controller.GameController;
import java.util.List;
import modele.Grid;
import modele.Move;

public class Menu {
	private GameController game;
	
	public Menu(GameController game) {
		this.game = game;
	}
	
	public void askPlayerName() {
		System.out.println("Enter name:");
	}
	
	public void showActions() {
		System.out.println("1.Up    3.Left  | 5.Restart\n2.Down  4.Right | 6.Exit\n=======================================\n");
	}
	
	public void showInfo(String time) {
		System.out.println("=======================================");
		System.out.println("Lvl"+ game.getLvl()+" | Moves: "+game.getMoves()+" | Timer: "+time+" | "+game.getPlayerName());
	}
	
	public void showVictory(String playerName, String time, int moves) {
		System.out.println("Congrats " + playerName + " you won!\nYou made " + moves + " moves in " + time+"\n");
	}
	
	public void showRestart() {
		System.out.println("Restart?\n1.Yes 2.No\n");
	}
	       
        /**
         * 
         * @param grid 
         */
        public void printBoard(){
            Grid grid = game.getGrid();
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
     * @param moveSequence 
     * @return  String
     */
    public static String printSequence(List<Move> moveSequence){
        String sequence = new String();
        if(moveSequence.size()>0){
            for(Move move : moveSequence){
                sequence+= move;
                sequence+=" ";
            }
        }
        return sequence;
    }
	
}