package view;

import controller.Game;

public class Menu {
	private Game game;
	
	public Menu(Game game) {
		this.game = game;
	}
	
	public void askPlayerName() {
		System.out.println("Enter name:");
	}
	
	public void showActions() {
		System.out.println("1.Up    3.Left  | 5.Restart\n2.Down  4.Right | 6.Exit\n=======================================\n");
	}
	
	public void showInfo(String time) {
		System.out.println("=======================================");
		System.out.println("Lvl"+ game.getLvl()+" | Moves: "+game.getMoves()+" | Timer: "+time+" | "+game.getPlayerName());
	}
	
	public void showVictory(String playerName, String time, int moves) {
		System.out.println("Congrats " + playerName + " you won!\nYou made " + moves + " moves in " + time+"\n");
	}
	
	public void showRestart() {
		System.out.println("Restart?\n1.Yes 2.No\n");
	}
	
	public void showBoard() {
		System.out.println("=======================================");
		for (int i1 = 0; i1 < game.getBoard().getMatrix()[0].length; ++i1) {
			String out = "";
			for (int i2 = 0; i2 < game.getBoard().getMatrix()[1].length; ++i2) {
				if(game.getBoard().getMatrix()[i1][i2].isEmpty()) {
					if(game.getBoard().getMatrix()[i1][i2].isGoal())
						out += game.getBoard().getMatrix()[i1][i2].getFloor().getId() + " ";
					else
						out += ". ";
				}
				else
					out += game.getBoard().getMatrix()[i1][i2].getElement().getId() + " ";
			}
			System.out.println(out);

		}
		System.out.println("=======================================");
	}
	
	
}