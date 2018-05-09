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
 * by returning true if all values in the collection are unique, false if not
 * @author Evan Smith
 */
public class UniqueChecker<T> implements CollectionChecker {
	private List<T> list;
    
	/**
	 * Initializes an Iterator collection to a list
	 * @param iterator  Iterator<T> object to check
	 */
	public UniqueChecker(Iterator<T> iterator) {
		this.list = new ArrayList<>();
		while (iterator.hasNext()) {
			this.list.add(iterator.next());
		}
	}
	
	/**
	 * UniqueChecker's implementation of the passes() function
	 * @return	boolean representing whether all elements are unique
	 */
	@Override
	public boolean passes() {
		for (int i = 0; i < this.list.size(); i++) {
			for (int j = i + 1; j < this.list.size(); j++) {
				if (this.list.get(i).equals(this.list.get(j))) {
                                    return false;
                                }
                                else{};
			} // nested for-loop
		} // initial for-loop
		return true;
	}
	
}
