/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.ia.CPUPlayer;
import controller.tools.CSVElement;
import java.util.List;
import modele.Move;


public class RobotGameController extends GameController {

    private CPUPlayer cpu;
    private List<Move> solution;
    
    public RobotGameController(CSVElement csv) {
        super(csv);
    }
    
    /**
     * @return Returns the solution found by the CPUPlayer
     */
    public List<Move> getSolution() {
        return solution;
    }
    
    /**
     * 
     */
    public void solve() {
        try {
            this.cpu = new CPUPlayer(this.getGrid().clone());
            this.solution = this.cpu.getSolution();
            this.setNbMoves(this.solution.size());
        } catch (Exception e) {
            System.err.print(e);
        }
    }
    
}
