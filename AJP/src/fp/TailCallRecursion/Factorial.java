package fp.TailCallRecursion;

public class Factorial {

	public static int factorial(final int number) {
		return factorialTailRec(1, number).invoke();
	}
	
	private static TailCall<Integer> factorialTailRec(final int factorial, int number){
		if(number == 1) {
			return TailCalls.done(factorial);
		}else {
			return TailCalls.call(() -> factorialTailRec(factorial * number, number - 1));
		}
	}
	
}
