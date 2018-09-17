package fp.exceptionTests;

import org.junit.Assert;

/**
 * Tests whether executing a block of code will throw error define by exceptionClass Argument.
 * Returns the throwable if succeeded,
 * null if not.
 */
public class TestHelper {
	
	/**
	 * Asserts the exception class defined is thrown, in Executable block of code.
	 * @param exceptionClass - the Error to be thrown.
	 * @param block - the code to be executed that throws the exceptionClass.
	 * @return the error thrown else null.
	 */
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
