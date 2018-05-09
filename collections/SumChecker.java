/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Concrete child class of CollectionChecker that implements the interface
 * by returning true if all values are equal to a specified sum
 * @author Evan Smith
 */
public class SumChecker<T> implements CollectionChecker {
    private final int sum;
    private List<T> list;
    
    /**
     * Initializes an Iterator collection to a list
     * @param iterator  Iterator<T> object to check
     * @param _sum  value representing the total sum of the proper values
     */
    public SumChecker(Iterator<T> iterator, int _sum) {
        this.sum = _sum;
        this.list = new ArrayList<>();
        while (iterator.hasNext()) {
            this.list.add(iterator.next());
        }
    }
    
    /**
     * SumChecker's implementation of the passes() function, note that this is a
     * really roundabout way of doing things but it works
     * @return  boolean representing whether all values are equal to the sum
     */
    @Override
    public boolean passes() {
        int total = 0;
        // Converts a string to an int (basically JavaScript in a nutshell)
        for (int index = 0; index < this.list.size(); index++) {
            total += Integer.parseInt(this.list.get(index).toString());
        }
        return this.sum == total;
    }
    
}
