package utils.search;

import java.util.ArrayList;
import java.util.Optional;
import java.util.function.BiFunction;

public class LinearSearch<T> implements Search<T> 
{
	@Override
	public Optional<T> search(ArrayList<T> list, BiFunction<String, T, Integer> searchStrategy, String searchValue)
	{
		for(T element : list)
		{
			if(searchStrategy.apply(searchValue, element) == 0)
			{
				System.out.println("Found");
				return Optional.ofNullable(element);
			}
		}
		System.out.println("Not found.");
		return Optional.empty();
	}
}
