/**
 * 
 */
package edu.truman.cs260.krewson.toodle;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

/**
 * @author Tanner
 *
 */
public class ToodleFile {

	private FileReader fileStream;
	private Scanner fileIn;
	
	private PrintWriter fileOut;
	
	public void writeTasksToFile (ArrayList<Task> tasksToWrite) throws FileNotFoundException {
		fileOut = new PrintWriter("Task_List.txt");
		
		fileOut.println(tasksToWrite.size());
		
		for (Task task : tasksToWrite) {
			fileOut.println(task.STATUS);
			fileOut.println(task.getIdentifier());
			fileOut.println(task.getDescription());
			fileOut.println(task.getPriority());
			fileOut.println(task.getOrder());
			
			if (task instanceof CompletedTask) {
				CompletedTask newTask = (CompletedTask)task;
			    Calendar cal = Calendar.getInstance();
			    cal.setTime(newTask.getCompletionDate());
			    fileOut.println(cal.get(Calendar.YEAR));
			    fileOut.println(cal.get(Calendar.MONTH));
			    fileOut.println(cal.get(Calendar.DAY_OF_MONTH));
			} else if (task instanceof CancelledTask) {
				CancelledTask newTask = (CancelledTask)task;
				fileOut.println(newTask.getCancellationReason());
			}
		}
		
		fileOut.close();
	}
	
	public ArrayList<Task> readTasksFromFile () throws IOException {
	    File file = new File("Task_List.txt");

	    if (!file.isFile() && !file.createNewFile())
	    {
	        throw new IOException("Could not read from file Task_List.txt");
	    }
		fileStream = new FileReader(file);
		fileIn = new Scanner(fileStream);
		
		ArrayList<Task> newTaskList = new ArrayList<Task>();
		
		int numberOfTasks;
		try {
			numberOfTasks = fileIn.nextInt();
			fileIn.nextLine();
		} catch (Exception e) {
			numberOfTasks = 0;
		}
		
		for (int i = 0; i < numberOfTasks; i++) {
			String status = fileIn.nextLine();
			int identifier = fileIn.nextInt();
			fileIn.nextLine();
			String description = fileIn.nextLine();
			char priority = fileIn.nextLine().charAt(0);
			int order = fileIn.nextInt();
			fileIn.nextLine();
			
			if (status.equals("Completed")) {
				int year = fileIn.nextInt();
				int month = fileIn.nextInt();
				int day = fileIn.nextInt();
				Date completionDate = new GregorianCalendar(year, month, day).getTime();
				newTaskList.add(new CompletedTask(identifier, description, priority, order, completionDate));
			} else if (status.equals("Cancelled")) {
				String cancellationReason = fileIn.nextLine();
				newTaskList.add(new CancelledTask(identifier, description, priority, order, cancellationReason));
			} else {
				newTaskList.add(new IncompleteTask(identifier, description, priority, order));
			}
		}
		
		fileIn = null;
		fileStream.close();
		
		return newTaskList;
	}
}
