package fp.memoization;

import utils.io.Output;

public class TestFib {

	public static void main(String[] args) {

		Output.testSpeed("Fab for nth of 40...", () -> System.out.println(Fib.fib(40)));

		//1 2 3 4 5 6 7  8...
		//1 1 2 3 5 8 13 21...
	}

}
