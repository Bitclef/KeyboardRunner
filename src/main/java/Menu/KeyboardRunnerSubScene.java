package Menu;

import javafx.animation.TranslateTransition;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.util.Duration;

public class KeyboardRunnerSubScene extends SubScene {

    private boolean isHidden;
    private final static String BACKGROUND_IMAGE = "background/plx-1.png";


    public KeyboardRunnerSubScene(){
        super(new AnchorPane(), 600, 400);


        BackgroundImage image = new BackgroundImage(new Image(BACKGROUND_IMAGE, 600, 400, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);

        AnchorPane root2 = (AnchorPane) this.getRoot();
        root2.setBackground(new Background(image));

        isHidden = true;

        setLayoutX(1024);
        setLayoutY(80);
    }

    public void moveSubScene(){
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(0.3));
        transition.setNode(this);

        if(isHidden){
            transition.setToX(-800);
            isHidden = false;
        }else {
            transition.setToX(100);
            isHidden = true;
        }

        transition.play();
    }
    public AnchorPane getPane(){
        return (AnchorPane) this.getRoot();
    }

}
