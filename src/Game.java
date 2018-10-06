import view.Menu;

public class Game {
	private String playerName;
	private Board board;
	
	public Game(String playerName) {
		this.playerName = playerName;
		this.board = new Board();
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
	
	public void Play() {
		while(true) {
			this.getBoard().showBoard();
			this.getBoard().moveCharacter(new Menu().showMenu());
		}
	}
	
	
}
