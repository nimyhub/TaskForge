package app;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import data.Task;
import data.TaskSerializer;
import data.Config;
import data.Priority;
import data.TaskSerializerFactory;
import manager.ConfigManager;
import manager.TaskManager;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;
import javafx.scene.control.ButtonBar;

public class TaskForgeController {
	@FXML
	private ListView<String> taskListView;
	private TaskManager taskManager;
	private ConfigManager configManager;
	private Stage stage;
	private int taskNumber = 0;
	
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	public void setConfigManager() {
		configManager = new ConfigManager();
	}
	
	public void setTaskManager() {
		Config config = configManager.loadConfig();
		TaskSerializer serializer = TaskSerializerFactory.get(config.getLastFormat());
		File file = new File(config.getLastDir(), config.getLastFileName() + config.getLastFormat());
		taskManager = new TaskManager(serializer, file);
		try {
			taskManager.loadTasks();
		} catch (IOException | ClassNotFoundException e) {e.printStackTrace();}
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
        try {
			taskManager.saveTasks();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    @FXML
    private void handleSettings() {

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
                	handleSave();
                    Platform.exit();
                } else if (result.get() == dontSaveButton) {
                    Platform.exit();
                }
            }
        } else {
            Platform.exit();
        }
    }
    
    @FXML
    private void handleOpenFile() {
    	FileChooser fileChooser = createFileChooser();
    	Config oldConfig = configManager.loadConfig();
    	File initialDir = new File(oldConfig.getLastDir());
    	if(initialDir.exists()) fileChooser.setInitialDirectory(initialDir);
    	File selectedFile = fileChooser.showOpenDialog(stage);
    	if(selectedFile == null) return;
    	Config config = createNewConfig(selectedFile);
    	configManager.saveConfig(config);
    	setTaskManager();
    }
    
    private Config createNewConfig(File selectedFile) {
    	Config config = new Config();
    	String dir = selectedFile.getParent();
    	String fileName = selectedFile.getName();
    	String format = "";                          
    	int i = fileName.lastIndexOf('.');
    	if (i > 0) {
    		format = fileName.substring(i);
    		fileName = fileName.substring(0, i);
    	}
    	config.setLastDir(dir);
    	config.setLastFileName(fileName);
    	config.setLastFormat(format);
    	return config;
    }
    
    private FileChooser createFileChooser() {
    	FileChooser chooser = new FileChooser();
        chooser.setTitle("Open Task File");
        chooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Task Files (*.dat, *.json, *.txt)", "*.dat", "*.json", "*.txt"),
            new FileChooser.ExtensionFilter("All Files", "*.*")
        );
        return chooser;
    }

	private void updateTaskList() {
		taskListView.getItems().clear();
		for (Task task : taskManager.getTaskList()) {
			taskListView.getItems().add(task.toString());
		}
	}
	
}
