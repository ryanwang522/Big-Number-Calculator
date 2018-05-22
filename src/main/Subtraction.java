package main;

import java.util.ArrayList;

public class Subtraction implements IOperation {
	public String perform(String num1, String num2) {
		
		boolean isNegative = false;
		// make sure num1 > num2
		if (isSmaller(num1, num2)) { 
			// subtract after swapping and add a minus sign to the ans
			num1 = swap(num2, num2 = num1);
			isNegative = true;
		}

		ArrayList<Integer> difference = new ArrayList<Integer>();
		int borrow = 0, k = 0, remainIndex = 0;

		/* Perform the subtraction */
		for (int i = num1.length() - 1, j = num2.length() - 1;
				 i >= 0 && j >= 0; i--, j--, remainIndex = i) {
			int numA = num1.charAt(i) - '0';
			int numB = num2.charAt(j) - '0';
			int diff = (numA - numB - borrow);
			
			if (diff < 0) {
				diff += 10;
				borrow = 1;
			} else 
				borrow = 0;

			difference.add(k++, diff);
		}
		
		/* Handle remained digits */
		while (remainIndex >= 0) {
			int remainNum = num1.charAt(remainIndex--) - '0';
			int diff = remainNum - borrow;
			if (diff < 0) {
				diff += 10;
				borrow = 1;
			} else 
				borrow = 0;
			difference.add(k++, diff);
		}

		String ans = isNegative ? "-" : "";
		for (int i = difference.size() - 1; i >= 0; i--)
			ans += difference.get(i);

		// eliminate leading zeros 
		return removeLeadingZeros(ans, isNegative);
	}

	public boolean isSmaller(String num1, String num2) {
		if (num1.length() < num2.length()) return true;
		if (num1.length() > num2.length()) return false;

		for (int i = 0; i < num1.length(); i++) {
			if (num1.charAt(i) > num2.charAt(i)) return false;
			else if (num1.charAt(i) < num2.charAt(i)) return true;
		}

		return false;
	}

	public String removeLeadingZeros(String ans, boolean isNegative) {
		if (isNegative) 
			while (ans.indexOf("0") == 1) ans = "-" + ans.substring(2);
		else 
			while (ans.indexOf("0") == 0) ans = ans.substring(1);
		
		// Prevent from returning empty string when answer = 0000
		return ans.equals("") ? "0" : ans;
	}
	
	public String swap(String x, String y) {
		return x;
	}
}
