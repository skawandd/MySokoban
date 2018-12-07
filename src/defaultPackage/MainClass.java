package defaultPackage;

import controller.Game;
import javafx.application.Application;
import view.UserInterface;

public class MainClass {

	public static void main(String[] args) {
		/*Game game = new Game(args);
		new Thread(game).start();
		game.Play();*/
		Application.launch(UserInterface.class, args);
	}
 
}
