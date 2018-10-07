package view;

import modele.Game; 
import java.util.Scanner;

public class Menu {
	private Game game;
	
	public Menu(Game game) {
		this.game = game;
	}
	
	public int showMenu() {
		System.out.println("1.Up    2.Down\n3.Left  4.Right\n===============\n");
		Scanner sc = new Scanner(System.in); //TODO close scanner
		return sc.nextInt();
	}
	
	public void showInfo() {
		System.out.println("=======================================");
		System.out.println("Lvl"+ game.getLvl()+" | moves:"+game.getMoves()+" | Time: XX:XX"+" | "+game.getPlayerName()); //TODO timer
	}

}
