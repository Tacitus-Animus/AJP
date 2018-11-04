package dailyCodingProblem;

import static org.junit.jupiter.api.Assertions.*;

import java.util.function.BiFunction;
import java.util.function.Function;

import org.junit.jupiter.api.Test;

/**
 * This problem was asked by Jane Street.
 *
 * cons(a, b) constructs a pair,
 * and car(pair) and cdr(pair) returns 
 * the first and last element of that pair. 
 * 
 * For example, 
 * car(cons(3, 4)) returns 3, 
 * and cdr(cons(3, 4)) returns 4.
 *
 * Given this implementation of cons:
 *
 * def cons(a, b):
 *   def pair(f):
 *       return f(a, b)
 *   return pair
 * 
 * Implement car and cdr.
 * 
 * @author Alexander Paul
 *
 */
class GetElementsofPair {

	//Solution mentioned closures
	//An anonymous function that takes another anonymous function which takes the 2 elements
	//The car and and cdr take in the function that return either element.
	@Test
	void test() {
		
		assertTrue(car(cons(3,4)) == 3);
		assertTrue(cdr(cons(3,4)) == 4);
		
	}

	/**
	 * This method takes two elements and outputs a function.
	 * 
	 * Since the the parameters are effectively final, 
	 * one can use lexical scoping to save the values from this context for use in another.
	 * 
	 * Which makes this a good example of a closure.
	 * 
	 * @param a is the first element.
	 * @param b is the second element.
	 * @return a Function which takes a BiFunction and returns an Integer.
	 * The BiFunction takes two Integer arguments and returns an Integer.
	 */
	static Function<BiFunction<Integer,Integer, Integer>, Integer> cons(int a, int b) {		
		return biFunction -> biFunction.apply(a, b);
	}
	
	/**
	 * This method invokes the BiFunction's apply method which returns an integer.
	 * This invocation is done via the Function's apply method which also returns an integer. 
	 * 
	 * @param pair is the Function in which to invoke the BiFunction's apply method onto the lexically scoped elements, a and b. 
	 * @return the first element/parameter of the BiFunction.
	 */
	static int car(Function<BiFunction<Integer,Integer, Integer>, Integer> pair) {
		return pair.apply((a,b) -> a);
	}
	
	/**
	 * This method invokes the BiFunction's apply method which returns an integer.
	 * This invocation is done via the Function's apply method which also returns an integer. 
	 * 
	 * Fun Fact: "cdr" is pronounced 'cudder'.
	 * @param pair is the Function in which to invoke the BiFunction's apply method onto the lexically scoped elements, a and b.
	 * @return the second element/parameter of the BiFunction.
	 */
	static int cdr(Function<BiFunction<Integer,Integer, Integer>, Integer> pair) {
		return pair.apply((a,b) -> b);
	}
	
}
