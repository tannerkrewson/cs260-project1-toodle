/**
 * 
 */
package edu.truman.cs260.krewson.toodle;

import java.util.Date;

/**
 * @author Tanner
 *
 */
public class CompletedTask extends Task {
	
	private Date completionDate;

	/**
	 * @param identifier
	 * @param description
	 * @param priority
	 * @param order
	 * @param completionDate
	 */
	public CompletedTask(int identifier, String description, char priority, int order, Date completionDate) {
		super(identifier, description, priority, order, "Completed");
		this.completionDate = completionDate;
	}
	
	public Date getCompletionDate () {
		return this.completionDate;
	}
	
}
