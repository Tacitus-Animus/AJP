package utils.sort;

import java.util.ArrayList;
import java.util.Comparator;

public class BackwardsBubbleSort<T> extends Sort<T> {

	@Override
	public void sort(ArrayList<T> list, Comparator<T> compareStrategy) 
	{
		for(int start = 0; start < list.size() - 1; start++)
		{
			for(int i = list.size() - 1; i > start; i--)
			{
				int result = compareStrategy.compare(list.get(i - 1), list.get(i));
				if(result < 0) swap(list, i, i - 1);
			}
		}
	}

}
