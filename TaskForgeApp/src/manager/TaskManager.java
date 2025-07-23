package manager;

import java.util.ArrayList;
import java.util.List;
import data.Task;

public class TaskManager {
	private List<Task> tasks;
	private FileManager fileManager;
	
	public TaskManager(String filename) {
		this.fileManager = new FileManager(filename);
		tasks = fileManager.loadTasks();
	}
	
	public void setSaveFile(String filename) {
		this.fileManager = new FileManager(filename);
		loadTasks();
	}
	
	public void loadTasks() {
		tasks = fileManager.loadTasks();
		if (tasks == null) tasks = new ArrayList<>();
	}
	
	public void saveTasks() {
		fileManager.saveTasks(tasks);
	}
	
	public boolean isTasksUpdated() {
	    List<Task> loadedTasks = fileManager.loadTasks();
	    return tasks.equals(loadedTasks);
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
	
	public List<Task> getTaskList(){
		return tasks;
	}
}
