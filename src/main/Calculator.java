package main;

import java.util.ArrayList;
import java.util.Scanner;

public class Calculator {
	
	public static boolean isNegative = false;
	public static boolean needSwap = false;
	public static void main(String[] args) {
		
		ArrayList<String> components = parseExpr(getInput());
		String answer = "";
		
		// iterate & calculate the expression
		for (int i = 1; i < components.size() - 1; i += 2) {
			//System.out.println(components);
			isNegative = needSwap = false;
			
			char operation = components.get(i).charAt(0);
			String numA = components.get(i - 1);
			String numB = components.get(i + 1);
			
			/* Handle the expression with negative operands & decide action */
			operation = chooseAction(numA, numB, operation);
			if (needSwap) numA = swap(numB, numB = numA);
			
			IOperation brain;
			switch (operation) {
				case '+':
					// get the numeric parts of numA and numB
					if (numA.indexOf("-") >= 0) numA = numA.substring(1);
					if (numB.indexOf("-") >= 0) numB = numB.substring(1);
					
					brain = new Addition();
					String sum = brain.perform(numA, numB);
					answer = changeSign(sum);

					// set the previous result back to the components list
					components.set(i + 1, sum);
					break;
				case '-':
					// get the numeric parts of numA and numB
					if (numA.indexOf("-") >= 0) numA = numA.substring(1);
					if (numB.indexOf("-") >= 0) numB = numB.substring(1);
					
					brain = new Subtraction();
					String diff = brain.perform(numA, numB);
					answer = changeSign(diff);
		
					// set the previous result back to the components list
					components.set(i + 1, diff);
					break;
				case '>':
				case '<':
				case '=':
					brain = new Comparsion(operation);
					answer = brain.perform(numA, numB);
					break;
				default:
					System.out.println("Unsupport opeation");
					i = components.size(); // force to break for-loop
				break;
			}
		}
		System.out.println(answer);
		
	}
	
	public static char chooseAction(String numA, String numB, char oper) {
		boolean signOfnumA = numA.indexOf("-") < 0 ? true : false;
		boolean signOfnumB = numB.indexOf("-") < 0 ? true : false;
		switch (oper) {
		case '+':
			if (signOfnumA == signOfnumB) {
				// -123 + -456 --> -(123 + 456)
				// addition of two negative numbers, set the ans to negative if needed
				if (!signOfnumA) isNegative = true;
				return oper;
			} else {
				// 123 + -456 --> 123 - 456
				// -123 + 456 --> 456 - 123
				// two different sign numbers' addition equals to subtraction after swapping
				if (!signOfnumA) needSwap = true;
				return '-';
			}
		case '-':
			if (signOfnumA == signOfnumB) {
				// -123 - -456 --> 456 - 123
				// two negative numbers subtraction equals to subtraction after swapping
				if (!signOfnumA) needSwap = true;
				return oper;
			} else {
				// 123 - -456 --> 123 + 456
				// -123 - 456 --> -(123 + 456)
				// subtraction of two different-sign numbers, set the ans to negative if needed
				if (!signOfnumA) isNegative = true;
				return '+';
			}
		default:
			return oper;
		}
	}
	
	public static String changeSign(String ans) {
		return isNegative ? "-" + ans : ans;
	}
	
	/* Read experssion from stdin */
	public static String getInput() {
		Scanner scn = new Scanner(System.in);
		String expr = scn.nextLine();
		scn.close();
		return expr;
	}

	/* Split by whitespace */
	public static ArrayList<String> parseExpr(String expr) {
		String[] parts = expr.split(" ");
		ArrayList<String> result = new ArrayList<String>();
		
		for (int i = 0; i < parts.length; i++)
			result.add(parts[i]);

		return result;
	}
	
	public static String swap(String x, String y) {
		return x;
	}
}
