package controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import data.Task;
import data.Config;
import manager.ConfigManager;
import manager.TaskManager;
import serializers.TaskSerializer;
import serializers.TaskSerializerFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javafx.scene.control.ButtonBar;

public class MainController {
	@FXML private ListView<String> taskListView;
	@FXML private Button editTask;
	@FXML private Button removeTask;
	private List<Task> tempTasks = new ArrayList<>();
	private TaskManager taskManager;
	private ConfigManager configManager;
	private boolean triedDefault = false;
	private Stage stage;
	
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	public void setConfigManager() {
		configManager = new ConfigManager();
	}
	
	public void initialize() {
		removeTask.disableProperty().bind(
	        taskListView.getSelectionModel().selectedItemProperty().isNull()
	    );
		editTask.disableProperty().bind(
		        taskListView.getSelectionModel().selectedItemProperty().isNull()
		    );
	}
	
	public void setTaskManager() {
		try {
			Config config = configManager.loadConfig();
			TaskSerializer serializer = TaskSerializerFactory.get(config.getLastFormat());
			if (serializer == null) throw new IllegalArgumentException("Unsupported format");
			File file = new File(config.getLastDir(), config.getLastFileName() + config.getLastFormat());
			updateWindowTitle(file);
			taskManager = new TaskManager(serializer, file);
			taskManager.loadTasks();
		} catch (IOException | ClassNotFoundException | IllegalArgumentException e) {
			if (!triedDefault) {
				triedDefault = true;
				configManager.defaultOnError();
				setTaskManager();
				triedDefault = false;
			} else {
				e.printStackTrace();
			}
		}
		updateTaskList();
	}
	
	@FXML
	private void handleEditTask() {
		try {
			Task selectedTask = taskManager.getTaskList().get(taskListView.getSelectionModel().getSelectedIndex());
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/TaskEditor.fxml"));
	        Parent root = loader.load();
	        TaskEditorController controller = loader.getController();
	        Stage stage = new Stage();
	        stage.setTitle("Edit Task");
	        stage.initModality(Modality.APPLICATION_MODAL);
	        stage.setScene(new Scene(root));
	        controller.setStage(stage);
	        controller.setInitialTask(selectedTask);
	        stage.showAndWait();
	        
	        Task result = controller.getResult();
	        if(result != null) {
	        	taskManager.addTask(result);
	        	taskManager.removeTask(selectedTask);
	        	updateTaskList();
	        }else {
	        	System.out.println("Edit cancelled – no changes made.");
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	@FXML
	private void handleAddTask() {
		try {
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/TaskEditor.fxml"));
	        Parent root = loader.load();
	        TaskEditorController controller = loader.getController();
	        Stage stage = new Stage();
	        stage.setTitle("Add Task");
	        stage.initModality(Modality.APPLICATION_MODAL);
	        stage.setScene(new Scene(root));
	        controller.setStage(stage);
	        stage.showAndWait();
	        taskManager.addTask(controller.getResult());
	        updateTaskList();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
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
    private void handleSave() {
        try {
			taskManager.saveTasks();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    @FXML
    private void handleSaveAs() throws Exception{
    	FileChooser fileChooser = createFileChooserSave();
    	Config oldConfig = configManager.loadConfig();
    	File initialDir = new File(oldConfig.getLastDir());
    	if(initialDir.exists()) fileChooser.setInitialDirectory(initialDir);
    	fileChooser.setInitialFileName(oldConfig.getLastFileName()+oldConfig.getLastFormat());
    	File file = fileChooser.showSaveDialog(stage);
    	if(file != null) {
    		Config config = createNewConfig(file);
        	configManager.saveConfig(config);
        	tempTasks = taskManager.getTaskList();
        	setTaskManager();
        	taskManager.setTaskList(tempTasks);
        	taskManager.saveTasks(file);
        	updateTaskList();
    	}
    }
    
    @FXML
    private void handleOpenFile() {
    	FileChooser fileChooser = createFileChooserOpen();
    	Config oldConfig = configManager.loadConfig();
    	File initialDir = new File(oldConfig.getLastDir());
    	if(initialDir.exists()) fileChooser.setInitialDirectory(initialDir);
    	File selectedFile = fileChooser.showOpenDialog(stage);
    	if(selectedFile == null) return;
    	Config config = createNewConfig(selectedFile);
    	configManager.saveConfig(config);
    	setTaskManager();
    }
    
    public void updateWindowTitle(File file) {
        if (file != null) {
            stage.setTitle("TaskForge - " + file.getName());
        } else {
            stage.setTitle("TaskForge");
        }
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
    
    private FileChooser createFileChooserOpen() {
    	FileChooser chooser = new FileChooser();
        chooser.setTitle("Open Task File");
        chooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Task Files (*.dat, *.json, *.txt)", "*.dat", "*.json", "*.txt"),
            new FileChooser.ExtensionFilter("All Files", "*.*")
        );
        return chooser;
    }
    
    private FileChooser createFileChooserSave() {
    	FileChooser chooser = new FileChooser();
        chooser.setTitle("Save Task File as");
        chooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Task Files (*.dat, *.json, *.txt)", "*.dat", "*.json", "*.txt")
        );
        return chooser;
    }

    private void updateTaskList() {
    	taskListView.getItems().clear();
    	if(taskManager.getTaskList() == null) return;
    	for (Task task : taskManager.getTaskList()) {
    		if (task != null) {
    			taskListView.getItems().add(task.toString());
    		} else {
    			System.err.println("Warning: Skipped null task in task list");
    		}
    	}
    }
	
}
