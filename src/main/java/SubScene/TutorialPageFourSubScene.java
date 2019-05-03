package SubScene;

import Menu.CreateContent;
import Menu.KeyboardRunnerSubScene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class TutorialPageFourSubScene {

    private static KeyboardRunnerSubScene sceneToHide;

    public static KeyboardRunnerSubScene createTutorialSubScene(){
        KeyboardRunnerSubScene tutorialSubScene = new KeyboardRunnerSubScene();
        CreateContent.root.getChildren().add(tutorialSubScene);

        tutorialSubScene.getPane().getChildren().add(headerText());
        tutorialSubScene.getPane().getChildren().add(tutorialMainSection());

        return tutorialSubScene;
    }

    private static Label headerText(){
        HeaderLabel infoLabel = new HeaderLabel("GETTING STARTED");
        infoLabel.setLayoutX(110);
        infoLabel.setLayoutY(25);

        return infoLabel;
    }

    private static GridPane tutorialMainSection(){
        GridPane pane = new GridPane();
        pane.setVgap(25);
        pane.setHgap(25);

        pane.add(text("Can a game actually help improve typing? \n YES!", 18), 0,0);
        pane.add(text("Studies have shown that: \n Younger children, \n Teenagers, \n and those college and beyond", 18),0, 1);
        pane.add(text("Can not only improve their typing skills \n but also reaction time and cognitive ability!", 18),0, 2);
        pane.add(text("PRESS PLAY TO START!", 18),0, 3);

        //pane.add(new ImageView("tutorial/fingers.jpg"),1,2);


        pane.setLayoutX(300 - (118 * 2));
        pane.setLayoutY(100);

        return pane;
    }


    private static Text text(String in, int size){
        Text text = new Text();

        text.setText(in);
        text.setTextAlignment(TextAlignment.CENTER);
        text.setFont(Font.loadFont(HelpSubScene.class.getResourceAsStream("/font.ttf"), size));

        return text;
    }

    private static void showSubScene(KeyboardRunnerSubScene subScene){
        if(sceneToHide != null){
            sceneToHide.moveSubScene();
        }

        subScene.moveSubScene();
        sceneToHide = subScene;

    }

}
