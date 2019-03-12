package Game;

import javafx.animation.AnimationTimer;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.time.Duration;
import java.util.List;
import java.util.Timer;
import java.util.stream.Collectors;

public class Game {

    private Pane root = new Pane();

    private double time;

    private Stage gameStage;
    private Scene gameScene;

    private boolean isUpKeyPressed = false;
    private boolean isUpKeyHeld = false;
    private boolean isDownKeyPressed = false;

    private Sprite player = new Sprite(50, 450, 40, 70, "player", Color.BLACK);
    private Sprite floor = new Sprite(0, 520, 1200, 2, "floor", Color.BLACK);
    private Sprite jumpLine = new Sprite(0, 250, 1200, 2, "jumpLine", Color.BLUE);
    private Sprite obstacle;



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
        root.getChildren().add(jumpLine);

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                onUpdate();
            }
        };
        timer.start();

        return root;
    }

    private void addObstacle(){
        if(time > .2 && Math.random() < .01 && !(player.dead)){
            obstacle = new Sprite(1300, 490, 30, 30, "obstacle", Color.DARKOLIVEGREEN);
            root.getChildren().add(obstacle);
        }


    }


    private void onUpdate(){
        time += 0.016;

        sprites().forEach(s -> {
            switch (s.type){
                case "player":
                    if(isUpKeyPressed)
                        jump(player);
                    if(!(isUpKeyPressed) && !(s.getBoundsInParent().intersects(floor.getBoundsInParent())))
                        fall(player);
                    break;
                case "obstacle":
                    s.moveLeft();
                    if(obstacle.getX() < 0){
                        obstacle.isOutOfView = true;
                    }
                    if(s.getBoundsInParent().intersects(player.getBoundsInParent())){
                        player.dead = true;
                    }
                    break;
            }
        });

        addObstacle();




        root.getChildren().removeIf(n -> {
            Sprite s = (Sprite) n;
            return s.dead;
        });

        root.getChildren().removeIf(n -> {
            Sprite s = (Sprite) n;
            return s.isOutOfView;
        });

        if(time > 1){
            time = 0;
        }

    }

    private void jump(Sprite sprite){
        if((sprite.getBoundsInParent().intersects(jumpLine.getBoundsInParent()))){
            isUpKeyPressed = false;
        }

        sprite.moveUp();

        if(isUpKeyHeld){
            jumpLine.moveUp(.5);
        }

    }
    private void fall(Sprite sprite){
        sprite.moveDown();
    }

    private void createKeyListeners(){
        gameScene.setOnKeyPressed(event -> {
            switch(event.getCode()){
                case UP:
                    if(player.getBoundsInParent().intersects(floor.getBoundsInParent()))
                        isUpKeyPressed = true;
                    isUpKeyHeld = true;
                    break;

            }
        });

        gameScene.setOnKeyReleased(event -> {
            switch(event.getCode()){
                case UP:
                    isUpKeyHeld = false;
                    break;
                case DOWN:
                    isDownKeyPressed = false;
                    break;
            }
        });
    }

}
