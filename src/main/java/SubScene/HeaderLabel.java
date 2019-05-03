package SubScene;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class HeaderLabel extends Label {

    private final static String font = "/font.ttf";

    public HeaderLabel(String text){
        setPrefWidth(380);
        setPrefHeight(49);
        setLabelFont();
        setText(text);
        setWrapText(true);
        setAlignment(Pos.CENTER);

    }

    private void setLabelFont(){
        setFont(Font.loadFont(getClass().getResourceAsStream(font), 30));
    }
}
