package fp.memoization;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class RodCutterMemo {
	
	final List<Integer> prices;
	
	/**
	 * When created, user can pass in a price list. 
	 * This list can be used as use cases to find the max profit between these prices.
	 * @param pricesForLength are the prices of a given length of rod. 
	 */
	public RodCutterMemo(final List<Integer> pricesForLength) {
		prices = pricesForLength;
	}
	
	/**
	 * Finds the max profit for the given rod length sold at the prices passed through the constructor.
	 * @param rodLength is the length of a given rod to be tested.
	 * @return the max profit between all uses cases of a given length.
	 */
	public int maxProfit(final int rodLength) {
		
		BiFunction<Function<Integer,Integer>, Integer, Integer> profitfunct = (function, length) -> {
			int profit = (length <= prices.size()) ? prices.get(length - 1) : 0;
			
				for(int i = 1; i < length; i++) {
					int priceWhenCut = function.apply(i) + function.apply(length - i);
					if(profit < priceWhenCut) profit = priceWhenCut;
				}
			return profit;
		};
		
		return Memoizer.callMemoized(profitfunct, rodLength);
		
	}
}
