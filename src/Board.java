
public class Board {
	private Element[][] board;
	
	public Board() {
		initWorld(1);
	}
	
	public void initWorld(int i) {
		board = new Element[20][20];
		initFloor();
		wallsLv1();
		goalsLv1();
		board[8][11] = new Character(); //add character
	}
	private void wallsLv1() {
		//[y][x]
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

	public void goalsLv1(){
		board[6][17] = new Goal();
		board[7][17] = new Goal();
		board[8][17] = new Goal();
		board[6][16] = new Goal();
		board[7][16] = new Goal();
		board[8][16] = new Goal();
	}
	
	public void initFloor() {
		for(int i1 = 0; i1 < board[0].length; ++i1) {
			for(int i2 = 0; i2 < board[0].length; ++i2) {
				board[i1][i2] = new Floor();
			}
		}
	}
	
	public void initWallTest() {
		for(int x = 1; x < board[0].length - 1; ++x) {
			for(int y = 1; y < board[0].length - 1; ++y) {
				board[1][y] = new Element(1); //top
				board[board[0].length-2][y] = new Wall(); //bot
				
				board[x][1] = new Element(1); //top
				board[x][board[0].length-2] = new Wall(); //bot
			}
		}
	}
	//board[y][x]
	public void initGoalTest() {
		board[2][8] = new Goal();
		board[2][9] = new Goal();
		board[2][10] = new Goal();
		board[2][11] = new Goal();
		
		board[3][8] = new Goal();
		board[3][9] = new Goal();
		board[3][10] = new Goal();
		board[3][11] = new Goal();
	}
	
	public void showBoard() {
		for (int i1 = 0; i1 < board.length; ++i1) {
			String out = "";
			for (int i2 = 0; i2 < board[i1].length; ++i2) {
				out += board[i1][i2].getElementId() + " ";
			}
			System.out.println(out);
		}
	}
	
	
}
