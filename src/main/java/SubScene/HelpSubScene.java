package SubScene;

import Menu.CreateContent;
import Menu.KeyboardRunnerSubScene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
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
        HeaderLabel infoLabel = new HeaderLabel("QUICK START");
        infoLabel.setLayoutX(110);
        infoLabel.setLayoutY(25);

        return infoLabel;
    }

    private static GridPane helpMainSection(){
        ImageView rock = new ImageView("rock.png");
        rock.setFitHeight(70);
        rock.setFitWidth(100);

        ImageView keyboardLetter = new ImageView("keyboard/q.png");
        keyboardLetter.setFitHeight(65);
        keyboardLetter.setFitWidth(65);

        ImageView run = new ImageView("run.gif");
        run.setFitHeight(70);
        run.setFitWidth(45);

        GridPane pane = new GridPane();
        pane.setVgap(25);
        pane.setHgap(25);

        pane.add((run), 0, 0);
        pane.add(text("This is Steve! \n Lead him on his adventure.", 18), 2,0);
        pane.add((rock),0,1);
        pane.add(text("Your goal is to Jump over these obstacles!\n ", 18),2, 1);
        pane.add((keyboardLetter),0,2);
        pane.add(text("Use the keys to type quickly! \n Steve will automatically jump", 18),2, 2);



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
