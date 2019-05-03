package Menu;

import Game.Game;
import SubScene.AboutSubScene;
import SubScene.HelpSubScene;
import SubScene.TutorialSubScene;
import javafx.animation.AnimationTimer;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CreateContent{

	private ImageView background1 = new ImageView("background/plx-1.png");
	private ImageView background2 = new ImageView("background/plx-2.png");
	private ImageView background2_2 = new ImageView("background/plx-2.png");
	private ImageView background3 = new ImageView("background/plx-3.png");
	private ImageView background3_3 = new ImageView("background/plx-3.png");
	private ImageView background4 = new ImageView("background/plx-4.png");
	private ImageView background4_4 = new ImageView("background/plx-4.png");
	private ImageView background5 = new ImageView("background/plx-5.png");
	private ImageView background5_5 = new ImageView("background/plx-5.png");

    public static Stage mainStage;
	public static Pane root = new Pane();

    private Game game;
    public static int highScore;
    private KeyboardRunnerSubScene sceneToHide;

    public Parent createContent(){

    	createBackground();
		AnimationTimer timer = new AnimationTimer() {
			@Override
			public void handle(long l) {
				moveBackground();
			}
		};
		timer.start();

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

		MenuItem about = new MenuItem("About");
		about.setOnMouseClicked(mouseEvent -> {
			showSubScene(AboutSubScene.createAboutSubScene());
		});

		MenuItem help = new MenuItem("Help");
		help.setOnMouseClicked(mouseEvent -> {
			showSubScene(HelpSubScene.createHelpSubScene());
		});

		MenuItem tutorial = new MenuItem("Tutorial");
		tutorial.setOnMouseClicked(mouseEvent -> {
			showSubScene(TutorialSubScene.createTutorialSubScene());
		});

		MenuBox hBox = new MenuBox(
				tutorial,
				playGame,
				about,
				help,
				exitProgram);
		hBox.setTranslateX(20);
		hBox.setTranslateY(550);

		root.getChildren().addAll(title, hBox);
		return root;
	}

	private void showSubScene(KeyboardRunnerSubScene subScene){
    	if(sceneToHide != null){
    		sceneToHide.moveSubScene();
		}

    	subScene.moveSubScene();
    	sceneToHide = subScene;

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

	private void createBackground(){
		//Background Images
		background1.setFitHeight(600); background1.setFitWidth(1200);

		background2.setFitHeight(600); background2.setFitWidth(1200);

		background3.setFitHeight(600); background3.setFitWidth(1200);

		background4.setFitHeight(600); background4.setFitWidth(1200);

		background5.setFitHeight(600); background5.setFitWidth(1200);

		background2_2.setFitHeight(600); background2_2.setFitWidth(1200); background2_2.setTranslateX(1200);

		background3_3.setFitHeight(600); background3_3.setFitWidth(1200); background3_3.setTranslateX(1200);

		background4_4.setFitHeight(600); background4_4.setFitWidth(1200); background4_4.setTranslateX(1200);

		background5_5.setFitHeight(600); background5_5.setFitWidth(1200); background5_5.setTranslateX(1200);


		root.getChildren().addAll(background1, background2, background2_2, background3, background3_3, background4, background4_4, background5, background5_5);
	}

	private void moveBackground(){
		background2.setTranslateX(background2.getTranslateX() - 0.3);
		background2_2.setTranslateX(background2_2.getTranslateX() - 0.3);

		background3.setTranslateX(background3.getTranslateX() - 0.8);
		background3_3.setTranslateX(background3_3.getTranslateX() - 0.8);

		background4.setTranslateX(background4.getTranslateX() - 1.2);
		background4_4.setTranslateX(background4_4.getTranslateX() - 1.2);

		background5.setTranslateX(background5.getTranslateX() - 2.5);
		background5_5.setTranslateX(background5_5.getTranslateX() - 2.5);

		if(background2.getTranslateX() < -1200)
			background2.setTranslateX(1190);

		if(background2_2.getTranslateX() < -1200)
			background2_2.setTranslateX(1190);

		if(background3.getTranslateX() < -1200)
			background3.setTranslateX(1190);

		if(background3_3.getTranslateX() < -1200)
			background3_3.setTranslateX(1190);

		if(background4.getTranslateX() < -1200)
			background4.setTranslateX(1190);

		if(background4_4.getTranslateX() < -1200)
			background4_4.setTranslateX(1190);

		if(background5.getTranslateX() < -1200)
			background5.setTranslateX(1190);

		if(background5_5.getTranslateX() < -1200)
			background5_5.setTranslateX(1190);

	}

}
