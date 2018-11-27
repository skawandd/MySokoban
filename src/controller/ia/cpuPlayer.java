/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.ia;

import controller.ia.model.Grid;
import controller.ia.model.Move;
import controller.ia.view.Display;
import controller.tools.CSVElement;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Francois
 */
public class cpuPlayer {
    
    private Grid board;
    // private Game game;
    
    cpuPlayer(){
        //game = new Game();
        String path = CSVElement.pick_CSVLevel();
        try {
            this.board = new Grid(CSVElement.readCSVFile(6, 5, path),6,5);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(cpuPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String[] args){
        
        cpuPlayer cpu = new cpuPlayer();
        
        //List<Move> answer = new ArrayList<>(Arrays.asList(Move.RIGHT,Move.DOWN,Move.UP,Move.RIGHT));
        List<Move> answer = cpu.generateMove();
        
        cpu.printSequence(answer);
        
//cpuPlayer.generateMove();
        /*
        Display.printBoard(cpu.getBoard());
        cpu.board.moveCharacter(monMouv);
        System.out.println(monMouv);
        Display.printBoard(cpu.getBoard());
        cpu.board.moveCharacter(monMouv);
        Display.printBoard(cpu.getBoard());*/
        
    }
    
    //public static List<Move> generateMove(){    
    public List<Move> generateMove(){
        List<Move> moveSequence = new ArrayList<>();
        return generateMoves(moveSequence);
    }
    
    private List<Move> generateMoves(List<Move> trace){
        Move previousMove;
        
        if(trace.size()>=1)
            previousMove = trace.get(trace.size()-1);
        else
            previousMove = null;
        
        List<Move> sequenceToTry;
        Grid testBoard;
        
        for(Move move : Move.values()){
            // We don't want to go back to the previous tile
            if(move == previousMove)
                continue;
            //make a copy of the trace to try new moves on it
            sequenceToTry = new ArrayList<>(trace);
            sequenceToTry.add(move);
            testBoard = new Grid(this.getBoard().getGrid(),6,5); // Test the next move on a test board
            boolean validSequence = testBoard.playSequence(sequenceToTry);
            if(validSequence){
                if(testBoard.hasWon())
                    return sequenceToTry;
                else
                    generateMoves(sequenceToTry);
            }
        }
        return null;
    }
    
    /**
     * Print an array of moves on the screen
     * @param moveSequence 
     */
    private void printSequence(List<Move> moveSequence){
        /*for(int i = moveSequence.length-1;i>=0;i--){
            System.out.println(moveSequence[i]);
        }*/
        for(Move move : moveSequence){
            System.out.print(move+" ");
        }
    }
    
    
    public Grid getBoard() {
        return this.board;
    }
}
