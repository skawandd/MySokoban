package modele;

public class Board {
	private Element[][] board;
	private int characterX, characterY;
	private Element nextCell;

	public Board() {
		initWorld(1);
	}

	public void initWorld(int i) {
		board = new Element[20][20];
		initFloor();
		wallsLv1();
		goalsLv1();
		boxLvl1();
		board[8][11] = new Character(11, 8); // add character
		characterX = 11; // x=11 y=8
		characterY = 8;
	}

	private void wallsLv1() {
		// [y][x]
		board[5][0] = new Wall();
		board[6][0] = new Wall();
		board[7][0] = new Wall();

		board[5][1] = new Wall();
		board[5][2] = new Wall();

		board[4][2] = new Wall();
		board[3][2] = new Wall();

		board[3][3] = new Wall();
		board[3][4] = new Wall();

		board[2][4] = new Wall();
		board[1][4] = new Wall();
		board[0][4] = new Wall();

		board[0][5] = new Wall();
		board[0][6] = new Wall();
		board[0][7] = new Wall();

		board[0][8] = new Wall();
		board[1][8] = new Wall();
		board[2][8] = new Wall();
		board[3][8] = new Wall();

		board[3][9] = new Wall();
		board[4][9] = new Wall();
		board[5][9] = new Wall();
		board[6][9] = new Wall();

		board[6][10] = new Wall();
		board[6][11] = new Wall();
		board[6][12] = new Wall();
		board[6][13] = new Wall();

		board[5][13] = new Wall();
		board[5][14] = new Wall();
		board[5][15] = new Wall();
		board[5][16] = new Wall();
		board[5][17] = new Wall();
		board[5][18] = new Wall();

		board[6][18] = new Wall();
		board[7][18] = new Wall();
		board[8][18] = new Wall();
		board[9][18] = new Wall();

		board[9][17] = new Wall();
		board[9][16] = new Wall();
		board[9][15] = new Wall();
		board[9][14] = new Wall();
		board[9][13] = new Wall();
		board[9][12] = new Wall();
		board[9][11] = new Wall();
		board[9][10] = new Wall();

		board[8][10] = new Wall();
		board[8][12] = new Wall();
		board[8][13] = new Wall();

		board[10][10] = new Wall();
		board[10][9] = new Wall();
		board[10][8] = new Wall();
		board[10][7] = new Wall();
		board[10][6] = new Wall();
		board[10][5] = new Wall();

		board[9][5] = new Wall();
		board[8][5] = new Wall();

		board[8][4] = new Wall();
		board[8][3] = new Wall();
		board[8][2] = new Wall();
		board[8][1] = new Wall();
		board[8][0] = new Wall();

		board[5][4] = new Wall();
		board[6][4] = new Wall();

		board[5][6] = new Wall();
		board[6][6] = new Wall();
		board[5][7] = new Wall();
		board[6][7] = new Wall();
	}

	public void goalsLv1() {
		board[6][17] = new Goal();
		board[7][17] = new Goal();
		board[8][17] = new Goal();
		board[6][16] = new Goal();
		board[7][16] = new Goal();
		board[8][16] = new Goal();
	}

	public void initFloor() {
		for (int i1 = 0; i1 < board[0].length; ++i1) {
			for (int i2 = 0; i2 < board[0].length; ++i2) {
				board[i1][i2] = new Floor();
			}
		}
	}

	public void boxLvl1() {
		// y;x 3;7
		board[7][2] = new Box(2, 7);

		board[2][5] = new Box(5, 2);
		board[4][5] = new Box(5, 4);
		board[7][5] = new Box(5, 7);

		board[3][7] = new Box(7, 3);
		board[4][7] = new Box(7, 4);
	}

	public void moveMobilElement(Element character, Element box, int move) {
		int currentCell = -1;
		switch (move) {
		case 2:
			if(!board[box.getPoint().getY()+1][box.getPoint().getX()].isObstacle()){
				currentCell = board[box.getPoint().getY()+1][box.getPoint().getX()].getCurrentCell();
				board[box.getPoint().getY()+1][box.getPoint().getX()] = new Box(box.getPoint().getX(), box.getPoint().getY()+1); //deplacement personnage
				board[box.getPoint().getY()+1][box.getPoint().getX()].setCurrentCell(currentCell);
				
				downCharacter();
				/*currentCell = board[box.getPoint().getY()][box.getPoint().getX()].getCurrentCell();
				board[box.getPoint().getY()][box.getPoint().getX()] = new Character(box.getPoint().getX(), box.getPoint().getY());
				board[box.getPoint().getY()][box.getPoint().getX()].setCurrentCell(currentCell);*/
				
				
			}
			break;
		case 8:

			break;
		case 4:

			break;
		case 6:

			break;

		}
	}

	public void upCharacter() {
		int cell = 0;

		System.out.println("Up");
		// [y][x]
		try {
			board[characterY][characterX] = new Element(board[characterY][characterX].getCurrentCell());
			if (board[characterY - 1][characterX].isGoal()) {
				board[--characterY][characterX] = new CharacterOnGoal(characterX, characterY);
			} else
				board[--characterY][characterX] = new Character(characterX, characterY);
		} catch (Exception e) {
			// TODO
		}

	}

	public void downCharacter() {
		int cell = 0;
		System.out.println("Down");
		try {
			cell = board[characterY + 1][characterX].getElementId();
			board[characterY][characterX] = new Element(board[characterY][characterX].getCurrentCell());
			if (board[characterY + 1][characterX].isGoal()) {
				board[++characterY][characterX] = new CharacterOnGoal(characterX, characterY);
			} else
				board[++characterY][characterX] = new Character(characterX, characterY);
		} catch (Exception e) {
			// TODO
		}
	}

	public void leftCharacter() {
		int cell = 0;
		System.out.println("Left");
		try {
			cell = board[characterY][characterX - 1].getElementId();
			board[characterY][characterX] = new Element(board[characterY][characterX].getCurrentCell());
			if (board[characterY][characterX - 1].isGoal()) {
				board[characterY][--characterX] = new CharacterOnGoal(characterX, characterY);
			} else
				board[characterY][--characterX] = new Character(characterX, characterY);
		} catch (Exception e) {
			// TODO
		}
	}

	public void rightCharacter() {
		int cell = 0;
		System.out.println("Right");
		try {
			cell = board[characterY][characterX + 1].getElementId();
			board[characterY][characterX] = new Element(board[characterY][characterX].getCurrentCell());
			if (board[characterY][characterX + 1].isGoal()) {
				board[characterY][++characterX] = new CharacterOnGoal(characterX, characterY);
				System.out.println("OKKK");
			} else
				board[characterY][++characterX] = new Character(characterX, characterY);
		} catch (Exception e) {
			// TODO
		}
	}

	public int getLengthX() {
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
			if(board[characterY + 1][characterX].isBox()){
				checkPlayerPos(board[characterY + 1][characterX]);
			}else if (!board[characterY + 1][characterX].isWall()) {
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

	private void checkPlayerPos(Element mElement) {
		if (mElement.getPoint().getX() == characterX && mElement.getPoint().getY() == characterY - 1) {
			moveMobilElement(board[characterY][characterX], mElement, 8);
		}
		if (mElement.getPoint().getX() == characterX && mElement.getPoint().getY() == characterY + 1) {
			moveMobilElement(board[characterY][characterX], mElement, 2);
		}
		if (mElement.getPoint().getX() == characterX - 1 && mElement.getPoint().getY() == characterY) {
			moveMobilElement(board[characterY][characterX], mElement, 4);
		}
		if (mElement.getPoint().getX() == characterX + 1 && mElement.getPoint().getY() == characterY) {
			moveMobilElement(board[characterY][characterX], mElement, 6);
		}

	}

}
