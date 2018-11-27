/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.ia.model;

import java.util.List;

/**
 *
 * @author Francois
 */
public class Grid {
    
    private byte[][] grid;
    private final int x;
    private final int y;
    private final int[] pushable = {Tile.BOX,Tile.BOX_ON_GOAL};
    private final int[] crossable ={Tile.SOL,Tile.GOAL};
    
    private Position characterPosition;

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    private void setCharacterPosition(Position characterPosition) {
        this.characterPosition = characterPosition;
    }
    public Position getCharacterPosition() {
        return characterPosition;
    }
    
    public byte[][] getGrid() {
        return this.grid;
    }
    
    public Grid(byte[][] p_Board, int p_x, int p_y){
        this.grid = p_Board;
        this.x = p_x;
        this.y = p_y;
        // Ensure the Grid is secure
        findInitialCharacterPosition();
    }
    
    private byte getItem(Position p_position){
        return this.grid[p_position.getX()][p_position.getY()];
    }
    
    private void setItem(Position position, byte item){
        this.grid[position.getX()][position.getY()] = item;
    }
    
    private void findInitialCharacterPosition(){
        for(int var_y=0; var_y < this.y; var_y++){
            for(int var_x = 0; var_x < this.x; var_x++){
                int itemInGrid = this.grid[var_x][var_y];
                if(itemInGrid == 5 || itemInGrid == 6){
                    this.characterPosition = new Position(var_x,var_y);
                    return;
                }
            }
        }
        System.err.println("Error : No character found on grid");
    }
    
    private int getEmptyGoalsLeft(){
        int nbEmptyGoals = 0;
        for(int var_y=0; var_y < this.y; var_y++){
            for(int var_x = 0; var_x < this.x; var_x++){
                int itemInGrid = this.grid[var_x][var_y];
                if(itemInGrid == 4){
                    nbEmptyGoals++;
                }
            }
        }
        return nbEmptyGoals;
    }
    
    public boolean hasWon(){
        return (getEmptyGoalsLeft() == 0);
    }

// getBoxesOnGround
       
    private Position getNeighborPosition(Position p_pos, Move direction){
        int x = p_pos.getX();
        int y = p_pos.getY();
        
        switch(direction){
            case UP:
                return new Position(x,y-1);
            case RIGHT:
                return new Position(x+1,y);
            case DOWN:
                return new Position(x,y+1);
            case LEFT :
                return new Position(x-1,y);
        }
        return null;
    }
    
    public boolean moveCharacter(Move direction){
        
        Position neighborTile = getNeighborPosition(this.getCharacterPosition(), direction);
        
        if(isCrossable(neighborTile)){
            moveItem(this.characterPosition, neighborTile);
            return true;
        }else if (isPushable(neighborTile, direction)){
            Position neighborNeighbor = getNeighborPosition(neighborTile, direction);
            moveItem(neighborTile,neighborNeighbor);
            moveItem(this.characterPosition, neighborTile);
            return true;
        } else {
            return false;
        }
        // TODO : handle out of grid
    }
        /**
     * play an array of Move on the board
     * @param moveSequence 
     * @return false if a move was not successfull
     */
    public boolean playSequence(List<Move> moveSequence){
        for(Move move : moveSequence){
            if(!this.moveCharacter(move))
                return false;
        }
        return true;
    }
    
    private void moveItem(final Position originTilePosition, final Position targetTilePosition){
        boolean isBox = false, isMario = false;
        // originTile moved can be character OR box
        byte originTileOldValue = getItem(originTilePosition);
        byte originTileNewValue;
        switch(originTileOldValue){
            case Tile.MARIO:
                originTileNewValue = Tile.SOL;
                isMario = true;
                break;
            case Tile.MARIO_ON_GOAL: 
                originTileNewValue = Tile.GOAL;
                isMario = true;
                break;
            case Tile.BOX :
                originTileNewValue = Tile.SOL;
                isBox = true;
                break;
            case Tile.BOX_ON_GOAL :
                originTileNewValue = Tile.GOAL;
                isBox = true;
                break;
            default :
                originTileNewValue = Tile.SOL;
                System.err.println("Character old position error");
                break;
        }

        // Set character on new tile
        byte targetTileOldValue = this.getItem(targetTilePosition);
        byte targetTileNewValue = 0;
        switch(targetTileOldValue){
            case Tile.SOL:
                if(isMario)
                    targetTileNewValue = Tile.MARIO;
                else if(isBox)
                    targetTileNewValue = Tile.BOX;
                else
                    System.out.println("Character new pos error");                    
                break;
            case Tile.GOAL:
                if(isMario)
                    targetTileNewValue = Tile.MARIO_ON_GOAL;
                else if(isBox)
                    targetTileNewValue = Tile.BOX_ON_GOAL;
                else
                    System.out.println("Character new pos error");
                break;
            default:
                targetTileNewValue = Tile.MARIO;
                System.out.println("Character new pos error");
                break;
        }

        //The exchange in itself
        setItem(originTilePosition, originTileNewValue);
        setItem(targetTilePosition, targetTileNewValue);
        if(isMario)
            this.setCharacterPosition(targetTilePosition);
    }
    
    private boolean isCrossable(Position tile){
        for(int i : this.crossable){
            if(this.getItem(tile) == i)
                return true;
        }
        return false;
    }
    
    private boolean isPushable(Position tile, Move direction){
        for(int i : this.pushable){
            if(this.getItem(tile) == i){
                boolean neighborIsCrossable = isCrossable(this.getNeighborPosition(tile, direction));
                if(neighborIsCrossable)
                    return true;
            }
        }
        return false;
    }
}
