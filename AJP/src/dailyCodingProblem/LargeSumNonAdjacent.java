package dailyCodingProblem;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Objects;

import org.junit.jupiter.api.Test;

/**
 * This problem was asked by Airbnb.
 * 
 * Given a list of integers, write a function that returns the largest sum of non-adjacent numbers. Numbers can be 0 or negative.
 * 
 * For example, [2, 4, 6, 2, 5] should return 13, since we pick 2, 6, and 5. [5, 1, 1, 5] should return 10, since we pick 5 and 5.
 * 
 * Follow-up: Can you do this in O(N) time and constant space?
 * 
 * @author Alexander Paul
 *
 */
class LargeSumNonAdjacent {

	@Test
	void test() {
		System.out.println(lrgSumNoAdjace(new int[] {5, 1, 1, 5, 9, 1, 9, 1}));
		
		//assertTrue(lrgSumNoAdjace(new int[] {2,4,6,2,5}) == 13);
		//assertTrue(lrgSumNoAdjace(new int[] {5, 1, 1, 5}) == 10);
		//assertTrue(lrgSumNoAdjace(new int[] {5, 1, 1, 5, 9, 1, 9, 1}) == 24);
		
	}

	private int lrgSumNoAdjace(int[] array) {
		Objects.requireNonNull(array);
		if(array.length == 1) return array[0];
		if(array.length == 2) return array[0] > array[1] ? array[0] : array[1];
		
		
		
		return 0;
	}

	
	
}
