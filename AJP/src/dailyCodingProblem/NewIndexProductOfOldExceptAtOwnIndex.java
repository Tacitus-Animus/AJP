package dailyCodingProblem;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
/**
 * This problem was asked by Uber.
 *
 * Given an array of integers, 
 * return a new array such that each element at index i of the new array 
 * is the product of all the numbers in the original array except the one at i.
 *
 * For example, if our input was [1, 2, 3, 4, 5], 
 * the expected output would be [120, 60, 40, 30, 24]. 
 * If our input was [3, 2, 1], 
 * the expected output would be [2, 3, 6].
 *
 * Follow-up: what if you can't use division?
 * @author Alexander Paul
 *
 */
class NewIndexProductOfOldExceptAtOwnIndex {

	@Test
	void test() {
		int[] array = new int[] {1, 2, 3, 4, 5};
		int[] array2 = new int[] {3, 2, 1};
		
		assertTrue(Arrays.toString(noDivision(array)).toString().equals("[120, 60, 40, 30, 24]"));

		assertTrue(Arrays.toString(noDivision(array2)).toString().equals("[2, 3, 6]"));
		
	}

	/**
	 * 
	 * @param array to get product values while product of individual indexs don't reflect 
	 * 			the same index of the old array. 
	 * @return return an array in which new indexes contain the product of old indexes except the same corresponding 
	 * 			index.
	 */
	private int[] noDivision(int[] array) {
		int[] newArray = new int[array.length];
		int product = 1;
		for(int exclude = 0; exclude < array.length; exclude++) {
			for(int i = 0 ; i < array.length; i ++) {
				product *= i == exclude ? 1 : array[i];
			}
			newArray[exclude] = product;
			product = 1;
		}
		return newArray;
	}

}
