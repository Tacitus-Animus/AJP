package generics.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import javafx.util.Builder;

public class WildCardCapture {

	public static void main(String[] args) {

		//inferred.
		var implicit = Lists.factory();
		
		//explicit can't be a wild-card.
		var explicit = Lists.<Object>factory();
		
		//nested wild-card works with explicit.
		var explicitWithWild = Lists.<List<?>>factory();
		
		//only top-level is restricted to no wild-cards.
		List<Number> nums = new ArrayList<Number>();
		
		List<? super Number> sink = nums;
		
		List<? extends Number> source = nums;
		
		//adding
		IntStream.rangeClosed(0, 10).forEach(i -> sink.add(i));
		
		var sum = 0.0;
		//putting
		for(Number num : source) {
			sum += num.doubleValue();
		}
		
	}

	private static class Lists{
		static <T> List<T> factory(){
			return new ArrayList<T>();
		}
	}
	
}
