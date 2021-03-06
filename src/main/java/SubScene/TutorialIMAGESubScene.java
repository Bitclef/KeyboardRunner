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

public class TutorialIMAGESubScene {

    private static KeyboardRunnerSubScene sceneToHide;

    public static KeyboardRunnerSubScene createTutorialSubScene(){
        KeyboardRunnerSubScene tutorialSubScene = new KeyboardRunnerSubScene();
        CreateContent.root.getChildren().add(tutorialSubScene);

        tutorialSubScene.getPane().getChildren().add(headerText());
        tutorialSubScene.getPane().getChildren().add(tutorialMainSection());
        tutorialSubScene.getPane().getChildren().add(createNextButton());


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

        ImageView posture = new ImageView("tutorial/posture.png");
        posture.setFitWidth(240);
        posture.setFitHeight(230);

        pane.add(text("This image will help!.", 18), 1,0);
        pane.add((posture),1,2);


        pane.setLayoutX(300 - (118 * 2));
        pane.setLayoutY(100);

        return pane;
    }

    private static Button createNextButton(){
        Button nextButton = new Button("NEXT");
        nextButton.setLayoutX(525);
        nextButton.setLayoutY(350);

        nextButton.setOnMouseClicked(mouseEvent -> {
            showSubScene(TutorialPageFourSubScene.createTutorialSubScene());
        });
        return nextButton;
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
