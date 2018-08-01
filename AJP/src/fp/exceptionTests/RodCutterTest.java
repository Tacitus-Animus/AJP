package fp.exceptionTests;

import java.util.Arrays;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class RodCutterTest {

	@Test
	public void ConciseExceptionTest() throws RodCutterException {
		
		RodCutter rodCutter = new RodCutter();
		
		//see the test difference.
		
		//rodCutter.setPrices(Arrays.asList(1));
		
		TestHelper.assertThrows(RodCutterException.class, () -> rodCutter.maxProfit(0));
		
		//or...
		
		//Assertions.assertThrows(RodCutterException.class, () -> rodCutter.maxProfit(0));
		
		
	}
	
}
