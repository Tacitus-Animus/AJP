package fp.stubsandweb;

import java.math.BigDecimal;
import java.util.function.Function;

public class CalculateNav {
		
	final private Function<String, BigDecimal> priceFinder;
	
	public CalculateNav(final Function<String, BigDecimal> priceFinder) {
		this.priceFinder = priceFinder;
	}
	
	public BigDecimal computeStockWorth(final String ticker, final int shares) {
		return priceFinder.apply(ticker).multiply(BigDecimal.valueOf(shares));
	}
	
}
