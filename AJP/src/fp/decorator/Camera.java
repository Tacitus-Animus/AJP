package fp.decorator;

import java.awt.Color;
import java.util.function.Function;
import java.util.stream.Stream;

public class Camera {

	private Function<Color,Color> filter;

	@SuppressWarnings("unchecked")
	public Camera() {
		setFilters();
	}
	
	public Color capture(final Color inputColor) {
		return filter.apply(inputColor);
	}
	
	@SuppressWarnings("unchecked")
	public void setFilters(final Function<Color, Color>... filters) {
		filter = Stream.of(filters)
				.reduce((filter, next) -> filter.compose(next))
				.orElseGet(Function::identity);
	}
	
	
}
