/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.ia;

import controller.ia.model.Grid;
import controller.ia.model.Move;
import controller.ia.model.MoveResult;
import controller.ia.model.TreeNode;
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
        TreeNode answer = cpu.generateMove();
        long tEnd = System.currentTimeMillis();
        long tDelta = tEnd - tStart;
        double elapsedSeconds = tDelta / 1000.0;
        System.out.println("This took : "+tDelta+" milliseconds or "+elapsedSeconds+" seconds");

        Display.printBoard(cpu.getBoard());
        if(answer != null){
            System.out.println("Success !");
            cpu.printSequence(answer.getMoveList());
        }
        cpu.getBoard().playSequence(answer.getMoveList());
        Display.printBoard(cpu.getBoard());
        
    }
        
    private TreeNode generateMove(){
        //List<List<Move>> theList = new ArrayList<>();
        List<TreeNode> arL_treeNodes = new ArrayList<>();
        
        Grid testBoard;
        Grid motherBoard = this.getBoard();
        boolean isMoveOk;
        // The code for first initialisation is duplicated
        // We don't want to make this each time in genMove
        for(Move moveToTry : Move.values()){
            testBoard = motherBoard.clone();
            //System.out.println(moveToTry);

            MoveResult result = testBoard.moveCharacter(moveToTry);
            isMoveOk = (!(result == MoveResult.BLOCKED));
            
            if(isMoveOk){
                List<Move> listToAdd = new ArrayList<>();
                listToAdd.add(moveToTry);
                TreeNode newNode = new TreeNode(listToAdd,testBoard);
                //theList.add(listToAdd);
                arL_treeNodes.add(newNode);
            }
            //Display.printBoard(testBoard);
        }
        if(arL_treeNodes.isEmpty()){
            System.err.println("Unable to make first Move, cherck the grid");
            return null;
        } else {
            //return null;
            return generateMoveN1(arL_treeNodes,1);
        }
    }
    /**
     * @Brief called recursively. It takes into account the previous moves to explore new ones
     * @param previousMovesList 
     * @return 
     */
    private TreeNode generateMoveN1( List<TreeNode> p_treeNodes, int level){
        level++;
        System.out.println("Level :" + level);
        List<TreeNode> okNodeList = new ArrayList<>();
        List<Move> testList ;
        Grid testBoard;
        MoveResult result;
        
        for(TreeNode node : p_treeNodes){
            
            boolean atLeastOneMovePossible = false;
            for(Move move : Move.values()){
                testBoard = node.getGrid().clone();
                testList = copyMoveList(node.getMoveList());
                
                testList.add(move);
                result = testBoard.moveCharacter(move);
                boolean isMovePossible = (!(result == MoveResult.BLOCKED));
                
                if(isMovePossible){
                    atLeastOneMovePossible = true;
                    TreeNode newNode = new TreeNode(testList,testBoard);
                    //printSequence(testList);
                    if(testBoard.hasWon())
                        return newNode;
                    else
                        okNodeList.add(newNode);
                }
            }
            //if(atLeastOneMovePossible) maybe useless
        }
        TreeNode answer = generateMoveN1(okNodeList, level);
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
