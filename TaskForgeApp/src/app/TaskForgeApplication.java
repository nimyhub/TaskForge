package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import manager.TaskManager;

public class TaskForgeApplication extends Application{
	@Override
	public void start(Stage primaryStage) throws Exception {
		System.out.println(getClass().getResource("/data/TaskForgeView.fxml"));
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/data/TaskForgeView.fxml"));
		Parent root = loader.load();
		TaskForgeController controller = loader.getController();
		controller.setTaskManager(new TaskManager("/data/task.dat"));
		primaryStage.setTitle("TaskForge");
		primaryStage.setScene(new Scene(root, 600, 400));
		primaryStage.show();
		primaryStage.setOnCloseRequest(event -> {
		    event.consume();
		    controller.handleExit();
		});
	}

}
