package fp.stubsandweb;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class CalculateNAVTest {

		@Test
		public void computeStockWorth() {
			
			final CalculateNav nav = new CalculateNav(ticker -> new BigDecimal("6.01"));
			
			BigDecimal expected = new BigDecimal("6010.00");
			
			Assert.assertEquals(0, nav.computeStockWorth("GOOG", 1000).compareTo(expected), 0.001);
			
		}

}
