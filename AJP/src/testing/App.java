package testing;


import java.net.URL;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;

//import com.sun.javafx.application.ParametersImpl;
//import com.sun.javafx.binding.BidirectionalBinding;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class App extends Application {
	
	private static final MenuItem PANE_ADD = new MenuItem("Pane"); 
	
	private static final Menu MENU_ADD = new Menu("Add", new Rectangle(5,5), PANE_ADD);
	
	private static final Canvas canvas = new Canvas();
	
	private static final MenuBar MENUBAR = new MenuBar(MENU_ADD);
	
	private static final BorderPane PARENT = new BorderPane();
	
	private static final Scene SCENE = new Scene(PARENT);
	
	private static final Stage STAGE = new Stage();
	
	
	
	@Override
	public void init() throws Exception {
		System.out.println("Initializing...");
		setUpMenuBar();
		StageSize.set(STAGE);
	}

	private void setUpMenuBar() {
		
	
					
		//BorderStroke bs = new BorderStroke(Paint.valueOf("RED"), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.FULL);
		
		//Background bg = new Background(new BackgroundImage(new Image("/testPaint.png"), null, null, BackgroundPosition.CENTER, null));
		
		PARENT.setTop(MENUBAR);
		PARENT.setCenter(new ImageView("testPaint.png"));
		
	}
	
	@Override
	public void start(Stage nullStage) throws Exception {
		System.out.println("Starting...");
		//STAGE.setTitle(ParametersImpl.getParameters(this).getNamed().get("Title"));
		STAGE.setScene(SCENE);
		STAGE.show();
	}
	
	@Override
	public void stop() {
		System.out.println("Stopping...");
	}

}
