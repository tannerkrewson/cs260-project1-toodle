/**
 * 
 */
package edu.truman.cs260.krewson.toodle;

/**
 * Represents a task that would find itself on a
 * to-do list.
 * @author Tanner
 *
 */
public class Task {
	
	/**
	 * Stores the information relating to the task,
	 * its id, description, priority, and order within
	 * its priority.
	 */
	private int identifier;
	private String description;
	private char priority;
	private int order;
	
	/**
	 * Stores the status of the type of Task.
	 * This is to be assigned by any extending class.
	 */
	public final String STATUS;

	/**
	 * This constructor takes in all of the necessary
	 * information to create a task.
	 * @param identifier unique internal id
	 * @param description name of the task
	 * @param priority urgent, normal, or low priority
	 * @param order priority within the three priority levels
	 * @param STATUS the type of task
	 */
	public Task(int identifier, String description, char priority, int order, String STATUS) {
		this.identifier = identifier;
		this.description = description;
		this.priority = priority;
		this.order = order;
		
		this.STATUS = STATUS;
	}
	
	/**
	 * Gets the task's identifier.
	 * @return unique internal id
	 */
	public int getIdentifier () {
		return this.identifier;
	}
	
	/**
	 * Gets the task's description.
	 * @return name of the task
	 */
	public String getDescription () {
		return this.description;
	}
	
	/**
	 * Gets the task's priority.
	 * @return urgent, normal, or low priority
	 */
	public char getPriority () {
		return this.priority;
	}
	
	/**
	 * Gets the task's order.
	 * @return priority within the three priority levels
	 */
	public int getOrder () {
		return this.order;
	}
	
	/**
	 * Compares this task to another task by its
	 * priority order. Returns positive if it is of
	 * higher priority, negative if it is of lower
	 * priority, and 0 if they are of the same
	 * priority.
	 * @param other the task to compare this one to
	 * @return a positive or negative number, or 0
	 */
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
	
	/**
	 * Compares this task to another task by its
	 * identifier. Returns positive if it is of
	 * higher priority, negative if it is of lower
	 * priority, and 0 if they are of the same
	 * priority.
	 * @param other the task to compare this one to
	 * @return a positive or negative number, or 0
	 */
	public int compareToById (Task other) {
    	if (this.getIdentifier() > other.getIdentifier()) {
			return 1;
		} else if (this.getIdentifier() < other.getIdentifier()) {
			return -1;
		} else {
			return 0;
		}
	}
	
	/**
	 * Returns the strength of the priority, so that
	 * they can be compared numerically. The lower the 
	 * number, the higher the priority.
	 * @return a number corresponding to the priority strength of this task
	 */
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
