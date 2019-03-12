package View;

import Menu.CreateContent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class StageManager {

    public AnchorPane mainPane;
    public Stage mainStage;

    public StageManager(){
            mainPane = new AnchorPane();
            CreateContent contentCreator = new CreateContent();
            Scene mainScene = new Scene(contentCreator.createContent());
            mainScene.setFill(Color.BLACK);
            mainStage = new Stage();
            mainStage.setScene(mainScene);
            mainStage.setTitle("Keyboard Runner");


    }

    public Stage getMainStage(){
        return mainStage;
    }


}
