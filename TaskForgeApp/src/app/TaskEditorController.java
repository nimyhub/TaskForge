package app;

import java.time.LocalDate;

import data.Priority;
import data.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TaskEditorController {
	@FXML private ComboBox<Priority> editTaskPriority;
	@FXML private TextField editTaskName;
	@FXML private TextArea editTaskDescription;
	@FXML private Button confirmEditTask;
    @FXML private Button exitEditTask;
    @FXML private DatePicker editTaskDueDate;
    
    private Task result = null;
    private Stage stage;
    
    public void initialize() {
    	editTaskPriority.getItems().setAll(Priority.values());
    	confirmEditTask.setOnAction(e -> onSave());
    	exitEditTask.setOnAction(e -> stage.close());
    }
    
    private void onSave() {
        String title = editTaskName.getText().trim();
        String desc = editTaskDescription.getText().trim();
        Priority priority = editTaskPriority.getValue();
        LocalDate date = editTaskDueDate.getValue();
        

        if (!title.isEmpty()) {
            result = new Task(title, desc, date, priority); // Or your constructor
            stage.close();
        } else {
        	editTaskName.setStyle("-fx-border-color: red;");
        }
    }
    
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    public void setInitialTask(Task task) {
    	editTaskName.setText(task.getTitle());
    	editTaskDescription.setText(task.getDescription());
    	editTaskPriority.setValue(task.getPriority());
    	editTaskDueDate.setValue(task.getDueDate());
    }
    
    public Task getResult() {
        return result;
    }
}
