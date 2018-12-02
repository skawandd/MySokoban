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
        //List<Move> answer = cpu.generateMove();
        
        List<Move> answer = cpu.genMove();
        
        //cpu.printSequence(answer);
        
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
    
    /**
     * @Brief called recursively. It takes into account the previous moves to explore new ones
     * @param trace
     * @return 
     */
    private List<Move> generateMoves(List<Move> trace){
        Move previousMove;
        // Get previous Move, null if first call
        int level = trace.size();
        if(level>0)
            previousMove = trace.get(level-1);
        else
            previousMove = null;
        
        List<Move> sequenceToTry;
        Grid testBoard;
        Grid motherBoard = this.getBoard();
        int X_size = motherBoard.getX();
        int Y_size = motherBoard.getY();
        
        for(Move move : Move.values()){
            // We don't want to move back to the previous tile
            if(previousMove != null && move == previousMove.getOpposite())
                continue;
            
            //make a copy of the trace to try new moves on it
            sequenceToTry = new ArrayList<>(trace);
            sequenceToTry.add(move);
            //printSequence(sequenceToTry);
            
            //testBoard = new Grid(motherBoard.getGrid(),X_size,Y_size); // Test the next move on a test board
            testBoard = motherBoard.clone();
            System.out.println("New Board :");
            Display.printBoard(testBoard);
            System.out.println();
            
            boolean isSequenceOk = testBoard.playSequence(sequenceToTry, true);
            
            if(isSequenceOk){
                printSequence(sequenceToTry);
                if(testBoard.hasWon()){
                    printSequence(sequenceToTry);
                    System.out.println("Gagné !");
                    return sequenceToTry;
                }
                else{
                    // TODO : Test if deadlocks
                    System.out.print("SEQUENCE OK, going deeper :\n");
                    generateMoves(sequenceToTry);
                }
            } else {
                System.out.print("BAD SEQUENCE : ");
                printSequence(sequenceToTry);
            }
            System.out.println("\t next");
            // Else don't take this move into account 
        }
        return null;
    }
    
    private List<Move> genMoveN1( List<List<Move>> previousMovesList){
        List<List<Move>> okMoveSequenceList = new ArrayList<>();
        List<Move> testList ;
        Grid testBoard;
        for(List<Move> moveList : previousMovesList){
            
            boolean atLeastOneMovePossible = false;
            for(Move move : Move.values()){
                testBoard = this.getBoard().clone();
                testList = copyMoveList(moveList);
                testList.add(move);
                boolean isSequenceOk = testBoard.playSequence(testList, false);
                
                if(isSequenceOk){
                    atLeastOneMovePossible = true;
                    if(testBoard.hasWon())
                        return testList;
                    else
                        okMoveSequenceList.add(testList);
                }
            }
            //if(atLeastOneMovePossible) maybe useless
        }
        List<Move> answer = genMoveN1(okMoveSequenceList);
        return answer;
    }
    
    private List<Move> genMove(){
        List<List<Move>> theList = new ArrayList<>();
        
        Grid testBoard;
        Grid motherBoard = this.getBoard();
        
        // The code for first initialisation is duplicated
        // We don't want to make this each time in genMove
        for(Move moveToTry : Move.values()){
            testBoard = motherBoard.clone();
            System.out.println(moveToTry);

            boolean isSequenceOk = testBoard.moveCharacter(moveToTry);
            
            if(isSequenceOk){
                List<Move> listToAdd = new ArrayList<>();
                listToAdd.add(moveToTry);
                theList.add(listToAdd);
            }
            //Display.printBoard(testBoard);

        }
        if(theList.isEmpty()){
            System.err.println("Unable to make first Move, cherck the grid");
            return null;
        } else {
            //return null;
            return genMoveN1(theList);
        }
    }
    
    private List<Move> copyMoveList(List<Move> originalList){
        List<Move> copyList = new ArrayList<>();
        
        for(Move move : originalList){
            copyList.add(move);
        }
        
        return copyList;
    }

    /**
     * Print an array of moves on the screen
     * @param moveSequence 
     */
    private void printSequence(List<Move> moveSequence){
        /*for(int i = moveSequence.length-1;i>=0;i--){
        System.out.println(moveSequence[i]);
        }*/
        if(moveSequence.size()>0){
            moveSequence.forEach((move) -> {
                System.out.print(move+" ");
            });
            System.out.println();
        }
    }
    
    
    public Grid getBoard() {
        return this.board;
    }
}
