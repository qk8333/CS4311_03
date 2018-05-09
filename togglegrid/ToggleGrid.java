/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package togglegrid;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * A 2-dimensional array of cells that can be toggled through a sequence of
 * valid values
 * @author Evan Smith
 */
public class ToggleGrid {
    private final ArrayList<ArrayList<ToggleCell>> grid;
    
    /**
     * Create a uniform ToggleGrid
     * Used for games like Sudoku
     * @param rows  number of rows
     * @param columns   number of columns
     * @param values    integer array of toggle values
     * @return  new ToggleGrid object
     */
    public static ToggleGrid makeUniformGrid(int rows, int columns, List<Integer> values) {
        ArrayList<ArrayList<ToggleCell>> temp = new ArrayList<>();
        for (int row = 0; row < rows; row++) {
            temp.add(new ArrayList<>());
            for (int column = 0; column < columns; column++) {
                temp.get(row).add(new ToggleCell(values));
            }
        }
        return new ToggleGrid(temp);
    }
    
    /**
     * Create a randomized Toggle grid with cells of a random number and 0
     * Used for games like Rullo
     * @param rows  number of rows
     * @param columns   number of columns
     * @param min   lower bounds
     * @param max   upper bounds
     * @param numberValues  number of random values in ToggleCell
     * @param hasZero   boolean representing whether the ToggleCell has a 0 value
     * @return  new ToggleGrid object
     */
    public static ToggleGrid makeRandomGrid(int rows, int columns, int min,
            int max, int numberValues, boolean hasZero) {
        ArrayList<ArrayList<ToggleCell>> temp = new ArrayList<>();
        List<Integer> values = new ArrayList<>();
        for (int row = 0; row < rows; row++) {
            temp.add(new ArrayList<>());
            for (int column = 0; column < columns; column++) {
                values.clear();
                for (int value = 0; value < numberValues; value++) {
                    values.add(ThreadLocalRandom.current().nextInt(min, max + 1));
                }
                if (hasZero) values.add(0); else{};
                temp.get(row).add(new ToggleCell(values));
            }
        }
        return new ToggleGrid(temp);
    }
    
    /**
     * Initialize a grid by passing a matrix of ToggleCell directly
     * @param _grid ArrayList<ArrayList<ToggleCell>> object to be passed directly
     */
    private ToggleGrid(ArrayList<ArrayList<ToggleCell>> _grid) {
        this.grid = _grid;
    }
    
    /**
     * @deprecated  Replaced by static methods used to call constructor
     * Initialize the grid, with all values starting at the first valid value
     * @param rows   the number of rows in the array
     * @param columns   the number of columns in the array
     * @param values    list of values that you can toggle through
     */
    public ToggleGrid(int rows, int columns, List<Integer> values) {
        this.grid = new ArrayList<>();
        for (int row = 0; row < rows; row++) {
            this.grid.add(new ArrayList<>());
            for (int column = 0; column < columns; column++) {
                this.grid.get(row).add(new ToggleCell(values));
            }
        }
    }

    /**
     * Toggle the value of a particular cell to its next value
     * @param row   the row of the cell
     * @param column    the column of the cell
     */
    public void toggle(int row, int column) {
        this.grid.get(row).get(column).toggle();
    }
    
    /**
     * Getter for the value at a particular cell of the grid
     * @param row   the row of the cell
     * @param column    the column of the cell
     * @return  the current value of the cell
     */
    public int value(int row, int column) {
        return this.grid.get(row).get(column).value();
    }
    
    /**
     * Produce a multi-row string with the values of the grid
     * @return  string output of grid
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (ArrayList<ToggleCell> row : this.grid) {
            for (ToggleCell cell : row) {
                result.append(' ');
                result.append(cell.value());
            }
            result.append('\n');
        }
        return result.toString();
    }
    
    /**
     * Return iterator over one row of the grid
     * @param row   row index to create new iterator
     * @return  new RowIterator(row)
     */
    public Iterator<ToggleCell> rowIterator(int row) {
        return new RowIterator(row);
    }
    
    /**
     * Return iterator over one column of the grid
     * @param column    column index to create new iterator
     * @return  new ColumnIterator(column)
     */
    public Iterator<ToggleCell> columnIterator(int column) {
        return new ColumnIterator(column);
    }
    
    /**
     * Return iterator over one sub area of the grid
     * @param firstRow  first row of area
     * @param firstColumn   first column of area
     * @param numberRows    number of rows in subgrid
     * @param numberColumns number of columns in subgrid
     * @return  new AreaIterator
     */
    public Iterator<ToggleCell> areaIterator(int firstRow, int firstColumn,
            int numberRows, int numberColumns) {
        return new AreaIterator(firstRow, firstColumn,
                numberRows, numberColumns);
    }
    
    /**
     * Nested class within ToggleGrid that implements Iterator<ToggleCell>
     * @author Evan Smith
     */
    public class RowIterator implements Iterator<ToggleCell> {
        private final int row;
        private int index;
        
        /**
         * Initialize the row and select the first element to be checked
         * @param _row   the row to be checked
         * @exception NoSuchElementException	improper parameter input
         */
        public RowIterator(int _row) {
            if (_row < 0 || _row >= ToggleGrid.this.grid.size()) {
                throw new NoSuchElementException();
            } else{};
            this.row = _row;
            this.index = 0;
        }
        
        /**
         * Iterate through row (first call starts at the first value)
         * @return  ToggleCell object
         * @exception NoSuchElementException    iterator out of bounds
         */
        @Override
        public ToggleCell next() {
            this.index++;
            if (!this.hasNext()) throw new NoSuchElementException();
            else return ToggleGrid.this.grid.get(this.row).get(this.index-1);
        }
        
        /**
         * Check if there is another element to iterate through
         * @return  boolean representing whether there is a subsequent object
         */
        @Override
        public boolean hasNext() {
            if (this.index >= ToggleGrid.this.grid.size()) return false;
            else return true;
        }
    }
    
    /**
     * A nested class within ToggleGrid that implements Iterator<ToggleCell>
     * @author Evan Smith
     */
    public class ColumnIterator implements Iterator<ToggleCell> {
        private final int column;
        private int index;
        
        /**
         * Initialize the column and select the first element to be checked
         * @param _column   the column to be checked
         */
        public ColumnIterator(int _column) {
            if (_column < 0 || _column >= ToggleGrid.this.grid.get(0).size()) {
                throw new NoSuchElementException();
            } else{};
            this.column = _column;
            this.index = 0;
        }
        
        /**
         * Iterate through column (first call starts at the first value)
         * @return  ToggleCell object
         * @exception NoSuchElementException    iterator out of bounds
         */
        @Override
        public ToggleCell next() {
            this.index++;
            if (!this.hasNext()) throw new NoSuchElementException();
            else {
                return ToggleGrid.this.grid.get(this.index-1).get(this.column);
            }
        }
        
        /**
         * Check if there is another element to iterate through
         * @return  boolean representing whether there is a subsequent object
         */
        @Override
        public boolean hasNext() {
            if (this.index >= ToggleGrid.this.grid.get(0).size()) return false;
            else return true;
        }
    }
    
    /**
     * A nested class within ToggleGrid that implements Iterator<ToggleCell>
     * @author Evan Smith
     */
    public class AreaIterator implements Iterator<ToggleCell> {
        private final int firstRow, firstColumn, numberRows, numberColumns;
        private int rowIndex, columnIndex;
        
        /**
         * Initialized the area to be toggled
         * @param _firstRow initialize the first row space of an area
         * @param _firstColumn  initialize the first column space of an area
         * @param _numberRows   the row size of the specified area
         * @param _numberColumns    the column size of the specified area
         * @exception NoSuchElementException    improper parameter input
         */
        public AreaIterator(int _firstRow, int _firstColumn,
                            int _numberRows, int _numberColumns) {
            if (_firstRow < 0 || 
                _firstRow >= ToggleGrid.this.grid.size() ||
                _firstColumn < 0 ||
                _firstColumn >= ToggleGrid.this.grid.get(0).size() ||
                _numberRows < 0 || 
                _numberRows >= ToggleGrid.this.grid.size() || 
                _numberColumns < 0 || 
                _numberColumns >= ToggleGrid.this.grid.get(0).size()) {
                throw new NoSuchElementException();
            } else{};
            this.firstRow = _firstRow;
            this.firstColumn = _firstColumn;
            this.numberRows = _numberRows;
            this.numberColumns = _numberColumns;
            this.rowIndex = _firstRow;
            this.columnIndex = _firstColumn;
        }
        
        /**
         * Iterate through the area
         * @return  ToggleCell object
         * @exception NoSuchElementException    iterator out of bounds
         */
        @Override
        public ToggleCell next() {
            if (this.rowIndex == this.firstRow && 
                this.columnIndex == this.firstColumn) {
                return ToggleGrid.this.grid
                        .get(this.rowIndex).get(this.columnIndex);
            }
            else {
                if (!this.hasNext()) throw new NoSuchElementException(); else{};
                this.columnIndex++;
                if (this.columnIndex >= this.numberColumns) {
                    this.rowIndex++;
                    this.columnIndex -= this.numberColumns;
                } else{};
                return ToggleGrid.this.grid
                        .get(this.rowIndex).get(this.columnIndex);
            }
        }
        
        /**
         * Check if there is another element to iterate through
         * @return  boolean representing whether there is a subsequent object
         */
        @Override
        public boolean hasNext() {
            if (this.rowIndex >= this.numberRows && 
                this.columnIndex >= this.numberColumns) return false;
            else return true;
        }
    }
    
}
