package dailyCodingProblem;

import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;

/**
 * This problem was recently asked by Google.
 *
 * Given a list of numbers and a number k, return whether any two numbers from the list add up to k.
 * For example, given [10, 15, 3, 7] and k of 17, return true since 10 + 7 is 17.
 *
 * Bonus: Can you do this in one pass?
 * 
 * Side Note:
 * Could use Binary search Approach. Time O(N log N) Space O(1);
 * 
 * @author Alexander Paul
 *
 */
public class ListHasSumOfNumber {

	@Test
	public void test_Brute_Force() {

		final int[] array = new int[] {3, 15, 10, 7};
		final int k = 17;
		
		assertTrue(bruteForce(array, 0, k) == true);
		assertTrue(onePass(array , k) == true);
		
	}

	/**
	 * Recursive brute-force method.
	 * O(N^2)
	 * @param array to check.
	 * @param i current index.
	 * @param k the sum.
	 * @return true if array contains two values that sum up to k.
	 */
	private static boolean bruteForce(int[] array, int i, int k) {
		for(int j = i + 1; j < array.length; j++) {
			if(array[i] + array[j] == k) return true;
		}
		i++;
		if(i == array.length - 1) return false;
		return bruteForce(array, i, k);
	}
	
	/**
	 * Bonus: Do it in one pass; no nested/looping.
	 * <p>
	 * O(N) Since look up in sets is O(1).
	 * <p>
	 * Uses a set to remember previous checked values.
	 * If the first and current index don't sum up to k,
	 * then you can check if the current plus a previous value sums up to k,
	 * else stores the current.
	 * 
	 * @param array to check.
	 * @param k is the sum.
	 * @return true if array contains two values that sum up to k.
	 */
	private static boolean onePass(int[] array, int k) {
		var set = new HashSet<Integer>();
		for(int j = 1; j < array.length; j++) {
			int current = array[j];
			if(array[0] + current == k) return true;
			else if(set.contains(k - current)) return true;
			else set.add(current);
		}
		return false;
	}
	
}
