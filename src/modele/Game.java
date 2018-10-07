package modele;
import view.Menu;

public class Game {
	private String playerName;
	private Board board;
	private int moves;
	private int lvl;
	
	public Game(String playerName) {
		this.playerName = playerName;
		this.board = new Board();
		this.moves = 0;
		this.lvl = 1;
	}
	public String getPlayerName() {
		return playerName;
	}
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	public Board getBoard() {
		return board;
	}
	public void setBoard(Board board) {
		this.board = board;
	}
	public int getMoves() {
		return this.moves;
	}
	public int getLvl() {
		return this.lvl;
	}
	public void Play() {
		moves = 0;
		while(true) {
			new Menu(this).showInfo();
			++moves;
			this.getBoard().showBoard();
			this.getBoard().moveCharacter(new Menu(this).showMenu());
		}
	}
	
	
}
