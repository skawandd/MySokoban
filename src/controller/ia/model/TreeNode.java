/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.ia.model;

import java.util.List;

/**
 *
 * @author benjamin
 */
public class TreeNode {
    
    private final List<Move> moveList;
    private final Grid grid;
    
    public TreeNode(List<Move> p_moves, Grid p_grid){
        this.moveList = p_moves;
        this.grid = p_grid.clone();
    }

    public List<Move> getMoveList() {
        return moveList;
    }

    public Grid getGrid() {
        return grid;
    }
    
    
    
}
