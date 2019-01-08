package menu;

import javafx.scene.Parent;
import javafx.scene.layout.Pane;

public class CreateContent{
	public static Parent createContent(){
		Pane root = new Pane();

		root.setPrefSize(1050,600);

		Title title = new Title("K E Y B O A R D R U N N E R");
		title.setTranslateX(200);
		title.setTranslateY(10);

		MenuBox hBox = new MenuBox(
				new MenuItem("Tutorial"),
				new MenuItem("Play"),
				new MenuItem("Help"),
				new MenuItem("About"),
				new MenuItem("Exit"));
		hBox.setTranslateX(20);
		hBox.setTranslateY(550);

		root.getChildren().addAll(title, hBox);
		return root;
	}
}
