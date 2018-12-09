package controller;

import controller.ia.CPUPlayer;
import controller.tools.CSVElement;
import modele.Grid;
import modele.Move;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Scanner;
import javafx.event.EventTarget;

public class GameController /*implements Runnable*/{
        private Grid grid;
	private int lvl = 1; //Init import CSV
	private String playerName;
	private ZonedDateTime start;
        private CPUPlayer cpu;
        private EventTarget eventTarget;
        private List<Move> solution;

    public List<Move> getSolution() {
        return solution;
    }
        
	public GameController(CSVElement csv) {
            this.grid = new Grid(csv);
            this.playerName = "MARIO";
            this.start = ZonedDateTime.now();
	}
        public void solve() {
		try {
                    this.cpu = new CPUPlayer(this.grid.clone());
                    this.solution = this.cpu.getSolution();
                    this.lvl = this.solution.size();
		} catch (Exception e) {
                    System.err.print(e);
		}
        }
        
        public Grid getGrid() {
            return grid;
        }

        public void setGrid(Grid grid) {
            this.grid = grid;
        }
	
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
		this.grid.moveCharacter(move);
	}
	
	public boolean checkWin() {		
		return grid.hasWon();
	}
	
	/*public void Play() {
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
                    Menu.printBoard(grid);
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
			IHMController.main(null);
		sc.close();
	}
        
        private void execCommand(int command){
            if(command > 0 && command <=4){
                Move move = Move.getFromNumber(command);
                this.getGrid().moveCharacter(move);
            } else{
                switch (command){
                    case 5 :
                        IHMController.main(null);
                        break;
                    case 6 :
                        System.exit(0);
                }
            }
        }
        
        public void setEventTarget(EventTarget p_et){
            this.eventTarget = p_et;
        }
        
        @Override
	public void run() {
		try {
			this.cpu = new CPUPlayer(this.grid.clone(), this.eventTarget);
                        this.solution = this.cpu.getSolution();
                        this.play();
		} catch (Exception e) {
                    System.err.print(e);
		}
	}

        public void play(){
            this.solve();
            if(this.solution != null){
                KeyCode keyCode;
                KeyEvent keyEvent;
                for(Move move : this.solution){
                    wait(2000);
                    switch(move){
                        case UP:
                            keyCode = KeyCode.UP;;
                            break;
                        case DOWN:
                            keyCode = KeyCode.DOWN;
                            break;
                        case LEFT:
                            keyCode = KeyCode.LEFT;
                            break;
                        case RIGHT:
                            keyCode = KeyCode.RIGHT;
                            break;
                        default: 
                            keyCode = null;
                    }
                    keyEvent = new KeyEvent(KeyEvent.KEY_PRESSED, move.toString(), "", keyCode, false, false, false, false);
                    KeyEvent.fireEvent(this.eventTarget, keyEvent);
                }
            }
        }*/

	public String getTimer() {
		return this.getStopWatch((int)start.until(ZonedDateTime.now(), ChronoUnit.SECONDS));
	}
	
}
