/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

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
    
    /** return teh move associated to the number
    * @param number
    * @return 
    */
    public static Move getFromNumber(int number){
        switch(number){
            case 1:
                return UP;
            case 2 :
                return DOWN;
            case 3 :
                return LEFT;
            case 4 :
                return RIGHT;
            default :
                return null;
        }
    }
}
