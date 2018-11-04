package dailyCodingProblem;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

/**
 * This problem was asked by Stripe.
 * 
 * Given an array of integers, 
 * find the first missing positive integer in linear -O(N)- time and space.
 * In other words, find the lowest positive integer that does not exist in the array.
 * The array can contain duplicates and negative numbers as well.
 * 
 * For example, the input [3, 4, -1, 1] should give 2.
 * 
 * The input [1, 2, 0] should give 3.
 * 
 * You can modify the input in-place.
 * 
 * @author Alexander Paul
 *
 */
class FirstMissingPosInt {

	@Test
	void test() {

		var array1 = new int[] {3, 4, -1, 1};
		
		var array2 = new int[] {1, 2, 0};
		
		
		var array3 = new int[] {-1,5, 2, 2, 0, 6, 1, 4, 0};
		
		assertTrue(lowestPosInt(array1) == 2);
		assertTrue(lowestPosInt(array2) == 3);
		assertTrue(lowestPosInt(array3) == 3);
		System.out.println(Arrays.toString(array3));
		
	}

	/**
	 * Since only need the lowest positive int, you only look between values 1 to array length.
	 * That means you can swap values that don't match their respective indexes
	 * and ignore anything below 0 as well as duplicates.
	 * 
	 * Solution mentioned sorting the array, then iterating over it. This wouldn't be O(n) time.
	 * 
	 * Or using a set and a counter to check. This wouldn't be in O(n) space.
	 * 
	 * @param array
	 * @return
	 */
	private static int lowestPosInt(int[] array) {
		int miss = 1;
		int index = 0;
		while(index < array.length) {
			int current = array[index];
			
			if(current <= 0) {
				index++;
				continue;
			}
			
			if(current == miss) miss++;
			
			if(current != index + 1) {
				int swap = array[current - 1];
				if(swap == current || swap <= 0) swap = 0;
				else if(swap == miss) miss++;
				swap(array, index, current - 1);
			}
			index++;
		}
		
		return miss;
	}
	
	static void swap(int[] array, int a, int b) {
		int temp = array[a];
		array[a] = array[b];
		array[b] = temp;
	}
	
}
