/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.ia.view;

/**
 *
 * @author Francois
 */
public final class Display {
    /*
    public enum Tiles{
        SOL(0, '.'),
        MUR(1,'#'),
        CAISSE(2,'X'),
        CAISSE_ON_GOAL(3,'x'),
        GOAL(4,'o'),
        MARIO(5,'m'),
        MARIO_ON_GOAL(6,'M');
        
        public final int code;
        public final char representation;
        
        Tiles(int pCode, char pRepresentation){
            this.code = pCode;
            this.representation = pRepresentation;
        }
        
        public char getRep(){
            return this.representation;
        }
    }*/
    private Display(){
        
    }
    
    public static void printBoard(int p_X, int p_Y, byte[][] p_Board){
        char[] symbols = {'.','#','X','x','o','m','M'};
                      
        for(int y=0; y < p_Y; y++){
            for(int x = 0; x < p_X; x++){
                int valBoard = p_Board[x][y];
                System.out.print(symbols[valBoard]);                
            }
            System.out.println();
        }
    }
    
}
