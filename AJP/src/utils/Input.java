package utils;

import java.math.BigDecimal;
import java.util.Scanner;
//Testing
public class Input 
{
	private static Scanner input = new Scanner(System.in);
	private static String storedString;
	private static char storedChar;

	public static String getStringln(String prompt) 
	{
		while(true) 
		{
			System.out.print(prompt);
			storedString = input.nextLine().trim();
			if(storedString.isEmpty()) System.err.println("Invalid Input: You didn't enter anything.");
			else break;
		}
		return storedString;
	}

	public static String getString(String prompt) 
	{
		while(true) 
		{	
			storedString = getStringln(prompt);
			if(storedString.contains(" ")) System.err.println("Invalid Input: You entered too many variables.");
			else break; 
		}
		return storedString;
	}
	
	public static char getChar(String prompt) 
	{
		while(true) 
		{	
			storedString = getString(prompt);
			if(storedString.length() > 1) System.err.println("Invalid Input: Too long.");
			else break;
		}
		return storedString.charAt(0);
	}
	
	public static char getLetter(String prompt) 
	{
		while(true) 
		{
			storedChar = getChar(prompt);
			if(!Character.isLetter(storedChar)) System.err.println("Invalid Input: Not a Letter.");
			else break;
		}
		return storedChar;
	}
	
	public static char getDigit(String prompt) 
	{
		while(true) 
		{
			storedChar = getChar(prompt);
			if(!Character.isDigit(storedChar)) System.err.println("Invalid Input: Not a Digit.");
			else break;
		}
		return storedChar;
	}
	
	public static Number getNumber(String prompt)
	{
		while(true) 
		{
			try{ 
				return new BigDecimal(getString(prompt)); 
			}catch(NumberFormatException E){ 
				System.err.println("Invalid Input: Not a number."); 
			}
		}	
	}
	
	public static double getDouble(String prompt) 
	{
		return getNumber(prompt).doubleValue();
	}
	
	public static float getFloat(String prompt) 
	{
		return getNumber(prompt).floatValue();
	}
	
	public static int getInt(String prompt) 
	{
		return getNumber(prompt).intValue(); 	
	}
}
