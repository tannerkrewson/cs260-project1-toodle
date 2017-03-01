/**
 * 
 */
package edu.truman.cs260.krewson.toodle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

/**
 * Represents a list of multiple Task objects,
 * and contains methods to create, discard, and
 * generally act upon these tasks.
 * @author Tanner
 *
 */
public class TaskList {
	
	/**
	 * Contains the list of tasks.
	 */
	private ArrayList<Task> tasks = new ArrayList<Task>();
	
	/**
	 * Provides an empty list of tasks.
	 */
	public TaskList () {}
	
	/**
	 * Provides a new TaskList object containing
	 * the tasks passed.
	 * @param tasks the tasks to add to the TaskList object
	 */
	public TaskList (ArrayList<Task> tasks) {
		this.tasks = tasks;
	}
	
	/**
	 * Cancels the given task, and sets a reason.
	 * @param idOfTaskToCancel the id of the task to cancel
	 * @param cancellationReason the reason the task was cancelled
	 */
	public void cancelTask (int idOfTaskToCancel, String cancellationReason) {
		Task taskToCancel = this.getTaskById(idOfTaskToCancel);
		this.removeTask(taskToCancel);
		this.addTask(new CancelledTask(
				taskToCancel.getIdentifier(), 
				taskToCancel.getDescription(), 
				taskToCancel.getPriority(), 
				taskToCancel.getOrder(), 
				cancellationReason));
	}
	
	/**
	 * Completed the given task, and sets the completion date to 
	 * the date in which the methods is called.
	 * @param idOfTaskToComplete the id of the task to complete
	 */
	public void completeTask (int idOfTaskToComplete) {
		Task taskToComplete = this.getTaskById(idOfTaskToComplete);
		this.removeTask(taskToComplete);
		this.addTask(new CompletedTask(
				taskToComplete.getIdentifier(), 
				taskToComplete.getDescription(), 
				taskToComplete.getPriority(), 
				taskToComplete.getOrder(), 
				new Date()));
	}
	
	/**
	 * Gets all of the tasks in this TaskList object in 
	 * the order of their priority.
	 * @return a list of tasks in order.
	 */
	public ArrayList<Task> getAllTasksInOrder () {
		this.sortTasksByPriorityOrder();
		return this.tasks;
	}
	
	/**
	 * Gets a unique id that can be used to construct
	 * new tasks.
	 * @return the new id
	 */
	public int getNextId () {
		this.sortTasksById();
		if (this.tasks.size() > 0) {
			return this.tasks.get(this.tasks.size() - 1).getIdentifier() + 1;
		} else {
			return 0;
		}
		
	}
	
	/**
	 * Creates a new task with the given descriptors,
	 * and returns its id.
	 * @param taskDescription the name of the task
	 * @param priority urgent, normal, or low priority, denoted U, N, or L
	 * @param taskOrder the priority of the task within the three priority levels
	 * @return the id of the newly created task
	 */
	public int createNewTask(String taskDescription, char priority, int taskOrder) {
		int newId = this.getNextId();
		this.addTask(new IncompleteTask(newId, taskDescription, priority, taskOrder));
		return newId;
	}
	
	/**
	 * Returns a list of tasks contained in this TaskList
	 * object that are of the Incomplete type.
	 * @return a list of incomplete tasks
	 */
	public ArrayList<Task> getAllIncompleteTasks () {
		ArrayList<Task> newTaskList = new ArrayList<Task>();
		for (Task task : this.getAllTasksInOrder()) {
			if (task.STATUS.equals("Incomplete")) {
				newTaskList.add(task);
			}
		}
		return newTaskList;
	}

	/**
	 * Returns a list of tasks contained in this TaskList
	 * object that are of the Incomplete type and are contained
	 * within a specified priority, U, N, or L.
	 * @param priority the priority of the desired tasks
	 * @return a list of incomplete tasks of the specified priority
	 */
	public ArrayList<Task> getAllIncompleteTasksWithPriority (char priority) {
		ArrayList<Task> newTaskList = new ArrayList<Task>();
		for (Task task : this.getAllTasksInOrder()) {
			if (task.STATUS.equals("Incomplete") && task.getPriority() == priority) {
				newTaskList.add(task);
			}
		}
		return newTaskList;
	}
	
	/**
	 * Adds the given task to this TaskList.
	 * @param newTask the task to add
	 */
	private void addTask(Task newTask) {
		this.tasks.add(newTask);
	}
	
	/**
	 * Removed the given task from this TaskList.
	 * @param taskToRemove the task to remove.
	 */
	private void removeTask (Task taskToRemove) {
		this.tasks.remove(taskToRemove);
	}
	
	/**
	 * Receives an id, and returns the corresponding
	 * task with that id.
	 * @param id the id of the task
	 * @return the task with the given id
	 */
	private Task getTaskById (int id) {
		for (Task task : tasks) {
			if (task.getIdentifier() == id) {
				return task;
			}
		}
		return null;
	}
	
	/**
	 * Sorts this TaskList's list of tasks by their ids.
	 */
	private void sortTasksById () {
		Collections.sort(this.tasks, new Comparator<Task>() {
	        public int compare(Task task1, Task task2)
	        {
	        	return task1.compareToById(task2);
	        }
	    });
	}
	
	/**
	 * Sorts this TaskList's list of tasks by their priorities.
	 */
	private void sortTasksByPriorityOrder () {
		Collections.sort(this.tasks, new Comparator<Task>() {
	        public int compare(Task task1, Task task2)
	        {
	        	return task1.compareToByOrder(task2);
	        }
	    });
	}
}
