package fp.stubsandweb;

public class YahooNAVTest {

	public static void main(String[] args) {

		final CalculateNav nav = new CalculateNav(YahooFinance::getPrice);
		
		System.out.println(String.format("100 shares of Google worth: $%.2f", nav.computeStockWorth("GOOG", 100)));
		
	}

}
