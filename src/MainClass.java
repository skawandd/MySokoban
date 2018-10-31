import controller.Game;

public class MainClass {

	public static void main(String[] args) {
		Game game = new Game();
		game.getBoard().initWorld(1);
		game.Play();
	}
 
}
