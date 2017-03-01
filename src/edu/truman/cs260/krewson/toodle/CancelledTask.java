/**
 * 
 */
package edu.truman.cs260.krewson.toodle;

/**
 * Represents a task that has been identified as
 * cancelled by the user.
 * @author Tanner
 *
 */
public class CancelledTask extends Task {
	
	/**
	 * Stores the reason the user cancelled the task.
	 */
	private String cancellationReason;

	/**
	 * This constructor takes in all of the necessary
	 * information to create a task, plus the information
	 * specific to a cancelled task.
	 * @param identifier unique internal id
	 * @param description name of the task
	 * @param priority urgent, normal, or low priority
	 * @param order priority within the three priority levels
	 * @param cancellationReason the reason the user cancelled the task
	 */
	public CancelledTask(int identifier, String description, char priority, int order, String cancellationReason) {
		super(identifier, description, priority, order, "Cancelled");
		this.cancellationReason = cancellationReason;
	}
	
	/**
	 * Get the reason the user cancelled this task.
	 * @return the reason the user cancelled the task 
	 */
	public String getCancellationReason () {
		return this.cancellationReason;
	}
	
}
