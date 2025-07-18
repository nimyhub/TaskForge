package app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.time.LocalDate;

import data.Priority;
import data.Task;
import manager.TaskManager;

public class TaskForgeApplication extends Application{
	private TaskManager taskManager = new TaskManager("data/test.dat");
	private ListView<String> taskListView = new ListView<String>();
	
	@Override
	public void start(Stage primaryStage){
		 primaryStage.setTitle("TaskForge");
		 updateTaskList();
		 
		 Button addButton = new Button("Add Task");
	     Button removeButton = new Button("Remove First Task");
	     Button saveButton = new Button("Save & Exit");
	     
	     addButton.setOnAction(e -> {
	         Task task = new Task("Test", "A test task", LocalDate.now().plusDays(1), Priority.HIGH);
	         taskManager.addTask(task);
	         updateTaskList();
	     });

	     removeButton.setOnAction(e -> {
	         taskManager.removeTask(0);
	         updateTaskList();
	     });

	     saveButton.setOnAction(e -> {
	         taskManager.saveTasks();
	         primaryStage.close();
	     });
	        
	     VBox layout = new VBox(10, taskListView, addButton, removeButton, saveButton);
	     layout.setPadding(new javafx.geometry.Insets(10));

	     Scene scene = new Scene(layout, 400, 300);
	     primaryStage.setScene(scene);
	     primaryStage.show();
	}
	
	private void updateTaskList() {
        taskListView.getItems().clear();
        for (Task task : taskManager.getTaskList()) {
            taskListView.getItems().add(task.toString());
        }
    }

}
