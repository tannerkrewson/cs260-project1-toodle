
import java.io.FileNotFoundException;
import java.io.IOException;

import edu.truman.cs260.krewson.toodle.*;

public class Toodle {

	public static void main(String[] args) {
		
		Console c = new Console();
		ToodleFile tf = new ToodleFile();
		TaskList tl = new TaskList();
		
		try {
			tl = new TaskList(tf.readTasksFromFile());
		} catch (IOException e) {
			c.printLine("Could not read from file Task_List.txt");
			System.exit(0);
		}
		
		while (true) {
			int cmd = Integer.parseInt(c.promptUserFor("Please enter a command (1 - 7): "));
			
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
				c.printTasks(tl.getAllTasksInOrder());
			case 5:
				int idToComplete = Integer.parseInt(c.promptUserFor("Enter ID of task to mark as completed: "));
				tl.completeTask(idToComplete);
				c.printLine("-- Task " + idToComplete + " marked as completed --");
			case 6:
				int idToCancel = Integer.parseInt(c.promptUserFor("Enter ID of task to cancel: "));
				String reason = c.promptUserFor("Enter reason for cancellation: ");
				tl.cancelTask(idToCancel, reason);
				c.printLine("-- Task " + idToCancel + " cancelled --");
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
		}

	}

}
