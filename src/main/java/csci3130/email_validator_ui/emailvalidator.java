/* emailvalidator.java
 * CSCI3130 Assignment 3
 * 
 * By Connor Foran
 * B00649015
 */

package csci3130.email_validator_ui;

import java.util.Scanner;

public class emailvalidator{
	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);
		String input = "";
		int rulesPassed;
		
		System.out.println("--CSCI3130 Assignment 3: Stage 2--");
		System.out.print("Please enter a string to check: ");
		input = scanner.nextLine(); //Get user input
		
		rulesPassed = validate(input); //Call validation function
		
		//Print result
		System.out.println("This string passed "+rulesPassed+" rules.");
		if(rulesPassed == 6)
			System.out.println("This string passes the validation check.");
		else
			System.out.println("This string did not pass the validation check.");
		
		scanner.close();
	}
	
	public static int validate(String input)
	{
		//Rule #1: The string has at least one '.'
		//Rule #2: The string contains one and only one '@'
		//Rule #3: The string can only contain letters, numbers, and these characters: ".@-_"
		//Rule #4: The string cannot contain a '.' adjacent to another '.' Or an '@'
		//Rule #5: The string must start and end with a letter or number
		//Rule #6: The string must contain a '.' at some point after the '@'
		
		boolean dotFound = false;
		boolean atFound = false;
		boolean dotAfterAt = false;
		boolean multipleAt = false;
		boolean validChars = true;
		boolean properStartEnd = true;
		boolean noIllegalAdjacents = true;
		char c, lastChar = '\0';
		int rulesPassed = 0;
		
		for(int i=0; i<input.length(); i++) //Parse the string
		{
			c = input.charAt(i);
			if(validChars && !(c == '.' || c == '-' || c == '@' || c == '_' || (c >= 65 && c <= 90) || (c >= 97 && c <= 122) || (c >= 48 && c <= 57))) //Check for invalid character
				validChars = false;
			if(properStartEnd && (i==0 || i==input.length()-1) && (c == '.' || c == '-' || c == '@' || c == '_')) //Starts or ends with a special character
				properStartEnd = false;
			if(noIllegalAdjacents && ((c == '.' && (lastChar == '.' || lastChar == '@')) || (c == '@' && lastChar == '.'))) //Check for illegal consecutive
				noIllegalAdjacents = false;
			if(c == '.') //Found a '.'
			{
				if(!dotFound)
				{
					dotFound = true;
					rulesPassed++;
				}
				if(atFound && !dotAfterAt) //Found a '.' at some point after an '@'
				{
					dotAfterAt = true;
					rulesPassed++;
				}
			}
			else if(c == '@' && !multipleAt) //Found an '@'
			{
				if(atFound) //If an @ was already found, it does not pass the rule
				{
					rulesPassed--;
					multipleAt = true;
				}
				else
				{
					atFound = true;
					rulesPassed++;
				}
			}
			lastChar = c;
		}
		
		if(validChars)
			rulesPassed++;
		if(properStartEnd)
			rulesPassed++;
		if(noIllegalAdjacents)
			rulesPassed++;
		
		return rulesPassed;
	}
}