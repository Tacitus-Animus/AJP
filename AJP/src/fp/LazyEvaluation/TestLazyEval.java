package fp.LazyEvaluation;

import java.util.function.Supplier;

import utils.io.Output;

public class TestLazyEval {

	public static void main(String[] args) {

		System.out.println(test(()-> evaluate(10), ()-> evaluate(1)));
		
	}

	static boolean test(Supplier<Boolean> eval1, Supplier<Boolean> eval2) {
		return eval1.get() && eval2.get();
	}
	
	static Supplier<Boolean> getSupplier(int i) {
		return () -> evaluate(i); 
	}
	
	static boolean evaluate(int i) {
		System.out.println("Evaluating " + i);
		simTimeConsumingTask();
		return i >= 100;
	}
	
	static void simTimeConsumingTask() {
		Output.type("..........");
	}
	
}
