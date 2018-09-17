package testing;

import static testing.Screen.*;

import javafx.stage.Stage;

public class StageSize {

	public static void set(Stage STAGE) {
		//max...
		STAGE.setMaxHeight(MAX_HEIGHT.get());
		STAGE.setMaxWidth(MAX_WIDTH.get());
		//min...
		STAGE.setMinHeight(MIN_HEIGHT.get());
		STAGE.setMinWidth(MIN_WIDTH.get());
		//in-between
		STAGE.setHeight(DEFAULT_HEIGHT.get());
		STAGE.setWidth(DEFAULT_WIDTH.get());
	}
}
