package fp.TailCallRecursion;

public class TestTCO {

	public static void main(String[] args) {

		final int number = 5;
	
		System.out.println(String.format("%.10s...", BigFactorial.factorial(number)));
		
	}

	public static int badFactorial(int fac, int num) throws StackOverflowError{
		if(num == 1) {
			return fac;
		}else {
			return badFactorial(fac * num, num - 1);
		}
	}
	
}
