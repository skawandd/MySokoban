/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.ia;

import modele.Grid;
import modele.Move;
import modele.MoveResult;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Francois
 */
public class CPUPlayer {
    
    private Grid grid;
    private long tStart;
    private long tEnd;
    private long tDelta;
    private double elapsedSeconds;
    private List<Move> solution;

    /**
     * CPUPlayer creation
     * @param p_grid The grid to resolve
     */
    public CPUPlayer(Grid p_grid){
        this.grid = p_grid;
        this.settStart();
        this.solution = generateMove();
        this.settEnd();
        this.settDelta();
    }

    /**
     * @return Solution List of Move
     */
    public List<Move> getSolution() {
        return solution;
    }

    /**
     * @return startTime
     */
    public long gettStart() {
        return tStart;
    }
    /**
     * @return EndTime
     */
    public long gettEnd() {
        return tEnd;
    }
    /**
     * @return Resolution duration
     */
    public long gettDelta() {
        return tDelta;
    }
    /**
     * Timer init
     */
    private void settStart() {
        this.tStart = System.currentTimeMillis();
    }
    /**
     * stop timer
     */
    private void settEnd() {
        this.tEnd = System.currentTimeMillis();
    }
    /**
     * compute duration
     */
    private void settDelta() {
        this.tDelta = this.tEnd - this.tStart;
        this.elapsedSeconds = this.tDelta / 1000.0;
    }
    /**
     * Recursive method exploring all possible moves
     * @return the move sequence that solve the Grid
     */
    private List<Move> generateMove(){
        List<List<Move>> theList = new ArrayList<>();
        
        Grid testBoard;
        Grid motherBoard = this.grid;
        boolean isSequenceOk;
        // The code for first initialisation is duplicated
        // We don't want to make this each time in genMove
        for(Move moveToTry : Move.values()){
            testBoard = motherBoard.clone();

            MoveResult result = testBoard.moveCharacter(moveToTry);
            isSequenceOk = (!(result == MoveResult.BLOCKED));
            
            if(isSequenceOk){
                List<Move> listToAdd = new ArrayList<>();
                listToAdd.add(moveToTry);
                theList.add(listToAdd);
            }
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
     * @return the sequence of move once it has been found
     */
    private List<Move> generateMoveN1( List<List<Move>> previousMovesList, int level){
        level++;
        List<List<Move>> okMoveSequenceList = new ArrayList<>();
        List<Move> testList ;
        Grid testBoard;
        MoveResult result;
        
        for(List<Move> moveList : previousMovesList){
            
            boolean atLeastOneMovePossible = false;
            for(Move move : Move.values()){
                testBoard = this.grid.clone();
                testList = copyMoveList(moveList);
                testList.add(move);
                result = testBoard.playSequence(testList);
                boolean isSequenceOk = (!(result == MoveResult.BLOCKED));
                
                if(isSequenceOk){
                    atLeastOneMovePossible = true;
                    if(testBoard.hasWon())
                        return testList;
                    else
                        okMoveSequenceList.add(testList);
                }
            }
        }
        List<Move> answer = generateMoveN1(okMoveSequenceList, level);
        return answer;
    }
    
    /**
     * List duplication
     * @param originalList
     * @return the brand new created List
     */
    private List<Move> copyMoveList(List<Move> originalList){
        List<Move> copyList = new ArrayList<>();
        
        for(Move move : originalList){
            copyList.add(move);
        }
        
        return copyList;
    }

}
