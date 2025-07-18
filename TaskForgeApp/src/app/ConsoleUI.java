package app;

import java.util.Scanner;
import data.Priority;
import data.Task;
import manager.TaskManager;
import java.time.LocalDate;

public class ConsoleUI {
	private static ConsoleUI instance = null;
	private boolean running;
	private Scanner scanner = new Scanner(System.in);
	private TaskManager taskManager = new TaskManager("data/test.dat");
	
	private ConsoleUI() {

	}
	
	public static ConsoleUI getInstance() {
		if(instance == null) instance = new ConsoleUI();
		return instance;
	}
	
	public void run() {
		running = true;
		System.out.println("Welcome to the Task Manager!");
		
		while (running) {
			System.out.println("\n\n\n\n\nTasks:");
			taskManager.printTasks();
			showMenu();
			String input = scanner.nextLine().trim();
			
			switch(input) {
				case "1":
					taskManager.addTask(new Task("Test", "A test task", LocalDate.now().plusDays(1), Priority.HIGH));
	                break;
	            case "2":
	            	taskManager.removeTask(0);
	                break;
	            case "q":
	            case "quit":
	            case "exit":
	            	System.out.println("Saving tasks...");
	            	taskManager.saveTasks();
	                System.out.println("Exiting program...");
	                running = false;
	                scanner.close();
	                break;
	            default:
	                System.out.println("Invalid option, please try again.");
			}
		}
	}
	
	private void showMenu() {
        System.out.println("\nMenu:");
        System.out.println("1 - Add new Task");
        System.out.println("2 - Delete Task");
        System.out.println("Q - Quit");
        System.out.print("Choose an option: ");
    }
}
