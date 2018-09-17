package testing;

//import com.sun.javafx.application.ParametersImpl;
//import com.sun.javafx.application.LauncherImpl;
import javafx.application.Application;

public class TestApp {

	//so much indirection... security?
	public static void main(String[] args) {
		
		Application.launch(App.class, "--Title=Test");
		//which calls
		//LauncherImpl.launchApplication(appClass, args);
		//which creates a preloaderclass for the App.class... then calls...
		//launchApplication(appClass, preloaderClass, args);
		//which creates a new Thread... that is started. Which calls...
		//launchApplication1(appClass, preloaderClass, args);
		//which creates a preloader from the preloaderClass of App.class...
		//gets the constructor from the preloader...
		//create a new instance using the constructor from the preloader from the preloaderclass of the App.class which is ultimately the App.
		
		//Also launchApplication1 will register the args with the right AppClass
		//ParametersImpl.registerParameters(pldr.get(), new ParametersImpl(args));
		//Setting the title by calling STAGE.setTitle(ParametersImpl.getParameters(this).getNamed().get("Title"));
		//or for unnamed...
		//...getParameters(this).getRaw().get(0);
	}

}
