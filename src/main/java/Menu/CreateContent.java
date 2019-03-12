package Menu;

import Game.Game;
import View.StageManager;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

public class CreateContent{
	public Parent createContent(){
		Pane root = new Pane();

		root.setPrefSize(1050,600);

		Title title = new Title("K E Y B O A R D R U N N E R");
		title.setTranslateX(200);
		title.setTranslateY(10);

		MenuItem exitProgram = new MenuItem("Exit");
		exitProgram.setOnMouseClicked(mouseEvent -> System.exit(0));

		MenuItem playGame = new MenuItem("Play");
		playGame.setOnMouseClicked(mouseEvent -> {
			Game game = new Game();
			StageManager stageManager = new StageManager();
			game.createNewGame(stageManager.getMainStage());
		});

		MenuBox hBox = new MenuBox(
				new MenuItem("Tutorial"),
				playGame,
				new MenuItem("Help"),
				new MenuItem("About"),
				exitProgram);
		hBox.setTranslateX(20);
		hBox.setTranslateY(550);

		root.getChildren().addAll(title, hBox);
		return root;
	}
}
