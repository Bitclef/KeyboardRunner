package Game;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Sprite extends Rectangle {
    final String type;
    boolean isOutOfView = false;
    boolean dead = false;

    Sprite(int x, int y, int w, int h, String type, Color color){
        super(w, h, color);
        this.type = type;
        setTranslateX(x);
        setTranslateY(y);
    }

    Sprite(int x, int y, int w, int h, String type){
        super(w, h);
        this.type = type;
        setTranslateX(x);
        setTranslateY(y);
    }

    void moveLeft(){
        setTranslateX(getTranslateX() - 4);
    }

    void moveUp(){
        setTranslateY(getTranslateY() - 4);
    }

    void moveUp(double i){
        setTranslateY(getTranslateY() - i);
    }

    void moveDown(){
        setTranslateY(getTranslateY() + 5);
    }

}
