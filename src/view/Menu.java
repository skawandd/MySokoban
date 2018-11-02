package view;

import controller.Game;

import java.util.Scanner;

public class Menu {
	private Game game;
	
	public Menu(Game game) {
		this.game = game;
	}
	
	public void showActions() {
		System.out.println("1.Up    3.Left  | 5.Restart\n2.Down  4.Right | 6.Exit\n=======================================\n");
	//	System.out.println("1.Up    2.Down\n3.Left  4.Right\n===============\n");
	}
	
	public void showInfo(String time) {
		System.out.println("=======================================");
		System.out.println("Lvl"+ game.getLvl()+" | Moves:"+game.getMoves()+" | Timer: "+time+" | "+game.getPlayerName()); //TODO timer
	}
	
	public void showBoard() {
		System.out.println("=======================================");
		for (int i1 = 0; i1 < game.getBoard().getMatrix()[0].length; ++i1) {
			String out = "";
			for (int i2 = 0; i2 < game.getBoard().getMatrix()[1].length; ++i2) {
				if(game.getBoard().getMatrix()[i1][i2].isEmpty())
					out += game.getBoard().getMatrix()[i1][i2].getFloor().getId() + " ";
				else
					out += game.getBoard().getMatrix()[i1][i2].getElement().getId() + " ";
			}
			System.out.println(out);

		}
		System.out.println("=======================================");
	}
	
	
}
