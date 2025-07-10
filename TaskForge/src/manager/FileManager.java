package manager;

import java.io.*;
import java.util.*;

import data.Task;

public class FileManager {
	public static void saveTasks(List<Task> tasks, String filename) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(tasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public static List<Task> loadTasks(String filename) {
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))){
			return (List<Task>) in.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			return new ArrayList<>();
		} 
	}
}
