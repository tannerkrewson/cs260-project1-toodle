
import java.io.FileNotFoundException;
import java.io.IOException;

import edu.truman.cs260.krewson.toodle.*;

/**
 * This main class reads in the tasks from the file, waits for
 * user input, and responds to the command issued by the user
 * appropriately.
 * @author Tanner
 *
 */
public class KrewsonToodle {

	/**
	 * The main method of this class executes when the program
	 * is first launched, and fulfills the intended purpose of
	 * the program.
	 * @param args
	 */
	public static void main(String[] args) {
		
		Console c = new Console();
		ToodleFile tf = new ToodleFile();
		TaskList tl = new TaskList();
		
		try {
			tl = new TaskList(tf.readTasksFromFile());
		} catch (IOException e) {
			c.printLine(e.getMessage());
			System.exit(0);
		}
		
		while (true) {
			
			//validate the input
			int cmd = 0;
			boolean inputValid = false;
			while (!inputValid) {
				try {
					cmd = Integer.parseInt(c.promptUserFor("Please enter a command (1 - 7): "));
					inputValid = true;
				} catch (Exception e) {}
				
			}
			
			//respond to the input
			switch (cmd) {
			case 1:
				String description = c.promptUserFor("Enter task description: ");
				char priority = c.promptUserFor("Enter priority (U = urgent, N = normal priority, L = low priority): ").toUpperCase().charAt(0);
				int taskOrder = Integer.parseInt(c.promptUserFor("Enter task order: "));
				int idOfNewTask = tl.createNewTask(description, priority, taskOrder);
				c.printLine("-- Task Created: ID is " + idOfNewTask);
				break;
			case 2:
				c.printTasks(tl.getAllIncompleteTasks());
				break;
			case 3:
				priority = c.promptUserFor("Please enter a priority (U, N, L): ").toUpperCase().charAt(0);
				c.printTasks(tl.getAllIncompleteTasksWithPriority(priority));
				break;
			case 4:
				c.printTasksWithStatus(tl.getAllTasksInOrder());
				break;
			case 5:
				int idToComplete = Integer.parseInt(c.promptUserFor("Enter ID of task to mark as completed: "));
				tl.completeTask(idToComplete);
				c.printLine("-- Task " + idToComplete + " marked as completed --");
				break;
			case 6:
				int idToCancel = Integer.parseInt(c.promptUserFor("Enter ID of task to cancel: "));
				String reason = c.promptUserFor("Enter reason for cancellation: ");
				tl.cancelTask(idToCancel, reason);
				c.printLine("-- Task " + idToCancel + " cancelled --");
				break;
			case 7:
				c.printLine("Goodbye");
				try {
					tf.writeTasksToFile(tl.getAllTasksInOrder());
				} catch (FileNotFoundException e) {
					c.printLine("Failed to save task list to Task_List.txt");
					System.exit(0);
				}
				System.exit(0);
			default:
				break;
			}
			c.printLine("");
		}

	}

}
