package controller;

import controller.ia.model.Grid;
import controller.ia.model.Move;
import controller.ia.view.Display;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

import defaultPackage.MainClass;

import view.Menu;

public class Game implements Runnable{
	private Board board;
        private Grid grid;
	private int moves;
	private int lvl = 1; //Init import CSV
	private String playerName;
	private ZonedDateTime start;
	
	
	public Game(/*String[] args*/) {
            this.grid = new Grid();
            this.playerName = "MARIO";
            this.start = ZonedDateTime.now();
            this.moves = 0;		
	}

        public Grid getGrid() {
            return grid;
        }

        public void setGrid(Grid grid) {
            this.grid = grid;
        }
        /*
	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}*/
	
	public int getLvl() {
		return lvl;
	}

	public void setLvl(int lvl) {
		this.lvl = lvl;
	}

	public int getMoves() {
		return this.grid.getNbMoves();
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
	
	public void move (Move move) {
		/*this.board.moveCharacter(i);*/
		this.grid.moveCharacter(move);
                // TODO RAF ici
		new Menu(this).showInfo(this.getTimer());
		Menu.printBoard(grid);
	}
	
	public boolean checkWin() {		
		return grid.hasWon();
	}
	
	public void Play() {
		Menu menu = new Menu(this);
		Scanner sc = new Scanner(System.in);
		if(this.playerName.length() > 6)
			this.playerName = this.playerName.substring(0, 6);
		this.start = ZonedDateTime.now();
                
		boolean win = false;
		do {
                    int answer;
                    menu.showInfo(this.getTimer());
                    //menu.showBoard();
                    Display.printBoard(grid);
                    if(win = grid.hasWon())
                            break;
                    ++moves;
                    menu.showActions();
                    
                    sc = new Scanner(System.in);
                    answer = sc.nextInt();
                    execCommand(answer);

		}while(!win);
		menu.showVictory(playerName, this.getTimer(), moves);
		menu.showRestart();
		if(sc.nextInt() == 1)
			MainClass.main(null);
		sc.close();
	}
        
        private void execCommand(int command){
            if(command > 0 && command <=4){
                Move move = Move.getFromNumber(command);
                this.getGrid().moveCharacter(move);
            } else{
                switch (command){
                    case 5 :
                        MainClass.main(null);
                        break;
                    case 6 :
                        System.exit(0);
                }
            }
        }
        

	public void run() {
		try {
			System.out.println("Thread");
			this.Play();
		} catch (Exception e) {
                    System.err.print(e);
		}
	}

	public String getTimer() {
		return this.getStopWatch((int)start.until(ZonedDateTime.now(), ChronoUnit.SECONDS));
	}

	
}
