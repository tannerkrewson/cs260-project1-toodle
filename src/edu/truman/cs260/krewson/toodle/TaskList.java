/**
 * 
 */
package edu.truman.cs260.krewson.toodle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

/**
 * @author Tanner
 *
 */
public class TaskList {
	private ArrayList<Task> tasks = new ArrayList<Task>();
	
	public TaskList () {}
	
	public TaskList (ArrayList<Task> tasks) {
		this.tasks = tasks;
	}
	
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
	
	public ArrayList<Task> getAllTasksInOrder () {
		this.sortTasksByPriorityOrder();
		return this.tasks;
	}
	
	public int getNextId () {
		this.sortTasksById();
		if (this.tasks.size() > 0) {
			return this.tasks.get(this.tasks.size() - 1).getIdentifier() + 1;
		} else {
			return 0;
		}
		
	}
	
	public int createNewTask(String taskDescription, char priority, int taskOrder) {
		int newId = this.getNextId();
		this.addTask(new IncompleteTask(newId, taskDescription, priority, taskOrder));
		return newId;
	}
	
	public ArrayList<Task> getAllIncompleteTasks () {
		ArrayList<Task> newTaskList = new ArrayList<Task>();
		for (Task task : this.getAllTasksInOrder()) {
			if (task.STATUS.equals("Incomplete")) {
				newTaskList.add(task);
			}
		}
		return newTaskList;
	}

	public ArrayList<Task> getAllIncompleteTasksWithPriority (char priority) {
		ArrayList<Task> newTaskList = new ArrayList<Task>();
		for (Task task : this.getAllTasksInOrder()) {
			if (task.STATUS.equals("Incomplete") && task.getPriority() == priority) {
				newTaskList.add(task);
			}
		}
		return newTaskList;
	}
	
	private void addTask(Task newTask) {
		this.tasks.add(newTask);
	}
	
	private void removeTask (Task taskToRemove) {
		this.tasks.remove(taskToRemove);
	}
	
	private Task getTaskById (int id) {
		for (Task task : tasks) {
			if (task.getIdentifier() == id) {
				return task;
			}
		}
		return null;
	}
	
	private void sortTasksById () {
		Collections.sort(this.tasks, new Comparator<Task>() {
	        @Override
	        public int compare(Task task1, Task task2)
	        {
	        	return task1.compareToById(task2);
	        }
	    });
	}
	
	private void sortTasksByPriorityOrder () {
		Collections.sort(this.tasks, new Comparator<Task>() {
	        @Override
	        public int compare(Task task1, Task task2)
	        {
	        	return task1.compareToByOrder(task2);
	        }
	    });
	}
}
