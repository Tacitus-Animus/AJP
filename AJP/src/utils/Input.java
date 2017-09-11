package utils;

import java.math.BigDecimal;
import java.util.Scanner;

/**
* <h1>Input Class</h1>
* The Input program implements a Scanner Object to
* retrieve, filter, and parse user input. 
* This makes it easy for the Client to get desired/proper
* input from the user.
* <p>
* <code>private static Scanner input = new Scanner(System.in);</code>
* <p> 
* Note: Most methods here contain a loop in which user won't
* be able to break out of until user satisfies method conditions.
* <p>
* <code>float number = Input.getFloat("Please Enter a a floating point value: ");</code>
* <p>
* Note: All methods need a prompt specified by Client that will be displayed to the user.
* @see Scanner
* 
* @author  Alexander J Paul
* @version 2.0
* @since   2017-SEP-11 
*/

public class Input 
{
	/**
	 * Scanner Object used to get basic input from user.
	 */
	private static Scanner input = new Scanner(System.in);

	/**
	 * <h2>getStringln</h2>
	 * This method is used to get a line of String that isn't empty else prints out
	 * input error. 
	 * @param prompt This is what will be displayed to the user prior to input
	 * <p> Note: prompt is printed out by System.out.print().
	 * @return String that isn't empty and doesn't contain whitespace on the front and back ends of it.
	 */
	public static String getStringln(String prompt) 
	{
		String storedString;
		
		while(true) 
		{
			System.out.print(prompt);
			storedString = input.nextLine().trim();
			if(storedString.isEmpty()) System.out.println("Invalid Input: You didn't enter anything.");
			else break;
		}
		return storedString;
	}

	/**
	 * <h2>getString</h2>
	 * This method makes use of method getStringln() to return a line of String from the user.
	 * Prints input error if it contains whitespace between non-whitespace characters.
	 * This makes it possible to only get a word/phrase from the user.
	 * @return String that doesn't contain whitespace between characters;
	 */
	public static String getString(String prompt) 
	{
		String storedString;
		
		while(true) 
		{	
			storedString = getStringln(prompt);
			if(storedString.contains(" ")) System.out.println("Invalid Input: You entered too many variables.");
			else break; 
		}
		return storedString;
	}
	
	/**
	 * <h2>getChar</h2>
	 * This method makes use of Method getString() to return a String from the user.
	 * Prints input error if the String length is greater than 1.
	 * This makes it possible to only get one char from the user.
	 * @return char stored at index 0 if String.
	 * @see Character
	 */
	public static char getChar(String prompt) 
	{
		String storedString;
		
		while(true) 
		{	
			storedString = getString(prompt);
			if(storedString.length() > 1) System.out.println("Invalid Input: Too long.");
			else break;
		}
		return storedString.charAt(0);
	}
	
	/**
	 * <h2>getLetter</h2>
	 * This method makes use of the method getChar() to return a char from user.
	 * Prints input error if char isn't a letter.
	 * @return char that is a letter only. (a-z,A-Z)
	 * @see Character
	 */
	public static char getLetter(String prompt) 
	{
		char storedChar;
		
		while(true) 
		{
			storedChar = getChar(prompt);
			if(!Character.isLetter(storedChar)) System.out.println("Invalid Input: Not a Letter.");
			else break;
		}
		return storedChar;
	}
	
	/**
	 * <h2>getDigit</h2>
	 * This method makes use of method getChar() to return char from user.
	 * Prints input error if char isn't a digit.
	 * @return char that is a digit only. (0-9)
	 * @see Character
	 */
	public static char getDigit(String prompt) 
	{
		char storedChar;
		
		while(true) 
		{
			storedChar = getChar(prompt);
			if(!Character.isDigit(storedChar)) System.out.println("Invalid Input: Not a Digit.");
			else break;
		}
		return storedChar;
	}
	
	/**
	 * <h2>getNumber</h2>
	 * Makes use of method getString() to return String from user.
	 * Prints input error if String can't be parsed into BigDecimal.
	 * <p> Note: BigDecimal seems to have to most precision getting a number value from the user.
	 * This makes it possible to get any other number type without losing precision. 
	 * @return Number that is a BigDecimal.
	 * @see Number
	 * @see BigDecimal
	 * @see NumberFormatException
	 */
	public static Number getNumber(String prompt)
	{
		while(true) 
		{
			try{ 
				return new BigDecimal(getString(prompt)); 
			}catch(NumberFormatException E){ 
				System.out.println("Invalid Input: Not a number."); 
			}
		}	
	}
	
	/** 
	 * <h2>getDouble</h2>
	 * Makes use of method getNumber() to return Number from user.
	 * @return double value of Number.
	 * @see Number
	 */
	public static double getDouble(String prompt) 
	{
		return getNumber(prompt).doubleValue();
	}
	
	/** 
	 * <h2>getFloat</h2>
	 * Makes use of method getNumber() to return Number from user.
	 * @return float value of Number.
	 * @see Number
	 */
	public static float getFloat(String prompt) 
	{
		return getNumber(prompt).floatValue();
	}
	
	/** 
	 * <h2>getInt</h2>
	 * Makes use of method getNumber() to return Number from user.
	 * @return int value of Number.
	 * @see Number
	 */
	public static int getInt(String prompt) 
	{
		return getNumber(prompt).intValue(); 	
	}
}
