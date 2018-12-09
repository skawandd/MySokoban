/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

/**
 * Position in the grid with 2 coordinates
 */
public final class Position {
    
    private int X;
    private int Y;
    
    /**
     * @param p_x new X
     * @param p_y new Y
     */
    public Position(int p_x, int p_y){
        this.setX(p_x);
        this.setY(p_y);
    }
    /**
     * Copy constructor
     * @param p_position Position from which to copy
     */
    public Position(Position p_position){
        this.setX(p_position.getX());
        this.setY(p_position.getY());
    }
    /**
     * @return X value
     */
    public int getX() {
        return X;
    }
    /** 
     * @param X new X value
     */
    public void setX(int X) {
        this.X = X;
    }
    /**
     * @return Y value
     */
    public int getY() {
        return Y;
    }
    /**
     * @param Y new Y value
     */
    public void setY(int Y) {
        this.Y = Y;
    }
    
    
    
}
