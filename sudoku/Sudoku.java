/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sudoku;

import collections.CollectionChecker;
import collections.UniqueChecker;
import java.util.ArrayList;
import togglegrid.ToggleGrid;

/**
 * Represents a Sudoku puzzle
 * @author Evan Smith
 */
public class Sudoku {
    private static final int ROWS = 9, COLUMNS = 9,
            MINVALUE = 1, MAXVALUE = 9,  SUBGRIDSIZE = 3;
    private ToggleGrid grid;
    private ArrayList<CollectionChecker> checkers;
    

    /** 
     * Initialize board and add checkers for the constraints for a solution
     */
    public Sudoku() {
        ArrayList<Integer> values = new ArrayList<>();
        for (int value = this.MINVALUE; value <= this.MAXVALUE; value++)
            values.add(value);
        this.grid = ToggleGrid.makeUniformGrid(this.ROWS, this.COLUMNS, values);
        this.checkers = new ArrayList<>();
        for (int row = 0; row < this.ROWS; row++) {
            this.checkers.add(new UniqueChecker(this.grid.rowIterator(row)));
        }
        for (int column = 0; column < this.COLUMNS; column++) {
            this.checkers.add(new UniqueChecker(this.grid.columnIterator(column)));
        }
        for (int row = 0; row < this.ROWS; row+=this.SUBGRIDSIZE) {
            for (int column = 0; column < this.COLUMNS; column += this.SUBGRIDSIZE) {
                this.checkers.add(new UniqueChecker(this.grid.areaIterator(row, 
                        column, this.SUBGRIDSIZE, this.SUBGRIDSIZE)));
            }
        }
    }

    /**
     * Set a cell value to a grid
     * @param row   row index
     * @param column    column index
     * @param value     value to add at index
     */
    public void setCell(int row, int column, int value) {
        int currentValue = this.grid.value(row, column);
        int toggleCount;
        if (value >= currentValue)
            toggleCount = value - currentValue;
        else toggleCount = value - currentValue + (this.MAXVALUE - this.MINVALUE + 1);
        for (int count = 0; count < toggleCount; count++)
            this.grid.toggle(row, column);
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
