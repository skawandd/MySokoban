package modele;

public class Board {
	private Cell[][] board;
	private int characterX, characterY;

	public Board() {
		this.board = new Cell[20][20];
	}

	public void initWorld(int lvl) {
		if (lvl == 1) {
			initFloorLvl1();
			initWallLvl1();
			initBoxLvl1();
			initGoalLvl1();
			characterY = 8;
			characterX = 11;
			board[characterY][characterX].setElement(new Character());
		}
	}

	public void initFloorLvl1() {
		for (int i1 = 0; i1 < board[0].length; ++i1) {
			for (int i2 = 0; i2 < board[0].length; ++i2) {
				board[i1][i2] = new Cell(new Empty(), new Floor());
			}
		}
	}

	public void initWallLvl1() {
		board[5][0].setElement(new Wall());
		board[6][0].setElement(new Wall());
		board[7][0].setElement(new Wall());

		board[5][1].setElement(new Wall());
		board[5][2].setElement(new Wall());

		board[4][2].setElement(new Wall());
		board[3][2].setElement(new Wall());

		board[3][3].setElement(new Wall());
		board[3][4].setElement(new Wall());

		board[2][4].setElement(new Wall());
		board[1][4].setElement(new Wall());
		board[0][4].setElement(new Wall());

		board[0][5].setElement(new Wall());
		board[0][6].setElement(new Wall());
		board[0][7].setElement(new Wall());

		board[0][8].setElement(new Wall());
		board[1][8].setElement(new Wall());
		board[2][8].setElement(new Wall());
		board[3][8].setElement(new Wall());

		board[3][9].setElement(new Wall());
		board[4][9].setElement(new Wall());
		board[5][9].setElement(new Wall());
		board[6][9].setElement(new Wall());

		board[6][10].setElement(new Wall());
		board[6][11].setElement(new Wall());
		board[6][12].setElement(new Wall());
		board[6][13].setElement(new Wall());

		board[5][13].setElement(new Wall());
		board[5][14].setElement(new Wall());
		board[5][15].setElement(new Wall());
		board[5][16].setElement(new Wall());
		board[5][17].setElement(new Wall());
		board[5][18].setElement(new Wall());

		board[6][18].setElement(new Wall());
		board[7][18].setElement(new Wall());
		board[8][18].setElement(new Wall());
		board[9][18].setElement(new Wall());

		board[9][17].setElement(new Wall());
		board[9][16].setElement(new Wall());
		board[9][15].setElement(new Wall());
		board[9][14].setElement(new Wall());
		board[9][13].setElement(new Wall());
		board[9][12].setElement(new Wall());
		board[9][11].setElement(new Wall());
		board[9][10].setElement(new Wall());

		board[8][10].setElement(new Wall());
		board[8][12].setElement(new Wall());
		board[8][13].setElement(new Wall());

		board[10][10].setElement(new Wall());
		board[10][9].setElement(new Wall());
		board[10][8].setElement(new Wall());
		board[10][7].setElement(new Wall());
		board[10][6].setElement(new Wall());
		board[10][5].setElement(new Wall());

		board[9][5].setElement(new Wall());
		board[8][5].setElement(new Wall());

		board[8][4].setElement(new Wall());
		board[8][3].setElement(new Wall());
		board[8][2].setElement(new Wall());
		board[8][1].setElement(new Wall());
		board[8][0].setElement(new Wall());

		board[5][4].setElement(new Wall());
		board[6][4].setElement(new Wall());

		board[5][6].setElement(new Wall());
		board[6][6].setElement(new Wall());
		board[5][7].setElement(new Wall());
		board[6][7].setElement(new Wall());
	}

	public void initBoxLvl1() {
		board[7][2].setElement(new BoxOnFloor());

		board[2][5].setElement(new BoxOnFloor());
		board[4][5].setElement(new BoxOnFloor());
		board[7][5].setElement(new BoxOnFloor());

		board[3][7].setElement(new BoxOnFloor());
		board[4][7].setElement(new BoxOnFloor());
	}

	public void initGoalLvl1() {
		board[6][17].setFloor(new Goal());
		board[7][17].setFloor(new Goal());
		board[8][17].setFloor(new Goal());
		board[6][16].setFloor(new Goal());
		board[7][16].setFloor(new Goal());
		board[8][16].setFloor(new Goal());
	}

	public Cell[][] getMatrix() {
		return this.board;
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

		}
	}
	
	public void upCharacter() {
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
	
	public void downCharacter() {
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
	
	public void rightCharacter() {
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
	
	public void leftCharacter() {
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
