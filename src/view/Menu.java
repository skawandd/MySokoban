package view;

import java.util.Scanner;

public class Menu {

	public Menu() {
		// TODO Auto-generated constructor stub
	}
	
	public int showMenu() {
		System.out.println("1.Up    2.Down\n3.Left  4.Right\n");
		Scanner sc = new Scanner(System.in);
		return sc.nextInt();
	}

}
