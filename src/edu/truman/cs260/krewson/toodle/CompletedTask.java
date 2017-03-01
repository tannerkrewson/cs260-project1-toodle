/**
 * 
 */
package edu.truman.cs260.krewson.toodle;

import java.util.Date;

/**
 * Represents a task that has been identified as
 * cancelled by the user.
 * @author Tanner
 *
 */
public class CompletedTask extends Task {
	
	/**
	 * Stores the date the user completed the task.
	 */
	private Date completionDate;

	/**
	 * This constructor takes in all of the necessary
	 * information to create a task, plus the information
	 * specific to a completed task.
	 * @param identifier unique internal id
	 * @param description name of the task
	 * @param priority urgent, normal, or low priority
	 * @param order priority within the three priority levels
	 * @param completionDate the date the user completed the task
	 */
	public CompletedTask(int identifier, String description, char priority, int order, Date completionDate) {
		super(identifier, description, priority, order, "Completed");
		this.completionDate = completionDate;
	}
	
	/**
	 * Get the date the user completed this task.
	 * @return the date the user completed the task 
	 */
	public Date getCompletionDate () {
		return this.completionDate;
	}
	
}
