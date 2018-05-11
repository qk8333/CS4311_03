/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package userdata;

/**
 * Class to hold, increment and reset the number of completed puzzles
 * @author Evan Smith
 */
public class CompletedData {
    private int fiveGrid, sixGrid, sevenGrid, eightGrid;
    
    /**
     * Initialize all values to zero
     */
    public CompletedData() {
        this.fiveGrid = 0;
        this.sixGrid = 0;
        this.sevenGrid = 0;
        this.eightGrid = 0;
    }
    
    /**
     * Getter for 5 x 5 wins
     * @return  number of 5 x 5 wins
     */
    public int getFiveGrid() {
        return this.fiveGrid;
    }
    
    /**
     * Getter for 6 x 6 wins
     * @return  number of 6 x 6 wins
     */
    public int getSixGrid() {
        return this.sixGrid;
    }
    
    /**
     * Getter for 7 x 7 wins
     * @return  number of 7 x 7 wins
     */
    public int getSevenGrid() {
        return this.sevenGrid;
    }
    
    /**
     * Getter for 8 x 8 wins
     * @return  number of 8 x 8 wins
     */
    public int getEightGrid() {
        return this.eightGrid;
    }
    
    /**
     * Increments 5 x 5 wins
     */
    public void incrementFiveGrid() {
        this.fiveGrid++;
    }
    
    /**
     * Increments 6 x 6 wins
     */
    public void incrementSixGrid() {
        this.sixGrid++;
    }
    
    /**
     * Increments 7 x 7 wins
     */
    public void incrementSevenGrid() {
        this.sevenGrid++;
    }
    
    /**
     * Increments 8 x 8 wins
     */
    public void incrementEightGrid() {
        this.eightGrid++;
    }
    
    /**
     * Resets all values to 0
     */
    public void reset() {
        this.fiveGrid = 0;
        this.sixGrid = 0;
        this.sevenGrid = 0;
        this.eightGrid = 0;
    }
    
}
