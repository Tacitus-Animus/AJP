package dailyCodingProblem;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;

import org.junit.jupiter.api.Test;

import fp.memoization.Memoizer;
/**
 * This problem was asked by Facebook.
 *
 * Given the mapping a = 1, b = 2, ... z = 26, and an encoded message, count the number of ways it can be decoded.
 *
 * For example, the message '111' would give 3, since it could be decoded as 'aaa', 'ka', and 'ak'.
 *
 * You can assume that the messages are decodable. For example, '001' is not allowed.
 * 
 * Took me 4 days... Wow.
 * 
 * Another day to implement it using memoization.
 * 
 * @author Alexander Paul
 *
 */ 
class DecodeMessage {
	
	@Test
	void testWorks() {
			
		//naive
		assertTrue(decode("111") == 3);
		
		//memoized
		assertTrue(memoDecode("111") == 3);		
		
		//verify
		assertTrue(memoDecode("121724") == decode("121724"));
		
	}
	
	//time was 0.7 seconds for memo.
	//time was 0.201 seconds for naive.
	@Test
	public void testSpeed() {
		//length of 50. 
		utils.io.Output.testSpeed("Memo Decode", () -> memoDecode("12172411342464192695814326121414141835102351121736"));
		utils.io.Output.testSpeed("Naive Decode", () ->    decode("12172411342464192695814326121414141835102351121736"));
				
	}
	
	/**
	 * The solution used memoization; a technique used in dynamic programming.
	 * <p>
	 * This problem can be solved using overlapping recursion.
	 * But with each call, means further calls on already computed values.
	 * This can be solved by caching those computed values to be fetched later.
	 * <p>
	 * With the power of generics, functional programming, and dynamic programming,
	 * We can use closures to encapsulate the memoization by using a generic method call.
	 * <p>
	 * The solution wasn't as much help with the code, 
	 * but the mention of memoization let me to figure out how to implement 
	 * it by trial, error, and past research.
	 * <p>
	 * <strong>Thank you Venkat</strong>
	 * <p>
	 * O(N) time since every index is checked once and computed values are stored.
	 * 
	 * @param message to be decoded
	 * @return number of ways the message can be decoded.
	 */
	static int memoDecode(String message) {
		//String msg = String.valueOf(message);
		
		BiFunction<Function<String, Integer>, String, Integer> decodeFunct = (memo, code) -> {
			int ways = 0;
			
			if(!Objects.equals(code.charAt(0), '0')) {
			    if(code.length() == 1) return 1;
				if((char)(Integer.valueOf(code.substring(0, 2)) + 96) <= 'z') {
					 if(code.length() > 3) ways += memo.apply(code.substring(2));
					 else ways++;
				}
				return memo.apply(code.substring(1)) + ways;
			}
			return ways;
		};
		
		return Memoizer.callMemoized(decodeFunct, message);
		
	}
	
	/**
	 * naive approach without memoization.
	 * Iterate once for the map.
	 * Then more than once for the overlapping recursion.
	 * O(n^2) quadratic change if n was doubled
	 * @param message
	 * @return
	 */
	static int decode(String message) {
		
		var combos = extractChars(message);
	
		int ways = check(combos, 0);
		
		return ways;
	}

	/**
	 * Climbs down the indices of the map for all possible ways.
	 * @param combos is the map to get sizes of each list.
	 * @param index to check.
	 * @return the number of ways to decode an integer.
	 */
	private static int check(HashMap<Integer, List<Character>> combos, int index) {
		int ways = 0;
		if(index == combos.size() - 1) return 1;
		if(combos.get(index).size() > 1) {
			if(index < combos.size() - 2) ways = check(combos, index + 2);
			else ways++;
		}
		return check(combos, index + 1) + ways;
	}

	/**
	 * Brute-Force: Every index in the message -except the last- can possibly be read as 2 letters.
	 * The first letter is the character value of the integer value plus 48 at that given index.
	 * The second letter is the character value of the integer value plus 96 of the 
	 * combined string values of the given index and the next index.
	 * @param message to be extracted for it's char values.
	 * @return map of indexes of their corresponding chars.
	 */
	private static HashMap<Integer, List<Character>> extractChars(String message) {
		char[] msg = message.toCharArray();
		HashMap<Integer, List<Character>> combos = new HashMap<>();
		for(int i = 0; i < msg.length; i++) {
			
			combos.computeIfAbsent(i, (key) -> { //new list for each index.
				var array = new ArrayList<Character>();
				array.add((char)(msg[key]  + 48)); //already chars; just shift of 48 to the letters.
				return array;
			});
			if(i < msg.length - 1) { //add to the list at that index; 
				StringBuilder sb = new StringBuilder();
				sb.append(msg[i]);
				sb.append(msg[i + 1]);
				char combined = (char)(Integer.valueOf(sb.toString()) + 96); //int to char letters needs a shift of 96.
				if(combined <= 'z') { //only add letters. 
					combos.computeIfPresent(i, (key, array) -> {
						array.add(combined);
						return array;
					});
				}	
			}
		}
		return combos;
	}
}
