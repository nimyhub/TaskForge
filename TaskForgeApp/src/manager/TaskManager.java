package manager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import data.Task;
import serializers.TaskSerializer;

public class TaskManager {
	private List<Task> tasks = new ArrayList<>();
	private TaskSerializer serializer;
	private File currentFile;
	
	public TaskManager(TaskSerializer serializer, File file) {
        this.serializer = serializer;
        currentFile = file;
    }
	
	public void loadTasks() throws IOException, ClassNotFoundException {
        this.tasks = serializer.load(currentFile);
    }
	
	public void saveTasks() throws IOException {
        serializer.save(tasks, currentFile);
    }

	public void saveTasks(File newFile) throws IOException {
        serializer.save(tasks, newFile);
    }
	
	public void setTaskList(List<Task> list) {
		if(list == null) return;
		tasks = list;
	}
	
	public boolean isTasksUpdated() {
		try {
			List<Task> loadedTasks;
			loadedTasks = serializer.load(currentFile);
			return tasks.equals(loadedTasks);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public void addTask(Task task) {
		if (task != null) {
	        tasks.add(task);
	    } else {
	        System.err.println("Warning: Attempted to add null task.");
	    }
	}
	
	public void removeTask(Task task) {
		tasks.remove(task);
	}
	
	public void removeTask(int index) {
		if(tasks.size() > index) tasks.remove(index);
	}
	
	public List<Task> getTaskList(){
		return tasks;
	}
}
