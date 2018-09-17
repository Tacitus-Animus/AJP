package fp.memoization;

import java.util.function.BiFunction;
import java.util.function.Function;

public class Fib {
	
	public static int fib(int number) {
		
		BiFunction<Function<Integer, Integer>, Integer, Integer> fibFunct = (function, num) -> {
				return num == 1 || num == 2 ? 1 : function.apply(num - 1) + function.apply(num - 2);
		};
		
		return Memoizer.callMemoized(fibFunct, number);
	}
	
}
