package defaultPackage;

import controller.Game;

public class MainClass {

	public static void main(String[] args) {
		Game game = new Game(args);
		new Thread(game).start();
		game.Play();
	}
 
}
