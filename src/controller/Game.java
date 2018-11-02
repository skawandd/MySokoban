package controller;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

import view.Menu;

public class Game {
	private Board board;
	private int moves;
	private int lvl = 1;
	private String playerName = "Toto";
	ZonedDateTime start;
	
	
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
	
	public void askPlayerName() {
		System.out.println("Enter name:");
		Scanner sc = new Scanner(System.in);
		this.playerName = sc.nextLine();
		sc.close();
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	
	public String getStopWatch(long seconds){
		int resultatEuclide = (int)seconds/60;
		int reste = (int)seconds%60;
		String buffer;
		if(resultatEuclide < 10)
			buffer = "0"+resultatEuclide;
		else
			buffer = ""+resultatEuclide;
		if(reste < 10)
			buffer += ":0"+reste;
		else
			buffer += ":"+reste;
		return buffer;
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
		this.start = ZonedDateTime.now();
		Scanner sc = null;
		boolean win = false;
		moves = 0;
		Menu menu = new Menu(this);
		do {
			menu.showInfo(this.getStopWatch(start.until(ZonedDateTime.now(), ChronoUnit.SECONDS)));
			++moves;
			menu.showBoard();
			if(win = checkWin())
				break;
			menu.showActions();
			sc = new Scanner(System.in);
			this.getBoard().moveCharacter(sc.nextInt());
		}while(!win);
		System.out.println("GG !!!");
		sc.close();
	}
	
}
