/**
 * 
 */
package edu.truman.cs260.krewson.toodle;

/**
 * @author Tanner
 *
 */
public class Task {
	
	private int identifier;
	private String description;
	private char priority;
	private int order;
	
	public final String STATUS;

	/**
	 * 
	 */
	public Task(int identifier, String description, char priority, int order, String STATUS) {
		this.identifier = identifier;
		this.description = description;
		this.priority = priority;
		this.order = order;
		
		this.STATUS = STATUS;
	}
	
	public int getIdentifier () {
		return this.identifier;
	}
	
	public String getDescription () {
		return this.description;
	}
	
	public char getPriority () {
		return this.priority;
	}
	
	public int getOrder () {
		return this.order;
	}
	
	public int compareToByOrder (Task other) {
    	if (this.getPriorityStrength() > other.getPriorityStrength()) {
			return 1;
		} else if (this.getPriorityStrength() < other.getPriorityStrength()) {
			return -1;
		} else {
	    	if (this.getOrder() > other.getOrder()) {
				return 1;
			} else if (this.getOrder() < other.getOrder()) {
				return -1;
			} else {
				return 0;
			}
		}
	}
	
	public int compareToById (Task other) {
    	if (this.getIdentifier() > other.getIdentifier()) {
			return 1;
		} else if (this.getIdentifier() < other.getIdentifier()) {
			return -1;
		} else {
			return 0;
		}
	}
	
	private int getPriorityStrength () {
		switch (this.getPriority()) {
			case 'U':
				return 1;
			case 'N':
				return 2;
			case 'L':
				return 3;
			default:
				return 0;
		}
	}

}
