package manager;

import java.io.*;
import java.util.*;

import data.Task;

public class FileManager {
	private File directory;
    private File file;

    public FileManager(String filePath) {
        this.file = new File(filePath);
        this.directory = file.getParentFile();
        if (directory != null && !directory.exists()) {
            directory.mkdirs();
        }
    }
	
	public void saveTasks(List<Task> tasks) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
            out.writeObject(tasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public List<Task> loadTasks() {
		if(!file.exists()) return new ArrayList<>();
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))){
			return (List<Task>) in.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			return new ArrayList<>();
		} 
	}
}
