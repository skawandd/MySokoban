/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.ia.model;

/**
 *
 * @author Francois
 */
public class grid {
    
    private byte[][] grid;
    private byte x;
    private byte y;

    public byte[][] getGrid() {
        return this.grid;
    }
    
    public grid(byte[][] p_Board){
        this.grid = p_Board;
        
        // Ensure the grid is secure
    }
    
    // getEmptyGoalsLeft
    
    // getBoxesOnGround
    
    private void checkForItems(){
        
    }
}
