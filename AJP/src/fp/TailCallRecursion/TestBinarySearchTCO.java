package fp.TailCallRecursion;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import utils.search.BadBinarySearch;
import utils.search.BinarySearch;
import utils.search.Search;

/**
 * This was to test the performance of a TCO binary search. It works when it comes to memory intensive 
 * task like the the BigFactorial Test, but not with 
 * @author msi
 *
 */
public class TestBinarySearchTCO {

	public static void main(String[] args) {

		ArrayList<Integer> list = IntStream.rangeClosed(1, 100000000).boxed().collect(Collectors.toCollection(ArrayList::new));
			
		run(new BinarySearch<>(), list);
		
		run(new BadBinarySearch<>(), list);
		
	}

	private static void run(Search<Integer, Integer> search, ArrayList<Integer> list) {
		long start = System.nanoTime();
		
		search
		.search(list, (searchVal, index) -> Integer.compare(searchVal, index), Integer.valueOf(5))
		.ifPresent(System.out::println);
		
		System.out.println("Time: " + TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - start));
	}
	
}
