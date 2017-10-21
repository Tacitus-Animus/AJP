package utils.search;

import java.util.ArrayList;
import java.util.Optional;
import java.util.function.BiFunction;

public class BinarySearch<T> implements Search<T> {

	@Override
	public Optional<T> search(ArrayList<T> list, BiFunction<String, T, Integer> searchStrategy, String searchValue) 
	{
		return binarySearch(list, 0, list.size() - 1, searchStrategy, searchValue);
	}

	private Optional<T> binarySearch(ArrayList<T> list, int front, int end, BiFunction<String, T, Integer> searchStrategy, String searchValue)
	{ 
		int mid = front + ((end - front) / 2);
				
		int result = searchStrategy.apply(searchValue, list.get(mid));
						
		if(result == 0) 
		{
			System.out.println("Found.");
			return Optional.ofNullable(list.get(mid));
		}
		
		if(result < 0 && mid > 0) return binarySearch(list, front, mid - 1, searchStrategy, searchValue);
		
		if(result > 0 && end > mid) return binarySearch(list, mid + 1, end, searchStrategy, searchValue);
		
		System.out.println("Not found.");
		return Optional.empty();
	}
	
}
