
public class MainClass {

	public static void main(String[] args) {
		System.out.println("              __       _         _                 \r\n  /\\/\\  _   _/ _\\ ___ | | _____ | |__   __ _ _ __  \r\n /    \\| | | \\ \\ / _ \\| |/ / _ \\| '_ \\ / _` | '_ \\ \r\n/ /\\/\\ \\ |_| |\\ \\ (_) |   < (_) | |_) | (_| | | | |\r\n\\/    \\/\\__, \\__/\\___/|_|\\_\\___/|_.__/ \\__,_|_| |_|\r\n        |___/                                      \r\n");
		Game game = new Game("Toto");
		game.getBoard().showBoard();
	}

}
