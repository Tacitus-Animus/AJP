package utils.io;

import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

/**
* @author  Alexander J Paul
* @version 1.1
* @since   2017-SEP-11 
 */
public class Output 
{
	private static int milliSeconds = 250;
	/**
	 * This method prints out String in typewriter like fashion.
	 * @param output - String to be printed out.
	 */
	public static void type(String output)
	{
		for(char index : output.toCharArray())
		{
			System.out.print(index);
			try {
				Thread.sleep(milliSeconds);
			} catch (InterruptedException e) {
				//Interrupted
			}
		}
		System.out.println();
	}
	/**
	 * This method prints out Strings in typewriter like fashion.
	 * @param output - Strings to be printed out.
	 */
	public static void type(String... output)
	{
		for(String index : output)
		{
			type(index);
		}
	}
	/**
	 * 
	 * @param milliSeconds - The delay between printing out Strings when type() method is invoked.
	 */
	public static void setDelay(@SuppressWarnings("hiding") int milliSeconds)
	{
		if(milliSeconds < 0) Output.milliSeconds = 0;
		else if(milliSeconds > 1000) Output.milliSeconds = 1000;
		else Output.milliSeconds = milliSeconds;
	}
	
	public static void testSpeed(String taskName, Runnable task) {
		System.out.println("Start " + taskName);
		long start = System.currentTimeMillis();
		
		task.run();
		
		long duration = System.currentTimeMillis() - start;
		System.out.printf("End %s\n"
						+ "Time: %d.%d seconds\n", taskName,TimeUnit.MILLISECONDS.toSeconds(duration), 
															  TimeUnit.MILLISECONDS.toMillis(duration));
		
	}
}
