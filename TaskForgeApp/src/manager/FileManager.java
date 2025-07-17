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
	
	private static boolean fileExists(String filename) {
		File file = new File(filename);
		return file.exists();
	}
	
	public static List<Task> loadTasks(String filename) {
		if(!fileExists(filename)) return new ArrayList<>();
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))){
			return (List<Task>) in.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			return new ArrayList<>();
		} 
	}
}
