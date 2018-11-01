package controller;

import view.Menu;

public class Game {
	private Board board;
	private int moves;
	private int lvl = 1;
	private String playerName = "Toto";
	
	public Game() {
		this.board = new Board();
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}
	
	public void showBoard() {
		System.out.println("=======================================");
		for (int i1 = 0; i1 < board.getMatrix()[0].length; ++i1) {
			String out = "";
			for (int i2 = 0; i2 < board.getMatrix()[1].length; ++i2) {
				if(board.getMatrix()[i1][i2].isEmpty())
					out += board.getMatrix()[i1][i2].getFloor().getId() + " ";
				else
					out += board.getMatrix()[i1][i2].getElement().getId() + " ";
			}
			System.out.println(out);

		}
		System.out.println("=======================================");
	}

	public int getLvl() {
		return lvl;
	}

	public void setLvl(int lvl) {
		this.lvl = lvl;
	}

	public int getMoves() {
		return moves;
	}

	public void setMoves(int moves) {
		this.moves = moves;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	
	public boolean checkWin() {
		int i = 0;
		for (int i1 = 0; i1 < board.getMatrix()[0].length; ++i1) {
			for (int i2 = 0; i2 < board.getMatrix()[1].length; ++i2) {
				if(board.getMatrix()[i1][i2].isBoxOnGoal())
					++i;
			}
		}
		if(i == 6) {
			return true;
		}
		
		return false;
	}
	
	public void Play() {
		boolean win = false;
		moves = 0;
		Menu menu = new Menu(this);
		do {
			menu.showInfo();
			++moves;
			menu.showBoard();
			if(win = checkWin())
				break;
			this.getBoard().moveCharacter(menu.showMenu());
		}while(!win);
		System.out.println("GG !!!");
	}
	
}
