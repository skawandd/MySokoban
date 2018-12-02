/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.ia.model;

import controller.ia.view.Display;
import controller.tools.CSVElement;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Francois
 */
public class Grid implements Cloneable{
    
    private byte[][] grid;
    private int x_size;
    private int y_size;
    private final int[] pushable = {Tile.BOX,Tile.BOX_ON_GOAL};
    private final int[] crossable ={Tile.SOL,Tile.GOAL};
    private Position characterPosition;
    
    public Grid(byte[][] p_Board, int p_x, int p_y){
        this.grid = p_Board;
        this.x_size = p_x;
        this.y_size = p_y;
        // Ensure the Grid is secure
        findCharacterPosition();
    }
    public Grid(){
        // CSVElement.readCSVFile(6, 5, path),6,5
        String path = CSVElement.pick_CSVLevel();
        try {
            CSVElement csv = new CSVElement(path);
            this.x_size = csv.getNbColumn();
            this.y_size = csv.getNbLine();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Grid.class.getName()).log(Level.SEVERE, null, ex);
            this.x_size = 0;
            this.y_size = 0;
        }
    }
        
    public int getX() {
        return x_size;
    }
    public int getY() {
        return y_size;
    }
    public Position getCharacterPosition() {
        return characterPosition;
    }
    public byte[][] getGrid() {
        return this.grid;
    }
    public boolean hasWon(){
        return (this.getEmptyGoalsLeft() == 0);
    }
    /**
     * @brief Move character in given direction
     * @param direction
     * @return 
     */
    public boolean moveCharacter(Move direction){
        
        Position neighborTile = getNeighborPosition(this.getCharacterPosition(), direction);
        
        if (neighborTile == null)
            return false;        
        else if(isCrossable(neighborTile)){
            return moveItem(this.characterPosition, neighborTile);
        }else if (isPushable(neighborTile, direction)){
            Position neighborNeighbor = getNeighborPosition(neighborTile, direction);
            if(neighborNeighbor == null)
                return false;
            boolean neighborWasMoved = moveItem(neighborTile,neighborNeighbor);
            boolean characterWasMoved = moveItem(this.characterPosition, neighborTile);
            return (neighborWasMoved && characterWasMoved);
        }
        return false;
    }
    
    /**
     * @brief returns a brand new Grid with its own byte Array
     * @return Grid
     */
    @Override
    public Grid clone(){
        {
            Grid newGrid = new Grid(
                    copy2DByteArray(this.getGrid()),
                    this.getX(),this.getY());
            return newGrid;
        }
    }
    
    /**
     * @brief Grid setter
     * @param p_grid 
     */
    private void setGrid(byte[][] p_grid){
        this.grid = p_grid;
    }
    private void setCharacterPosition(Position characterPosition) {
        this.characterPosition = characterPosition;
    }
    
    private byte getItem(Position p_position){
        return this.grid[p_position.getX()][p_position.getY()];
    }
    
    private void setItem(Position position, byte item){
        this.grid[position.getX()][position.getY()] = item;
    }
    
    private void findCharacterPosition(){
        for(int var_y=0; var_y < this.y_size; var_y++){
            for(int var_x = 0; var_x < this.x_size; var_x++){
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
        for(int var_y=0; var_y < this.y_size; var_y++){
            for(int var_x = 0; var_x < this.x_size; var_x++){
                int itemInGrid = this.grid[var_x][var_y];
                if(itemInGrid == 4 || itemInGrid == 6){
                    nbEmptyGoals++;
                }
            }
        }
        return nbEmptyGoals;
    }
    
    /**
     * @brief Gives you a tile's neighbor. 
     * @param p_pos the position you want to get the neighboor of
     * @param direction the direction of the neighbor
     * @return Position or null if not found
     */
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
    
    /**
     * play an array of Move on the board
     * @param moveSequence 
     * @param displayGrid : Should the grid be displayed after modification
     * @return false if a move was not successfull
     */
    public boolean playSequence(List<Move> moveSequence, boolean displayGrid){
        boolean playedOk;
        for(Move move : moveSequence){
            /*
            if(!this.moveCharacter(move))
                return false;
            if(displayGrid)
                Display.printBoard(this);*/
            playedOk = this.moveCharacter(move);
            Display.printBoard(this);
            if(!playedOk)
                return false;
        }
        return true;
    }
    
    /**
     * @brief Moves Mario or a box on the grid
     * @param originTilePosition
     * @param targetTilePosition 
     * @return false if move was not possible
     */
    private boolean moveItem(final Position originTilePosition, final Position targetTilePosition){
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
                System.err.println("Character old position error");
                return false;
        }

        // Set character on new tile
        byte targetTileOldValue = this.getItem(targetTilePosition);
        byte targetTileNewValue = -1;
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
                System.err.println("Character new pos error");
                return false;
        }

        //The exchange in itself
        setItem(originTilePosition, originTileNewValue);
        setItem(targetTilePosition, targetTileNewValue);
        if(isMario)
            this.setCharacterPosition(targetTilePosition);
        return true;
    }
    
    /**
     * @brief Tells you if a Tile is crossable
     * @param tile
     * @return boolean 
     */
    private boolean isCrossable(Position tile){
        if (tile == null)
            return false;
            
        for(int i : this.crossable){
            if(this.getItem(tile) == i)
                return true;
        }
        return false;
    }
    
    /**
     * @brief tells if an object is pushable in the given direction
     * @param tile
     * @param direction
     * @return true if the object can be pushed in the given direction
     */
    private boolean isPushable(Position tile, Move direction){
        int item = this.getItem(tile);
        for(int i : this.pushable){
            if(item == i){
                Position neighborPosition = this.getNeighborPosition(tile, direction);
                boolean neighborIsCrossable = isCrossable(neighborPosition);
                if(neighborIsCrossable)
                    return true;
            }
        }
        return false;
    }
    
    /**
     * @brief Copies originalArray and return a brand new copy.
     * @param originalArray
     * @return byte[][] the copy
     */
    private byte[][] copy2DByteArray(final byte[][] originalArray){
        byte[][] copyArray = new byte[originalArray.length][];
        for(int i = 0; i < copyArray.length; i++){
            copyArray[i] = new byte[originalArray[i].length];
            System.arraycopy(originalArray[i], 0, copyArray[i], 0, copyArray[i].length);
        }
        return copyArray;
    }
}
