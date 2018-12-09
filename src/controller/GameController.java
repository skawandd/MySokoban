package controller;

import controller.tools.CSVElement;
import modele.Grid;
import modele.Move;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import view.Display;

/**
 * GameController handles the Game in itself
 */
public abstract class GameController {

    protected final Grid grid;
    private int nbMoves; //Init import CSV
    private final ZonedDateTime start;

    /**
     * GameController
     * @param csv CSVElement needed
     */
    public GameController(CSVElement csv) {
        this.grid = new Grid(csv);
        this.nbMoves = 0;
        this.start = ZonedDateTime.now();
    }

    /**
     * @return The GameController Grid
     */
    public Grid getGrid() {
        return grid;
    }

    /**
     * @return number of moves
     */
    public int getNbMoves() {
        return this.grid.getNbMoves();
    }
    
    /**
     * @
     */
    protected void setNbMoves(int p_nb){
        this.nbMoves = p_nb;
    }



    /**
     * Moves the character in the given direction
     * @param move Direction to move the character
     */
    public void move(Move move) {
        this.grid.moveCharacter(move);
    }

    /**
     * @return true if the Game is Won
     */
    public boolean checkWin() {
        return grid.hasWon();
    }
    
    /**
     * @return Elapsed time
     */
    public String getTimer() {
        return Display.getStopWatch(
                (int) start.until(ZonedDateTime.now(),
                        ChronoUnit.SECONDS));
    }

}
