package app;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import data.Task;
import data.Priority;
import manager.TaskManager;
import java.time.LocalDate;
import java.util.Optional;
import javafx.scene.control.ButtonBar;

public class TaskForgeController {
	@FXML
	private ListView<String> taskListView;
	private TaskManager taskManager;
	private Stage stage;
	private int taskNumber = 0;
	
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	public void setTaskManager(TaskManager taskManager) {
		this.taskManager = taskManager;
		updateTaskList();
	}
	
	@FXML
	private void handleAddTask() {
        taskManager.addTask(new Task("Test"+ ++taskNumber, "From FXML", LocalDate.now().plusDays(1), Priority.HIGH));
        updateTaskList();
    }

	@FXML
    private void handleRemoveTask() {
		int selectedTask = taskListView.getSelectionModel().getSelectedIndex();
		if (selectedTask >= 0) {
			taskManager.removeTask(selectedTask);
	        updateTaskList();
		}
		else {
			System.out.println("No task selected.");
		}
    }

    @FXML
    private void handleSave() {
        taskManager.saveTasks();
    }
    
    @FXML
    public void handleExit() {
        if(!taskManager.isTasksUpdated()) {
        	Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        	alert.setTitle("Unsaved Changes");
            alert.setHeaderText("You have unsaved changes.");
            alert.setContentText("Do you want to save before exiting?");
            ButtonType saveButton = new ButtonType("Save");
            ButtonType dontSaveButton = new ButtonType("Don't Save");
            ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(saveButton, dontSaveButton, cancelButton);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent()) {
                if (result.get() == saveButton) {
                    taskManager.saveTasks();
                    Platform.exit();
                } else if (result.get() == dontSaveButton) {
                    Platform.exit();
                }
            }
        } else {
            Platform.exit();
        }
    }

	private void updateTaskList() {
		taskListView.getItems().clear();
		for (Task task : taskManager.getTaskList()) {
			taskListView.getItems().add(task.toString());
		}
	}
	
}
