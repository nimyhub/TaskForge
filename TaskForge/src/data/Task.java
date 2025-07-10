package data;

import java.io.Serializable;
import java.time.LocalDate;

public class Task implements Serializable{
	private static final long serialVersionUID = 1L;
	private String title;
	private String description;
	private LocalDate dueDate;
	private boolean isCompleted;
	private Priority priority;
	
	public Task(String title, String description, LocalDate dueDate, Priority priority) {
		this.title = title;
		this.description = description;
		this.dueDate = dueDate;
		this.priority = priority;
		this.isCompleted = false;
	}
	
	public void markComplete() {
		isCompleted = true;
	}
	
	public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public boolean isComplete() {
        return isCompleted;
    }

    public Priority getPriority() {
        return priority;
    }
    
    @Override
    public String toString() {
    	return String.format("[%s] %s (Due: %s) - %s", isCompleted ? "✓":" ", title, dueDate, priority);
    }
}
