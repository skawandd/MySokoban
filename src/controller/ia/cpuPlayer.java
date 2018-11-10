/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.ia;

import controller.Game;
import controller.Board;
import controller.ia.view.Display;
import controller.tools.CSVElement;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Francois
 */
public class cpuPlayer {
    
    private Board board;
    // private Game game;
    
    cpuPlayer(){
        //game = new Game();
        String path = CSVElement.pick_CSVLevel();
        try {
            this.board = CSVElement.readCSVFile(6, 5, path);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(cpuPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public int[][] getBoard() {
        return board;
    }
    
    public static void main(String[] args){
        
        cpuPlayer cpu = new cpuPlayer();
        Display.printBoard(6, 5, cpu.getBoard());
        
    }
    
}
