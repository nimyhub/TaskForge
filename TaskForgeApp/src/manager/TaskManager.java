package manager;

import java.util.List;
import data.Task;

public class TaskManager {
	private String filename = "data/test.dat";
	private List<Task> tasks;
	
	public TaskManager() {
		tasks = FileManager.loadTasks(filename);
	}
	
	public void loadTasks() {
		FileManager.loadTasks(filename);
	}
	
	public void saveTasks() {
		FileManager.saveTasks(tasks, filename);
	}
	
	public void addTask(Task task) {
		tasks.add(task);
	}
	
	public void removeTask(Task task) {
		if(tasks.indexOf(task) != -1)tasks.remove(task);
	}
	
	public void removeTask(int index) {
		if(tasks.size() > index) tasks.remove(index);
	}
	
	public void printTasks() {
		for (Task t : tasks) {
			System.out.println(t);
		}
	}
}
