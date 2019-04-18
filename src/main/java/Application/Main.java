package Application;

import View.StageManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {


	@Override
	public void start(Stage primaryStage) {

		StageManager manager = new StageManager();
		primaryStage = manager.getMainStage();
		primaryStage.show();



	}

	public static void main(String[] args) {
		System.setProperty("quantum.multithreaded", "false");
		Application.launch(Main.class, args);
	}
}
