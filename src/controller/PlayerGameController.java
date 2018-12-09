/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.tools.CSVElement;
import java.util.Scanner;
import modele.Move;
import view.Display;

public class PlayerGameController extends GameController {

    private String playerName;

    public PlayerGameController(CSVElement csv) {
        super(csv);
        this.playerName = "MARIO";
    }
    
    /**
     * Increment nbMoves property
     */
    protected void incrementNbMoves(){
        this.setNbMoves(this.getNbMoves()+1);
    }
    
    public void Play() {
        Display menu = new Display(this);
        Scanner sc = new Scanner(System.in);
        if (this.playerName.length() > 6) {
            this.playerName = this.playerName.substring(0, 6);
        }

        boolean win = false;
        do {
            int answer;
            menu.showInfo(this.getTimer());
            //menu.showBoard();
            Display.printBoard(grid);
            if (win = grid.hasWon()) {
                break;
            }
            incrementNbMoves();
            menu.showActions();

            sc = new Scanner(System.in);
            answer = sc.nextInt();
            execCommand(answer);

        } while (!win);
        menu.showVictory(playerName, this.getTimer(), getNbMoves());
        menu.showRestart();
        if (sc.nextInt() == 1) {
            IHMController.main(null);
        }
        sc.close();
    }

    private void execCommand(int command) {
        if (command > 0 && command <= 4) {
            Move move = Move.getFromNumber(command);
            this.getGrid().moveCharacter(move);
        } else {
            switch (command) {
                case 5:
                    IHMController.main(null);
                    break;
                case 6:
                    System.exit(0);
            }
        }
    }
        /**
     * Ask the player for his name
     */
    protected void askPlayerName() {
        System.out.println("Enter name:");
        Scanner sc = new Scanner(System.in);
        this.playerName = sc.nextLine();
        sc.close();
    }

    /**
     * @return playerName for the Game
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * @param playerName new playerName to set
     */
    protected void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
}
