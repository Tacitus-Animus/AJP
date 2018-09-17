package fp.TailCallRecursion;

import java.math.BigInteger;

import static java.math.BigInteger.ONE;

public class BigFactorial {
	
	public static BigInteger factorial(final int number) {
		return factorialTailRec(ONE, BigInteger.valueOf(number)).invoke();
	}
	
	private static TailCall<BigInteger> factorialTailRec(final BigInteger factorial, final BigInteger number){
		if(number.equals(ONE)) {
			return TailCalls.done(factorial);
		}else {
			return TailCalls.call(() ->	{
				return factorialTailRec(multiply(factorial, number), decrement(number));
			});
		}
	}
	
	public static BigInteger decrement(final BigInteger number) {
		return number.subtract(ONE);
	}
	
	public static BigInteger multiply(final BigInteger first, final BigInteger second) {
		return first.multiply(second);
	}
	
}
