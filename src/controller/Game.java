package controller;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

import view.Menu;

public class Game {
	private Board board;
	private int moves;
	private int lvl = 1; //Init import CSV
	private String playerName;
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
	
	public String getStopWatch(int seconds){
		int resultatEuclide = seconds/60;
		int reste = seconds%60;
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
		if(i == board.getGoalNb()) {
			return true;
		}
		
		return false;
	}
	
	public void Play() {
		Menu menu = new Menu(this);
		menu.askPlayerName();
		Scanner sc = new Scanner(System.in);
		this.playerName = sc.nextLine().toUpperCase();
		if(this.playerName.length() > 6)
			this.playerName = this.playerName.substring(0, 6);
		this.start = ZonedDateTime.now();
		boolean win = false;
		moves = 0;
		do {
			menu.showInfo(this.getStopWatch((int)start.until(ZonedDateTime.now(), ChronoUnit.SECONDS)));
			menu.showBoard();
			if(win = checkWin())
				break;
			++moves;
			menu.showActions();
			sc = new Scanner(System.in);
			this.getBoard().moveCharacter(sc.nextInt());
		}while(!win);
		menu.showVictory(playerName, this.getStopWatch((int)start.until(ZonedDateTime.now(), ChronoUnit.SECONDS)), moves);
		sc.close();
	}
	
}
