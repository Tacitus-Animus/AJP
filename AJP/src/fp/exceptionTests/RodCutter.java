package fp.exceptionTests;

import java.util.List;

public class RodCutter {

	
	public void setPrices(final List<Integer> prices) throws RodCutterException {
		throw new RodCutterException();
	}
	
	public int maxProfit(final int length) throws RodCutterException {
		if(length == 0) throw new RodCutterException();	
		return 0;
	}
	
}
