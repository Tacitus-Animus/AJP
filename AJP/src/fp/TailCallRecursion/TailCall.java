package fp.TailCallRecursion;

import java.util.stream.Stream;

/**
 * Tail Call is used in recursive tasks that are memory heavy like Big Integer Factorial.
 * This and with the use Lambdas makes it possible for java to use Tail Call Optimization.
 * This uses lazy initialization and invocation. In turn, doesn't cause stack-overflow in recursive tasks.
 * The Helper Class TailCalls is used as the driver.
 * @author Alex Paul
 * @see TailCalls
 * @param <T> to be the accumulated result of a recursive operation.
 */
@FunctionalInterface
public interface TailCall<T> {

	TailCall<T> apply();
	
	default boolean isComplete() { return false; }
	
	default T result() { throw new Error("Not implemented"); }
	
	default T invoke() {
		return Stream.iterate(this, TailCall::apply)
				.filter(TailCall::isComplete)
				.findFirst()
				.get()
				.result();
	}
	
}
