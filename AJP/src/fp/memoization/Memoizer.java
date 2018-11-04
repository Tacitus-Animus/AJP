 package fp.memoization;

import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Used to speed up overlapping recursive algorithms such as the {@link RodCutterMemo Rod-Cutter Problem} or the {@link Fib Fibanacci Sequence}.
 * <p>Naive Code:
 * 
 * <pre>{@code
 *  fibanacci(nth){
 *   return nth == 1 || nth == 2 ? 1 : fibanacci(nth - 1) + fibanacci(nth - 2);
 * }</pre>
 * 
 * <p>Memoized Code:
 * 
 * <pre><code>
 *  fibanacci(nth){
 *   BiFunction<Function<Integer, Integer>, Integer, Integer> fibFunction = (underTheHoodMemoizer, nth) -> {
 *    return nth == 1 || nth == 2 ? 1 : memoizeIfAbsentOrElseGet.apply(nth - 1) + memoizeIfAbsentOrElseGet.apply(nth - 2);
 *   };
 *   Memoizer.callMemoized(fibFunction, nth);
 *  }
 * </code></pre>
 * 
 * <p>Here, the fibanacci method isn't recursively called. 
 * It's replaced by function that does the work for us under the hood in the Memoizer Class.
 * The underTheHoodMemoizer will return the value for the nth term if it exists else,
 * recursively calls the fibFunction again to cache the value for later use.
 * 
 * <p>Eventually, The overlapping recursive calls will call to return values for the same nth terms that have already been computed.
 * Unlike the naive approach, the Memoizer will return the cached value already computed, saving time. 
 * 
 * <strong>Credit for the Code goes to Functional Programming in Java by Venkat Subramaniam</strong>
 * @author Alexander Paul
 *
 */
public class Memoizer {

	/**
	 * @param overlappingRecursiveFunction recursively called if value wasn't compute for.
	 * @param value that is to computed.
	 * @return R result.
	 */
	public static <T,R> R callMemoized(final BiFunction<Function<T, R>, T, R> overlappingRecursiveFunction, final T value) {
		
		Function<T,R> memoizeIfAbsentOrElseGet = new Function<T, R>() {
 
			//causes ConcurrentModificationException for HashMap; source code isn't clear where exception is thrown.
			//or causes IllegalStateException for ConcurrentHashMap; Source code reveals clear Contract "No Modification in method"[Paraphrased]
			//Found Solution after some reading on Java Generics and Collections: Speed Up the Java Developement by Maurice Naftalin.
			//By accident, in the book it mentions this using weakly consistent iterators that don't throw ConcurrentModificationException
			//CAS(Compare and Swap) mechanism is what is used so methods such as computeIfAbsent work with recursive updates.
			//Still need to learn more about skip lists...
			private final Map<T, R> cachedValues = new ConcurrentSkipListMap<>();
			
			public R apply(final T value) {	
				return cachedValues.computeIfAbsent(value, key -> overlappingRecursiveFunction.apply(this, key));
			}
		};
		return memoizeIfAbsentOrElseGet.apply(value);
	}

}
