package controller;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

import defaultPackage.MainClass;
import view.Menu;

public class Game implements Runnable{
	private Board board;
	private int moves;
	private int lvl = 1; //Init import CSV
	private String playerName;
	private ZonedDateTime start;
	
	
	public Game(/*String[] args*/) {
		this.board = new Board();
		this.playerName = "MARIO";
		this.start = ZonedDateTime.now();
		this.moves = 0;
	//	this.arguments = args;
		
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
		return this.board.getMoves();
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
		int euclide = seconds/60;
		int rest = seconds%60;
		String buffer;
		if(euclide < 10)
			buffer = "0"+euclide;
		else
			buffer = ""+euclide;
		if(rest < 10)
			buffer += ":0"+rest;
		else
			buffer += ":"+rest;
		return buffer;
	}
	
	public void move (int i) {
		this.board.moveCharacter(i);
		
		new Menu(this).showInfo(this.getTimer());
		new Menu(this).showBoard();
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
//		menu.askPlayerName();
		Scanner sc = new Scanner(System.in);
//		this.playerName = sc.nextLine().toUpperCase();
		if(this.playerName.length() > 6)
			this.playerName = this.playerName.substring(0, 6);
		this.start = ZonedDateTime.now();
		boolean win = false;

		do {
			menu.showInfo(this.getTimer());
			menu.showBoard();
			if(win = checkWin())
				break;
			++moves;
			menu.showActions();
			sc = new Scanner(System.in);
			try {
				this.getBoard().moveCharacter(sc.nextInt());
			} catch (Exception e){
			}
		}while(!win);
		menu.showVictory(playerName, this.getTimer(), moves);
		menu.showRestart();
		if(sc.nextInt() == 1)
			MainClass.main(null);
		sc.close();
	}

	public void run() {
		try {
			System.out.println("Thread");
			this.Play();
		} catch (Exception e) {
		}
	}

	public String getTimer() {
		return this.getStopWatch((int)start.until(ZonedDateTime.now(), ChronoUnit.SECONDS));
	}

	
}
