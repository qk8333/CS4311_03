/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package collections;

/**
 * Strategy interface to check the values of the matrix
 * @author Evan Smith
 */
public interface CollectionChecker {
	/**
	 * Function to check whether the values on a grid satisfy the check
	 * @return	boolean representing whether the check has passed
	 */
	public boolean passes();
}
