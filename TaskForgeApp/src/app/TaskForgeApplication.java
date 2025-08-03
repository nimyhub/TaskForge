package app;

import controllers.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TaskForgeApplication extends Application{
	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainView.fxml"));
		Parent root = loader.load();
		MainController controller = loader.getController();
		controller.setConfigManager();
		controller.setStage(primaryStage);
		controller.setTaskManager();
		primaryStage.setTitle("TaskForge");
		primaryStage.setScene(new Scene(root, 600, 400));
		primaryStage.show();
		controller.setTaskManager();
		primaryStage.setOnCloseRequest(event -> {
		    event.consume();
		    controller.handleExit();
		});
	}

}
