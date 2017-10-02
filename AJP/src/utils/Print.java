package utils;

public class Print 
{
	public static void out(String output)
	{
		for(char index : output.toCharArray())
		{
			System.out.print(index);
			try {
				Thread.sleep(10);
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
			out(index);
		}
	}
}
