/**
 * 
 */
package edu.truman.cs260.krewson.toodle;

/**
 * @author Tanner
 *
 */
public class CancelledTask extends Task {
	
	private String cancellationReason;

	/**
	 * @param identifier
	 * @param description
	 * @param priority
	 * @param order
	 * @param cancellationReason
	 */
	public CancelledTask(int identifier, String description, char priority, int order, String cancellationReason) {
		super(identifier, description, priority, order, "Cancelled");
		this.cancellationReason = cancellationReason;
	}
	
	public String getCancellationReason () {
		return this.cancellationReason;
	}
	
}
