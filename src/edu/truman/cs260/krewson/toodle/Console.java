/**
 * 
 */
package edu.truman.cs260.krewson.toodle;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Wraps the regular Java console and provides
 * functionality specific to printing Tasks.
 * @author Tanner
 *
 */
public class Console {

	/**
	 * Wraps the user input from the console.
	 */
	private Scanner consoleIn = new Scanner(System.in);
	
	/**
	 * Wraps the regular output to the console.
	 */
	private PrintStream consoleOut = System.out;
	
	/**
	 * Asks the user a question, and returns the
	 * response.
	 * @param query the question to ask the user
	 * @return the value the user enters
	 */
	public String promptUserFor (String query) {
		consoleOut.print(query);
		return consoleIn.nextLine();
	}
	
	/**
	 * Prints a list of the tasks in a given
	 * ArrayList of tasks in the format specified
	 * in the functional spec.
	 * @param tasksToPrint the tasks to print
	 */
	public void printTasks (ArrayList<Task> tasksToPrint) {	
		consoleOut.println("ID     Task                 Priority Order");
		consoleOut.println("------ ----                 -------- -----");
		
		for (Task task : tasksToPrint) {
			consoleOut.printf("%1$6d %2$-20.20s %3$8.8s %4$5d", 
					task.getIdentifier(), 
					task.getDescription(), 
					task.getPriority(), 
					task.getOrder());
			consoleOut.println();
		}
	}
	
	/**
	 * Prints a list of the tasks in a given
	 * ArrayList of tasks in the format specified
	 * in the functional spec and includes the additional
	 * information found in CompletedTasks and 
	 * CancelledTasks.
	 * @param tasksToPrint the tasks to print
	 */
	public void printTasksWithStatus (ArrayList<Task> tasksToPrint) {
		consoleOut.println("ID     Task                 Priority Order Status Date     Reason");
		consoleOut.println("------ ----                 -------- ----- ------ -------- ------");
		
		for (Task task : tasksToPrint) {
			consoleOut.printf("%1$6d %2$-20.20s %3$8.8s %4$5d ", 
					task.getIdentifier(), 
					task.getDescription(), 
					task.getPriority(), 
					task.getOrder());
			if (task instanceof CompletedTask) {
				CompletedTask thisTask = (CompletedTask)task;
				consoleOut.printf("%1$-6.6s%2$9tD ", 
						thisTask.STATUS,
						thisTask.getCompletionDate());
			} else if (task instanceof CancelledTask) {
				CancelledTask thisTask = (CancelledTask)task;
				consoleOut.printf("%1$-6.6s          %2$-1s", 
						thisTask.STATUS,
						thisTask.getCancellationReason());
			}
			consoleOut.println();
		}
	}
	
	/**
	 * Wraps the regular System.out.println
	 * @param lineToPrint the line to print
	 */
	public void printLine (String lineToPrint) {
		consoleOut.println(lineToPrint);
	}
}
