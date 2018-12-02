/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.ia.view;

import controller.ia.model.Grid;

/**
 *
 * @author Francois
 */
public final class Display {
    
    public static void printBoard(Grid grid){
        int p_X = grid.getX(),
                p_Y = grid.getY();
        byte[][] p_Board = grid.getGrid();
        char[] symbols = {'.','#','x','X','o','m','M'};
                      
        for(int y=0; y < p_Y; y++){
            for(int x = 0; x < p_X; x++){
                int valBoard = p_Board[x][y];
                System.out.print(symbols[valBoard]);                
            }
            System.out.println();
        }
    }
}
