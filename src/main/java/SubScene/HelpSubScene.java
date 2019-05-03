package SubScene;

import Menu.CreateContent;
import Menu.KeyboardRunnerSubScene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class HelpSubScene {

    public static KeyboardRunnerSubScene createHelpSubScene(){
    KeyboardRunnerSubScene helpSubScene = new KeyboardRunnerSubScene();

    CreateContent.root.getChildren().add(helpSubScene);

        helpSubScene.getPane().getChildren().add(headerText());
        helpSubScene.getPane().getChildren().add(helpMainSection());


        return helpSubScene;
}

    private static Label headerText(){
        Label infoLabel = new Label("TEXT");
        infoLabel.setLayoutX(110);
        infoLabel.setLayoutY(25);

        return infoLabel;
    }

    private static GridPane helpMainSection(){
        GridPane pane = new GridPane();
        pane.setVgap(25);
        pane.setHgap(25);

        pane.add(text("This is your ship, \n treat her well.", 18), 2,0);
        //pane.add(new ImageView("/ShipChooser/meteor_brown.png"), 0, 1);
        pane.add(text("Use the arrow keys to avoid \n meteoroids", 18),2, 1);
        //pane.add(new ImageView("/gold_star.png"),0,2);
        pane.add(text("But don't forget to \n grab stars on the way!", 18),2, 2);



        pane.setLayoutX(300 - (118 * 2));
        pane.setLayoutY(100);

        return pane;
    }

    private static Text text(String in, int size){
        Text text = new Text();

        text.setText(in);
        text.setTextAlignment(TextAlignment.CENTER);

        return text;
    }
}
