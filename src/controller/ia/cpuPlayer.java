/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.ia;

import controller.ia.model.Grid;
import controller.ia.model.Move;
import controller.ia.model.MoveResult;
import controller.ia.view.Display;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Francois
 */
public class cpuPlayer {
    
    private Grid board;
    
    cpuPlayer(){
        this.board = new Grid();
    }
    
    public static void main(String[] args){
        
        cpuPlayer cpu = new cpuPlayer();        


        long tStart = System.currentTimeMillis();        
        List<Move> answer = cpu.generateMove();
        long tEnd = System.currentTimeMillis();
        long tDelta = tEnd - tStart;
        double elapsedSeconds = tDelta / 1000.0;
        System.out.println("This took : "+tDelta+" milliseconds or "+elapsedSeconds+" seconds");
       

        Display.printBoard(cpu.getBoard());
        if(answer != null){
            System.out.println("Success !");
            cpu.printSequence(answer);
        }
        cpu.getBoard().playSequence(answer);
        Display.printBoard(cpu.getBoard());
        
    }
        
    private List<Move> generateMove(){
        List<List<Move>> theList = new ArrayList<>();
        
        Grid testBoard;
        Grid motherBoard = this.getBoard();
        boolean isSequenceOk;
        // The code for first initialisation is duplicated
        // We don't want to make this each time in genMove
        for(Move moveToTry : Move.values()){
            testBoard = motherBoard.clone();
            //System.out.println(moveToTry);

            MoveResult result = testBoard.moveCharacter(moveToTry);
            isSequenceOk = (!(result == MoveResult.BLOCKED));
            
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
            return generateMoveN1(theList,1);
        }
    }
    /**
     * @Brief called recursively. It takes into account the previous moves to explore new ones
     * @param previousMovesList 
     * @return 
     */
    private List<Move> generateMoveN1( List<List<Move>> previousMovesList, int level){
        level++;
        System.out.println("Level :" + level);
        List<List<Move>> okMoveSequenceList = new ArrayList<>();
        List<Move> testList ;
        Grid testBoard;
        MoveResult result;
        
        for(List<Move> moveList : previousMovesList){
            
            boolean atLeastOneMovePossible = false;
            for(Move move : Move.values()){
                testBoard = this.getBoard().clone();
                testList = copyMoveList(moveList);
                testList.add(move);
                result = testBoard.playSequence(testList);
                boolean isSequenceOk = (!(result == MoveResult.BLOCKED));
                
                if(isSequenceOk){
                    atLeastOneMovePossible = true;
                    //printSequence(testList);
                    if(testBoard.hasWon())
                        return testList;
                    else
                        okMoveSequenceList.add(testList);
                }
            }
            //if(atLeastOneMovePossible) maybe useless
        }
        List<Move> answer = generateMoveN1(okMoveSequenceList, level);
        return answer;
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
