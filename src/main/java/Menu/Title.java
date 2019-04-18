package Menu;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

class Title extends StackPane {
	Title(String name){
		Rectangle bg = new Rectangle(750,60);
		bg.setStroke(Color.WHITE);
		bg.setStrokeWidth(2);
		bg.setFill(null);

		Text text = new Text(name);
		text.setFill(Color.WHITE);
		text.setFont(Font.font("Georgia", FontWeight.SEMI_BOLD, 50));

		setAlignment(Pos.CENTER);
		getChildren().addAll(bg,text);
	}
}
