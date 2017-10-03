package utils;

public class Print 
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
	public static void out(String... output)
	{
		for(String index : output)
		{
			type(index);
		}
	}
	public static void setDelay(int milliSeconds)
	{
		if(milliSeconds < 0) Print.milliSeconds = 0;
		else if(milliSeconds > 1000) Print.milliSeconds = 1000;
		else Print.milliSeconds = milliSeconds;
	}
}
