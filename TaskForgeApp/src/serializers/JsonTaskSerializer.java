package serializers;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import data.Task;

public class JsonTaskSerializer implements TaskSerializer  {

	private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
	
	@Override
	public void save(List<Task> tasks, File file) throws IOException {
		try (Writer writer = new FileWriter(file)){
			gson.toJson(tasks, writer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Task> load(File file) throws IOException, ClassNotFoundException {
        if (!file.exists()) {
            System.out.println("File not found: " + file.getName() + ". Returning empty task list.");
            return new ArrayList<>();
        }
		try (Reader reader = new FileReader(file)) {
            Type taskListType = new TypeToken<List<Task>>() {}.getType();
            return gson.fromJson(reader, taskListType);
        } catch (JsonSyntaxException | JsonIOException e) {
            System.err.println("Failed to parse JSON file: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        } catch (IOException e) {
            System.err.println("I/O error while reading file: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
	}

}
