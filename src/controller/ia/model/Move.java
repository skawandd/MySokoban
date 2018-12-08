/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.ia.model;

/**
 *
 * @author francoois
 */
public enum Move {
    UP,
    RIGHT,
    DOWN,
    LEFT;
    
    public Move getOpposite(){
        
        switch(this){
            case UP:
                return DOWN;
            case DOWN :
                return UP;
            case LEFT :
                return RIGHT;
            case RIGHT :
                return LEFT;
            default :
                return null;
        }
    }
}
