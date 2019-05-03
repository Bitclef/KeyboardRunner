package SubScene;

import Menu.CreateContent;
import Menu.KeyboardRunnerSubScene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class AboutSubScene {

    public static KeyboardRunnerSubScene createAboutSubScene(){
        KeyboardRunnerSubScene aboutSubScene = new KeyboardRunnerSubScene();
        CreateContent.root.getChildren().add(aboutSubScene);

        aboutSubScene.getPane().getChildren().add(headerText());
        aboutSubScene.getPane().getChildren().add(aboutMainSection());


        return aboutSubScene;
    }

    private static Label headerText(){
        HeaderLabel infoLabel = new HeaderLabel("ABOUT");

        infoLabel.setLayoutX(110);
        infoLabel.setLayoutY(25);

        return infoLabel;
    }

    private static GridPane aboutMainSection(){
        GridPane pane = new GridPane();
        pane.setVgap(25);
        pane.setHgap(25);

        pane.add(text("Miami University - CIT\n 2019", 25), 0,0);
        pane.add(text("Capstone Project \n Martin Archer", 25),0, 1);
        pane.add(text("Created using JavaFX \n Thank you for playing!", 25),0, 2);

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

}
