package utils;

import java.util.ArrayList;
import lab_6_Package.Monster;

public class Visuals 
{
	public static void see(ArrayList<Monster> array, int pivot, int left, int right)
	{
		//Output.type((array.stream().map(monster -> String.format("%5f", monster.getHealth())).collect(Collectors.joining("|", "[", "]"))));
		
		System.out.print("[");
		for(int i = 0; i < array.size() - 1; i++)
		{
			String mark = "";
			mark += i == left ? "L" : " ";
			mark += i == pivot ? "P" : " ";
			mark += i == right ? "R" : " ";			
			System.out.printf("  %s", mark);
			System.out.print("|");
		}
		System.out.println("]");
	}
	
}
