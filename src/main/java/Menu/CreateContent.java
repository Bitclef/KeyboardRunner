package Menu;

import Game.Game;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CreateContent{

    private Stage mainStage;
    private Game game;
    public static int highScore;


    public Parent createContent(){
		Pane root = new Pane();

		root.setPrefSize(1050,600);

		Title title = new Title("K E Y B O A R D   R U N N E R");
		title.setTranslateX(150);
		title.setTranslateY(10);

		MenuItem exitProgram = new MenuItem("Exit");
		exitProgram.setOnMouseClicked(mouseEvent -> System.exit(0));

		MenuItem playGame = new MenuItem("Play");
		playGame.setOnMouseClicked(mouseEvent -> {

		    game = new Game();


			setMainStage(mainStage);
			game.createNewGame(getMainStage(), getHighScore());
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

    private Stage getMainStage() {
        return mainStage;
    }

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

	public int getHighScore() {
		return highScore;
	}

}
