package View;

import Menu.CreateContent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class StageManager {

    private Stage mainStage;

    public StageManager(){
            CreateContent contentCreator = new CreateContent();

            Scene mainScene = new Scene(contentCreator.createContent());
            mainStage = new Stage();
            mainStage.setScene(mainScene);
            mainStage.setTitle("Keyboard Runner");
            contentCreator.setMainStage(mainStage);

    }

    public Stage getMainStage(){
        return mainStage;
    }


}
