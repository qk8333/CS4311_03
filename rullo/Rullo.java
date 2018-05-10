/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rullo;

import collections.CollectionChecker;
import collections.SumChecker;
import java.util.ArrayList;
import java.util.Random;
import togglegrid.ToggleGrid;

/**
 * Represents a Rullo puzzle
 * @author Evan Smith
 */
public class Rullo {
    private static final int MINVAL = 1, MAXVAL = 9;
    private ToggleGrid grid;
    private ArrayList<CollectionChecker> checkers;
    private final ArrayList<ArrayList<Integer>> board;
    private final ArrayList<Integer> rowKey, columnKey;
    
    /**
     * Initialize board and add constraints for a solution
     * Rullo game and solution created at random
     * @param size  size of square grid to be created
     */
    public Rullo(int size) {
        Random random = new Random(System.currentTimeMillis());
        this.grid = ToggleGrid.makeRandomGrid(size, size, this.MINVAL, 
                this.MAXVAL, 1, true);
        // Create a seperate copy of the board for output
        this.board = new ArrayList<>();
        for (int row = 0; row < size; row++) {
            this.board.add(new ArrayList<>());
            for (int column = 0; column < size; column++) {
                this.board.get(row).add(this.grid.value(row, column));
            }
        }
        // Randomize the toggled sections to create answer keys
        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {
                if (random.nextBoolean()) this.grid.toggle(row, column); else{};
            }
        }
        this.rowKey = new ArrayList<>();
        this.columnKey = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            int row = 0;
            int column = 0;
            for (int j = 0; j < size; j++) {
                row += this.grid.value(i, j);
                column += this.grid.value(j, i);
            }
            this.rowKey.add(row);
            this.columnKey.add(column);
        }
        // Reset grid to default state
        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {
                if (this.grid.value(row, column) == 0) {
                    this.grid.toggle(row, column);
                } else{};
            }
        }
        // Create checkers
        this.checkers = new ArrayList<>();
        for (int index = 0; index < size; index++) {
            this.checkers.add(new SumChecker(this.grid.rowIterator(index), 
                    this.rowKey.get(index)));
            this.checkers.add(new SumChecker(this.grid.columnIterator(index), 
                    this.columnKey.get(index)));
        }
    }
    
    /**
     * Function to toggle the value in a cell on the grid
     * @param row   row number to toggle in grid
     * @param column    column number to toggle in grid
     */
    public void setCell(int row, int column) {
        this.grid.toggle(row, column);
    }
    
    /**
     * Function to check if puzzle is solved
     * @return  boolean representing whether the puzzle is solved
     */
    public boolean solved() {
        for (CollectionChecker checker: this.checkers) {
            if (!checker.passes())
                return false;
            else{};
        }
        return true;
    }
    
    /**
     * Getter for the row key
     * @return  ArrayList of values in the row key
     */
    public ArrayList<Integer> getRowKey() {
        return this.rowKey;
    }
    
    /**
     * Getter for the column key
     * @return  ArrayList of values in the column key
     */
    public ArrayList<Integer> getColumnKey() {
        return this.columnKey;
    }
    
    /**
     * Getter for the values on the board (not the actual grid)
     * @return  board matrix
     */
    public ArrayList<ArrayList<Integer>> getBoard() {
        return this.board;
    }
    
    /**
     * Override string representation of the board
     * @return  string representation of the board
     */
    @Override
    public String toString() {
        return this.grid.toString();
    }
    
}
