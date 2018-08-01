package fp.exceptionTests;

import org.junit.Assert;

public class TestHelper {
	
	public static <X extends Throwable> Throwable assertThrows(final Class<X> exceptionClass, final Executable block) {
		try {
			block.run();
		}catch(Throwable ex) {
			if(exceptionClass.isInstance(ex)) { 
				return ex;
			}
		}
		
		Assert.fail("Failed to throw expected Exception ");
				return null;
		
	}
	
}
