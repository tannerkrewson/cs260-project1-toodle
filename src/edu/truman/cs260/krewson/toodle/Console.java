/**
 * 
 */
package edu.truman.cs260.krewson.toodle;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Tanner
 *
 */
public class Console {

	private Scanner consoleIn = new Scanner(System.in);
	private PrintStream consoleOut = System.out;
	
	public String promptUserFor (String query) {
		consoleOut.print(query);
		return consoleIn.nextLine();
	}
	
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
	
	public void printLine (String lineToPrint) {
		consoleOut.println(lineToPrint);
	}
}
