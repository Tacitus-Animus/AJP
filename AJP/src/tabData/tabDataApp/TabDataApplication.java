package tabData.tabDataApp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TabDataApplication extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
		stage.setTitle("UH-60L Tab Data Application");
		stage.setScene(new Scene(root, 300, 275));
		stage.show();
	}

	public static void main(String[] args) {
		launch(TabDataApplication.class, args);
	}

}
