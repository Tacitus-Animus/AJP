package utils.sort;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * This Sort class sorts by the means of the selection sort algorithm used for sorting a list of type T
 * <p> using Comparator of type T..
 * @author Alexander J Paul
 * @since 10-OCT-2017
 * @version 1.3
 * @param <T> - The type specific to the this Sort Class.
 */
public class SelectionSort<T> extends Sort<T> {

	@Override
	public void sort(ArrayList<T> list, Comparator<T> compareStrategy)
	{
		for(int end = list.size() - 1; end > 0; end--) 
		{	
			int maxIndex = end;		
			
			for(int checkIndex = 0; checkIndex < end; checkIndex++) //The selection.
			{																		
				int result = compareStrategy.compare(list.get(checkIndex), list.get(maxIndex));
				
				if(result > 0) maxIndex = checkIndex;
			}
			swap(list, end, maxIndex);
		}		
	}
}
