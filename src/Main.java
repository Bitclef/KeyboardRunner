import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import menu.CreateContent;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		Scene scene = new Scene(CreateContent.createContent());
		primaryStage.setTitle("Keyboard Runner");
		scene.setFill(Color.BLACK);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
