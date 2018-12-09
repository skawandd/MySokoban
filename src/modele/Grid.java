/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import controller.tools.CSVElement;
import java.util.List;

/**
 *
 * @author Francois
 */
public class Grid implements Cloneable{
    
    private byte[][] grid;
    private final int x_size;
    private final int y_size;
    private final int[] pushable = {Tile.BOX,Tile.BOX_ON_GOAL};
    private final int[] crossable ={Tile.SOL,Tile.GOAL};
    private Position characterPosition;
    private int nbMoves;

    public int getNbMoves() {
        return nbMoves;
    }
    
    public Grid(byte[][] p_Board, int p_x, int p_y){
        this.grid = p_Board;
        this.x_size = p_x;
        this.y_size = p_y;
        findCharacterPosition();
    }
    public Grid(CSVElement csv){
        this.grid = csv.getCsvGrid();
        this.x_size = csv.getNbColumn();
        this.y_size = csv.getNbLine();
        findCharacterPosition();
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
     * Move character in given direction
     * @param direction Direction the caracter is taking
     * @return MoveResult tells to the calling function what happened
     */
    public MoveResult moveCharacter(Move direction){
        
        Position neighborTile = getNeighborPosition(this.getCharacterPosition(), direction);
        
        if (neighborTile == null)
            return MoveResult.BLOCKED;        
        else if(isCrossable(neighborTile)){
            moveItem(this.characterPosition, neighborTile);
            this.nbMoves++;
            return MoveResult.MOVED;
        }else if (isPushable(neighborTile, direction)){
            Position neighborNeighbor = getNeighborPosition(neighborTile, direction);
            if(neighborNeighbor == null)
                return MoveResult.BLOCKED;
            boolean neighborWasMoved = moveItem(neighborTile,neighborNeighbor);
            boolean characterWasMoved = moveItem(this.characterPosition, neighborTile);
            if(neighborWasMoved && characterWasMoved){
                this.nbMoves++;
                return MoveResult.PUSHED;
            }
        }
        return MoveResult.BLOCKED;
    }
    
    /**
     * Returns a brand new Grid with its own byte Array
     * @return Grid the Clone of This grid
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
     * Plays an array of Move on the board
     * @param moveSequence Move sequence to play
     * @return false if a move was not successfull
     */
    public MoveResult playSequence(List<Move> moveSequence){
        boolean playedOk;
        MoveResult previousResult = null, result = MoveResult.BLOCKED;
        Move previousMove = null;
        for(Move move : moveSequence){                
            result = this.moveCharacter(move);
            
            //The character CAN NOT go back, EXCEPT if he just pushed a box
            if((previousMove != null) && (move == previousMove.getOpposite()))
                if(previousResult != MoveResult.PUSHED)
                    return MoveResult.BLOCKED;
            
            playedOk = ((result == MoveResult.MOVED) || (result == MoveResult.PUSHED));
            if(!playedOk)
                return MoveResult.BLOCKED;
            previousMove = move;
            previousResult = result;
        }
        // We want to know if the player was pushing or moving during the LAST move.
        return result;
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
