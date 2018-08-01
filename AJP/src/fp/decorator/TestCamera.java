package fp.decorator;

import java.awt.Color;
import java.util.function.Consumer;

public class TestCamera {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		final Camera camera = new Camera();
		
		
		final Consumer<String> printCaptured = filterInfo -> 
		System.out.println(
				String.format("with %s: %s", 
						filterInfo, camera.capture(new Color(200,100,200))));
		
		printCaptured.accept("No Filter");
		
		camera.setFilters(Color::brighter);
		
		printCaptured.accept("Brighter Filter");
		
		camera.setFilters(Color::darker);
		
		printCaptured.accept("Darker Filter");
		
		camera.setFilters(Color::brighter, Color::brighter);
		
		printCaptured.accept("More Bright filter");
		
	}

}
