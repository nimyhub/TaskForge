package data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

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
    
    @Override
	public boolean equals(Object obj) {
	    if (this == obj) return true;
	    if (obj == null || getClass() != obj.getClass()) return false;
	    Task other = (Task) obj;
	    return Objects.equals(title, other.title)
	        && Objects.equals(description, other.description)
	        && Objects.equals(dueDate, other.dueDate)
	        && Objects.equals(isCompleted, other.isCompleted)
	        && Objects.equals(priority, other.priority);
	}

	@Override
	public int hashCode() {
	    return Objects.hash(title, description, dueDate, isCompleted, priority);
	}
}
