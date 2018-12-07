package controller;

import modele.BoxOnFloor;
import modele.BoxOnGoal;
import modele.Cell;
import modele.Character;
import modele.CharacterOnGoal;
import modele.Empty;
import modele.Floor;
import modele.Goal;
import modele.Wall;
import defaultPackage.MainClass; 
import controller.tools.CSVElement; 
import java.io.FileNotFoundException;

public class Board {
	private Cell[][] board;
	private int characterX, characterY, GoalNb;
        private int boardXSize = 20,
                boardYSize = 20;
        
        public static final class dir {
            public static final int UP = 1;
            public static final int DOWN = 2;
            public static final int LEFT = 3;
            public static final int RIGHT = 4;
        }
        
        public Board() {
            this.board = new Cell[boardXSize][boardYSize];
            int[][] csv;
            String path = CSVElement.pick_CSVLevel();
            System.out.println(path);
            
            try {
                csv = CSVElement.readCSVFile(boardXSize, boardYSize, path);
                int val;
                
                // Go through the array
                for (int X = 0; X<boardXSize; X++){
                    for (int Y = 0; Y<boardYSize; Y++){
                        val = csv[X][Y];
                        Cell currentCell;

                        //Init cell depending on CSV Content
                        switch(val){
                            default :
                            case 0:
                                currentCell = new Cell(new Empty(), new Floor());
                                break;
                            case 1:
                                currentCell = new Cell(new Wall(), new Floor());                                
                                break;
                            case 2:
                                currentCell = new Cell(new BoxOnFloor(), new Floor());
                                break;
                            case 3:
                                currentCell = new Cell (new BoxOnGoal(), new Floor());
                                break;
                            case 4:
                                currentCell = new Cell(new Empty(), new Goal());
                                break;
                            case 5:
                                currentCell = new Cell(new Character(), new Floor());
                                // FIXME character X&Y are not the same than the board
                                this.characterY = X;
                                this.characterX = Y;
                                break;
                            case 6:
                                currentCell = new Cell(new Character(), new Goal());
                                break;
                        }
                        board[X][Y] = currentCell;
                    }
                }
            } catch (FileNotFoundException ex) {
                System.err.println(ex.toString());
            }

            initGoalNb();
            //initWorld();           
        }
   
	public Cell[][] getMatrix() {
		return this.board;
	}

	public int getGoalNb() {
		return this.GoalNb;
	}
	
	public int getElement(int x, int y){
		return this.board[y][x].getElement().getId();
	}

	public void moveCharacter(int i) {
		switch (i) {
		
		case 1:
			upCharacter();
			break;

		case 2:
			downCharacter();
			break;

		case 3:
			leftCharacter();
			break;

		case 4:
			rightCharacter();
			break;
		
		case 5:
			MainClass.main(null);
			break;
			
		case 6:
			System.exit(0);
			break;
		}
	}
	
        private void initGoalNb() {
        int i = 0;
        for (int i1 = 0; i1 < board[0].length; ++i1) {
                for (int i2 = 0; i2 < board[0].length; ++i2) {
                        if(board[i1][i2].isGoal())
                                ++i;
                }
        }
        this.GoalNb = i;
	}

	private void upCharacter() {
		if (board[characterY - 1][characterX].isBox() && board[characterY - 2][characterX].isFree()) {
			board[characterY - 1][characterX].setElement(new Empty());
			if(board[characterY - 2][characterX].isGoal())
				board[characterY - 2][characterX].setElement(new BoxOnGoal());
			else
				board[characterY - 2][characterX].setElement(new BoxOnFloor());
		}
		if(board[characterY - 1][characterX].isFree()){
			board[characterY][characterX].setElement(new Empty());
			if(board[characterY - 1][characterX].isGoal())
				board[--characterY][characterX].setElement(new CharacterOnGoal());
			else
				board[--characterY][characterX].setElement(new Character());
		}
	}
	
	private void downCharacter() {
		if (board[characterY + 1][characterX].isBox() && board[characterY + 2][characterX].isFree()) {
			board[characterY + 1][characterX].setElement(new Empty());
			if(board[characterY + 2][characterX].isGoal())
				board[characterY + 2][characterX].setElement(new BoxOnGoal());
			else
				board[characterY + 2][characterX].setElement(new BoxOnFloor());
		}
		if(board[characterY + 1][characterX].isFree()){
			board[characterY][characterX].setElement(new Empty());
			if(board[characterY + 1][characterX].isGoal())
				board[++characterY][characterX].setElement(new CharacterOnGoal());
			else
				board[++characterY][characterX].setElement(new Character());
		}
	}
	
	private void rightCharacter() {
		if (board[characterY][characterX + 1].isBox() && board[characterY][characterX + 2].isFree()) {
			board[characterY][characterX + 1].setElement(new Empty());
			if(board[characterY][characterX + 2].isGoal())
				board[characterY][characterX + 2].setElement(new BoxOnGoal());
			else
				board[characterY][characterX + 2].setElement(new BoxOnFloor());
		}
		if(board[characterY][characterX + 1].isFree()){
			board[characterY][characterX].setElement(new Empty());
			if(board[characterY][characterX + 1].isGoal())
				board[characterY][++characterX].setElement(new CharacterOnGoal());
			else
				board[characterY][++characterX].setElement(new Character());
		}
	}
	
	private void leftCharacter() {
		if (board[characterY][characterX - 1].isBox() && board[characterY][characterX - 2].isFree()) {
			board[characterY][characterX - 1].setElement(new Empty());
			if(board[characterY][characterX - 2].isGoal())
				board[characterY][characterX - 2].setElement(new BoxOnGoal());
			else
				board[characterY][characterX - 2].setElement(new BoxOnFloor());
		}
		if(board[characterY][characterX - 1].isFree()){
			board[characterY][characterX].setElement(new Empty());
			if(board[characterY][characterX - 1].isGoal())
				board[characterY][--characterX].setElement(new CharacterOnGoal());
			else
				board[characterY][--characterX].setElement(new Character());
		}
	}
	
}
