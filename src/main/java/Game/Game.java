package Game;

import javafx.animation.AnimationTimer;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.List;
import java.util.stream.Collectors;

public class Game {

    private Pane root = new Pane();

    private Stage gameStage;
    private Scene gameScene;

    private boolean isUpKeyPressed;
    private boolean isDownKeyPressed;
    private boolean isRightKeyPressed;
    private boolean isLeftKeyPressed;

    private Sprite player = new Sprite(50, 450, 40, 70, "player", Color.BLACK);
    private Sprite floor = new Sprite(0, 520, 1200, 2, "player", Color.BLACK);


    public Game(){
    }

    private List<Sprite> sprites(){
        return root.getChildren().stream().map(n -> (Sprite) n ).collect(Collectors.toList());
    }

    public void createNewGame(Stage mainStage){
        mainStage.hide();

        initializeStage();
        createKeyListeners();

        gameStage.show();
    }

    private void initializeStage() {
        gameStage = new Stage();
        gameScene = new Scene(createContent());
        gameStage.setScene(gameScene);
    }





    private Parent createContent(){ //WHERE THE GAME LOOP IS
        root.setPrefSize(1200, 600);

        //Add player. Was created at the top.
        root.getChildren().add(player);

        //add floor
        root.getChildren().add(floor);

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                onUpdate();
            }
        };
        timer.start();

        return root;
    }

    private void onUpdate(){

        sprites().forEach(s -> {
            switch (s.type){
                case "player":
                    if(isUpKeyPressed)
                        player.moveUp();
                    if(isDownKeyPressed)
                        player.moveDown();
                    if(isRightKeyPressed)
                        player.moveRight();
                    if(isLeftKeyPressed)
                        player.moveLeft();
                    break;
            }
        });
        root.getChildren().removeIf(n -> {
            Sprite s = (Sprite) n;
            return s.dead;
        });

//        if(Math.random() < .01 && !(player.isDead())){
//
//        }
    }

    private void createKeyListeners(){
        gameScene.setOnKeyPressed(event -> {
            switch(event.getCode()){
                case UP:
                    isUpKeyPressed = true;
                    break;
                case DOWN:
                    isDownKeyPressed = true;
                    break;
                case RIGHT:
                    isRightKeyPressed = true;
                    break;
                case LEFT:
                    isLeftKeyPressed = true;
                    break;
            }
        });

        gameScene.setOnKeyReleased(event -> {
            switch(event.getCode()){
                case UP:
                    isUpKeyPressed = false;
                    break;
                case DOWN:
                    isDownKeyPressed = false;
                    break;
                case RIGHT:
                    isRightKeyPressed = false;
                    break;
                case LEFT:
                    isLeftKeyPressed = false;
                    break;
            }
        });
    }

}
