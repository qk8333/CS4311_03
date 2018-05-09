/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package togglegrid;

import java.util.List;
import java.util.Objects;

/**
 * A value that can be toggled through a finite sequence of valid values
 * @author Evan Smith
 */
public class ToggleCell {
    private final List<Integer> values;
    private int valueIndex;
    
    /**
     * Initialize a list of valid values and start at the first value
     * @param _values
     */
    public ToggleCell(List<Integer> _values) {
        this.values = _values;
        this.valueIndex = 0;
    }
    
    /**
     * Toggle the current value to the next valid value
     * wrap if the iterator reaches the end
     */
    public void toggle() {
        this.valueIndex = (this.valueIndex + 1) % this.values.size();
    }
    
    /**
     * Getter for the current value
     * @return  the current value at the toggled index
     */
    public int value() {
        return this.values.get(this.valueIndex);
    }
    
    /**
     * Overloaded equals function to compare values of individual cells
     * @param object    object to be compared to
     * @return  boolean representing whether the two cells share the same value
     */
    @Override
    public boolean equals(Object object) {
        if (object == null) return false;
        else if (!ToggleCell.class.isAssignableFrom(object.getClass())) {
            return false;
        }
        else {
            final ToggleCell other = (ToggleCell) object;
            return this.value() == other.value();
        }
    }

    /**
     * Overloaded hash code from equals operator
     * @return hash
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.values);
        hash = 59 * hash + this.valueIndex;
        return hash;
    }
    
    /**
     * Overloaded toString() function for class portability
     * @return  String representation of current value
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(this.values.get(this.valueIndex));
        return result.toString();
    }
}
