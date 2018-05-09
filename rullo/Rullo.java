/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rullo;

import collections.CollectionChecker;
import collections.SumChecker;
import java.util.ArrayList;
import java.util.NoSuchElementException;
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
    private ArrayList<Integer> rowKey, columnKey;
    
    /**
     * Initialize board and add constraints for a solution
     * Rullo game and solution created at random
     * @param size  size of square grid to be created
     */
    public Rullo(int size) {
        Random random = new Random();
        this.grid = ToggleGrid.makeRandomGrid(size, size, this.MINVAL, 
                this.MAXVAL, 1, true);
        // Randomize the toggled sections to create answer keys
        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {
                boolean toggle = random.nextBoolean();
                if (toggle) this.grid.toggle(row, column); else{};
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
     * @exception NoSuchElementException    one or more parameters out of bounds
     */
    public void setCell(int row, int column) {
        if (row < 0 || row >= this.rowKey.size() ||
            column < 0 || column >= this.columnKey.size()) {
            throw new NoSuchElementException();
        }
        else {
            this.grid.toggle(row, column);
        }
    }
    
    /**
     * Function to check if puzzle is solved
     * @return  boolean representing whether the puzzle is solved
     */
    public boolean solved() {
        for (CollectionChecker checker: this.checkers)
            if (!checker.passes())
                return false;
        return true;    
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
