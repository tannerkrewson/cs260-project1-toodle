/**
 * 
 */
package edu.truman.cs260.krewson.toodle;

/**
 * Represents a task that has not yet been completed
 * by the user.
 * @author Tanner
 *
 */
public class IncompleteTask extends Task {

	/**
	 * This constructor takes in all of the necessary
	 * information to create a task that has yet to
	 * be completed.
	 * @param identifier unique internal id
	 * @param description name of the task
	 * @param priority urgent, normal, or low priority
	 * @param order priority within the three priority levels
	 */
	public IncompleteTask(int identifier, String description, char priority, int order) {
		super(identifier, description, priority, order, "Incomplete");
	}

}
