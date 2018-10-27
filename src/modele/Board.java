package modele;

import controller.tools.CSVElement;
import java.io.FileNotFoundException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * 
 * @author Dylan
 * The play board
 */
public class Board {
	private Element[][] board;
	private int characterX, characterY;
	private Element nextCell;
        
        private int boardXSize = 20;
        private int boardYSize = 20;

	public Board() {
            this.board = new Element[boardXSize][boardYSize];
            int[][] csv;
            //String path = "/Users/benjamin/Dropbox/0-ECE/JAVA_POO/Projet_Sokoban/MySokoban/src/view/niveau_1.csv";
            String path = pick_CSVLevel();
            System.out.println(path);
            
            try {
                csv = CSVElement.readCSVFile(boardXSize, boardYSize, path);
                int val;
                
                // Go through the array
                for (int X = 0; X<boardXSize; X++){
                    for (int Y = 0; Y<boardYSize; Y++){
                        val = csv[X][Y];
                        board[X][Y] = new Element(val);
                    }
                }
            } catch (FileNotFoundException ex) {
                System.err.println(ex.toString());
            }
            initWorld();           
        }
        
        private String pick_CSVLevel(){
            FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV File", "csv");
            JFileChooser chooser = new JFileChooser();
            chooser.setFileFilter(filter);
            
                int returnVal = chooser.showOpenDialog(null);
                if(returnVal == JFileChooser.APPROVE_OPTION) {
                    return chooser.getSelectedFile().getAbsolutePath();
                }
    
            return "";
        }

	public void initWorld() {
            // TODO Specify start position in CSV
            board[8][11] = new Character(0); // add character
            // TODO Read character property from character !!!
            characterX = 11; // x=11 y=8
            characterY = 8;
	}
        
        /*
        public void moveMobilElement() {
		
	}*/
	
	public void upCharacter() {
		int cell = 0;

		System.out.println("Up");
		// [y][x]
		try {
			board[characterY][characterX] = new Element(
					board[characterY][characterX].getCurrentCell());
			if (board[characterY - 1][characterX].isGoal()) {
				board[--characterY][characterX] = new CharacterOnGoal();
			} else
				board[--characterY][characterX] = new Character(cell);
		} catch (Exception e) {
			// TODO
		}

	}

	public void downCharacter() {
		int cell = 0;
		System.out.println("Down");
		try {
			cell = board[characterY + 1][characterX].getElementId();
			board[characterY][characterX] = new Element(
					board[characterY][characterX].getCurrentCell());
			if (board[characterY + 1][characterX].isGoal()) {
				board[++characterY][characterX] = new CharacterOnGoal();
			} else
				board[++characterY][characterX] = new Character(cell);
		} catch (Exception e) {
			// TODO
		}
	}

	public void leftCharacter() {
		int cell = 0;
		System.out.println("Left");
		try {
			cell = board[characterY][characterX - 1].getElementId();
			board[characterY][characterX] = new Element(
					board[characterY][characterX].getCurrentCell());
			if (board[characterY][characterX - 1].isGoal()) {
				board[characterY][--characterX] = new CharacterOnGoal();
			} else
				board[characterY][--characterX] = new Character(cell);
		} catch (Exception e) {
			// TODO
		}
	}

	public void rightCharacter() {
		int cell = 0;
		System.out.println("Right");
		try {
			cell = board[characterY][characterX + 1].getElementId();
			board[characterY][characterX] = new Element(
					board[characterY][characterX].getCurrentCell());
			if (board[characterY][characterX + 1].isGoal()) {
				board[characterY][++characterX] = new CharacterOnGoal();
				System.out.println("OKKK");
			} else
				board[characterY][++characterX] = new Character(cell);
		} catch (Exception e) {
			// TODO
		}
	}
	
	public int getLengthX(){
		return board[0].length;
	}
	
	public int getLengthY() {
		return board[1].length;
	}
	
	public Element getElement(int x, int y) {
		return board[y][x];
	}

	public void moveCharacter(int i) {

		switch (i) {

		case 1:

			if (!board[characterY - 1][characterX].isWall()) {
				upCharacter();
			}
			break;

		case 2:
			if (!board[characterY + 1][characterX].isWall()) {
				downCharacter();
			}
			break;

		case 3:
			if (!board[characterY][characterX - 1].isWall()) {
				leftCharacter();
			}
			break;

		case 4:
			if (!board[characterY][characterX + 1].isWall()) {
				rightCharacter();
			}
			break;

		default:
			break;
		}
	}
}
