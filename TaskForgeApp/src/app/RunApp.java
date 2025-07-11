package app;

import data.Task;
import manager.TaskManager;
import data.Priority;
import java.time.LocalDate;

public class RunApp {

	public static void main(String[] args) {
		TaskManager taskManager = new TaskManager();
		Task firstTask = new Task("Finish UML", "Draw UML for task manager project", LocalDate.now().plusDays(1), Priority.HIGH);
		taskManager.addTask(firstTask);
		//taskManager.removeTask(0);
		taskManager.printTasks();
		taskManager.saveTasks();
	}

}
