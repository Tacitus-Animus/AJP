package fp.TailCallRecursion;

import java.util.stream.Stream;

/**
 * The javadoc is my own understanding based on book and author specified below.
 * 
 * <p>This Helper Class for TailCalls returns proper TailCalls for a recursive task.
 * The use of a simple recursive Factorial Example will be used.
 * For example: 
 * 
 * <pre>{@code 
 * private static TailCall<Integer> factorialTailRec(final int factorial, int number){
 *  if(number == 1) {
 *	 return TailCalls.done(factorial);
 *	}else {
 *	 return TailCalls.call(() -> factorialTailRec(factorial * number, number - 1));
 *	}
 * }
 * }</pre>
 * 
 * <p>Recusive Task require a start/end point; this example being the {@code final int number} being the end point.
 * If the end point is reached, a {@code TailCall<T>} is returned that simply returns the final resulting value.
 * It will also make {@link TailCall#isComplete()} return true;
 * If end point is not reached, a {@code TailCall<T>} is returned that has the next Factorial calculation.
 * This Helper class uses the {@link #call(TailCall)} and {@link #done(T)} methods in this endeavor.
 * 
 * <p>When the {@code call} method is called, a lambda is passed into it. 
 * Since the {@code call} method needs a {@code TailCall<T>}, the compiler will recognize the lambda
 * as the code to override the {@link TailCall#apply()} method.
 * Example of a Lambda: 
 * 
 * <pre>{@code 
 * () -> factorialTailRec(factorial * number, number - 1);
 * }</pre>
 * 
 * <p>In this case, the TailCall has to be invoked first to actually perform the {@code calls} and {@code done} methods.
 * The {@link TailCall#invoke()} will return the actual value made my the TailCalls by the {@link TailCall#result()};
 * Like so: 
 * 
 * <pre>
 * {@code 
 * public static int factorial(final int number) {
 *  return factorialTailRec(1, number).invoke();
 * }
 * }</pre>
 * 
 * <p>When invoked:
 * 
 * <pre>{@code
 * default T invoke() {
 *  return Stream.iterate(this, TailCall::apply)
 *	 .filter(TailCall::isComplete)
 *	 .findFirst()
 *   .get()
 *   .result();
 * }
 * }</pre>
 * 
 * <p>The {@link Stream#iterate(Object, java.util.function.UnaryOperator)} will create a {@code Stream} of {@code TailCall<T>}s.
 * The {@code TailCall}s will perform the {@code apply} method, accumulating the result, in this case, the factorial.
 * When the {@code Stream} reaches a {@code TailCall} that returns a true {@code isComplete} flag,
 * The Stream calls the {@code result} method instead of {@code apply} method.
 * Lambdas aren't evaluated or invoked until reached at that point in the program.
 * This is where the name "Tail Recursion" comes from. The evaluation happens at the end of the recursion, not at the beginning.
 * So that way, no information as to be put on the memory stack to remember the previous method call on {@code FactorialTailRec}.
 * The lambda will pass the accumulated data to the next {@code TailCall} and so on.
 * @author Alex Paul
 * @author Credit for the Code goes to Functional Programming in Java by Venkat Subramaniam
 *
 */
public class TailCalls {

	public static <T> TailCall<T> call(final TailCall<T> nextCall) { return nextCall; }
	
	public static <T> TailCall<T> done(final T value){
		return new TailCall<T>() {
		
			@Override
			public boolean isComplete() {
				return true;
			}
			
			@Override
			public T result() {
				return value;
			}
			
			@Override
			public TailCall<T> apply() { throw new Error("Not Implemented"); }
			
		};
	}
	
}
