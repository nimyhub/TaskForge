package data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TxtTaskSerializer implements TaskSerializer {

	@Override
	public void save(List<Task> tasks, File file) throws IOException {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
			for(Task task : tasks) {
				String status = task.isComplete() ? "DONE" : "TODO";
				String line = status + "::"
						+ sanitize(task.getTitle()) + "::"
						+ sanitize(task.getDescription()) + "::"
						+ task.getDueDate() + "::"
						+ task.getPriority();
				writer.write(line);
				writer.newLine();
			}
		}
	}
	
	private String sanitize(String input) {
		return input.replace("::", "--");
	}

	@Override
	public List<Task> load(File file) throws IOException, ClassNotFoundException {
		List<Task> tasks = new ArrayList<>();
		if (file == null || !file.exists()) return tasks;
		try (BufferedReader reader = new BufferedReader(new FileReader(file))){
			String line;
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split("::");
				if (parts.length < 5) continue;
				boolean isDone = parts[0].equals("DONE");
				String title = parts[1];
				String description = parts[2];
				LocalDate dueDate = LocalDate.parse(parts[3]);
				Priority priority;
                try {
                	priority = Priority.valueOf(parts[4].toUpperCase());
                } catch (IllegalArgumentException e) {
                	priority = Priority.MEDIUM;
                }
                Task task = new Task(title, description, dueDate, priority);
                if(isDone) task.markComplete();
                tasks.add(task);
			}
		}
		return tasks;
	}

}
