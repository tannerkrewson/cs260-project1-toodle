/**
 * 
 */
package edu.truman.cs260.krewson.toodle;

/**
 * @author Tanner
 *
 */
public class IncompleteTask extends Task {

	/**
	 * @param identifier
	 * @param description
	 * @param priority
	 * @param order
	 */
	public IncompleteTask(int identifier, String description, char priority, int order) {
		super(identifier, description, priority, order, "Incomplete");
	}

}
