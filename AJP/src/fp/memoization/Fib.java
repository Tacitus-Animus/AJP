package fp.memoization;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Used to calculate a value in the fibanacci sequence.
 * Uses Memo-ization to cache values. 
 * Since the fibinacci sequence has over-lapping recursion,
 * the use of memo-ization speeds up quadratic time O(n^2) -increase fourfold- to be logarithmic O(log n)-increased by constant amount.
 * 
 * <p>Naive approach would be to call the fib method where the function would be.
 * Here the overLappingRecursive function implementation is in the Memoizer Class.
 * It was named as such here for brevity.
 * Same with numberToBeCalcForNth; the recursion comes into play when the bi-function is passed into the callMemoized method 
 * along with the numberToBeCalc.
 * 
 * <p>Here we can see the Fib algorithm.
 * But, the Memoizer keeps the memoization process under the hood.
 * 
 * <strong>Credit for the Code goes to Functional Programming in Java by Venkat Subramaniam.</strong>
 * @author Alexander Paul
 * @see Memoizer
 */
public class Fib {
	
	/**
	 * @param nthTerm to be computed for the fibinacci sequence.
	 * @return the computed value for the nth Term.
	 */
	public static int fib(int nthTerm) { 
		
		BiFunction<Function<Integer, Integer>, Integer, Integer> fibFunct = (memoizeIfAbsentOrElseGet, nth) -> {
				return nth == 1 || nth == 2 ? 1 : memoizeIfAbsentOrElseGet.apply(nth - 1) + memoizeIfAbsentOrElseGet.apply(nth - 2);
		};
		
		return Memoizer.callMemoized(fibFunct, nthTerm);
	}
	
}
