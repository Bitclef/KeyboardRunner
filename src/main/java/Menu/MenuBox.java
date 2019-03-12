package Menu;

import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

class MenuBox extends HBox {
	MenuBox(MenuItem...items){
		getChildren().add(createSeparator());

		for(MenuItem item : items){
			getChildren().addAll(item, createSeparator());
		}
	}

	private Line createSeparator(){
		Line sep = new Line();
		sep.setEndY(30);
		sep.setStroke(Color.DARKGRAY);
		return sep;
	}
}
