package utils;

public class Output 
{
	private static int milliSeconds = 50;
	
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
	public static void type(String... output)
	{
		for(String index : output)
		{
			type(index);
		}
	}
	public static void setDelay(int milliSeconds)
	{
		if(milliSeconds < 0) Output.milliSeconds = 0;
		else if(milliSeconds > 1000) Output.milliSeconds = 1000;
		else Output.milliSeconds = milliSeconds;
	}
}
