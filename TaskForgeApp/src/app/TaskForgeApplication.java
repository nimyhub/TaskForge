package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TaskForgeApplication extends Application{
	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/data/TaskForgeView.fxml"));
		Parent root = loader.load();
		TaskForgeController controller = loader.getController();
		controller.setConfigManager();
		controller.setTaskManager();
		primaryStage.setTitle("TaskForge");
		primaryStage.setScene(new Scene(root, 600, 400));
		primaryStage.show();
		primaryStage.setOnCloseRequest(event -> {
		    event.consume();
		    controller.handleExit();
		});
	}

}
