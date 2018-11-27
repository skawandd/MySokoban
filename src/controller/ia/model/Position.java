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
public final class Position {
    
    private int X;
    private int Y;
    
    public Position(int p_x, int p_y){
        this.setX(p_x);
        this.setY(p_y);
    }
    
    public int getX() {
        return X;
    }

    public void setX(int X) {
        this.X = X;
    }

    public int getY() {
        return Y;
    }

    public void setY(int Y) {
        this.Y = Y;
    }
    
    
    
}
