package main;

import java.util.ArrayList;

public class Addition implements IOperation {
	
	public String perform(String num1, String num2) {
		
		// make sure num1 isn't shorter than num2 
		if (num1.length() < num2.length()) 
			num1 = swap(num2, num2 = num1);
		
		ArrayList<Integer> sum = new ArrayList<Integer>();
		int carry = 0, k = 0, remainIndex = 0;
		
		/* Perform the addition from the end */
		for (int i = num1.length() - 1, j = num2.length() - 1;
				 i >= 0 && j >= 0; i--, j--, remainIndex = i) {
			int numA = num1.charAt(i) - '0';
			int numB = num2.charAt(j) - '0';
			
			sum.add(k++, (numA + numB + carry) % 10);
			carry = (numA + numB + carry) / 10;
		}
		
		if (num1.length() == num2.length()) {
			if (carry > 0) sum.add(k++, carry);
		} else {
			// handle remained digits
			while (remainIndex >= 0) {
				int remainNum = num1.charAt(remainIndex) - '0';
				sum.add(k++, (remainNum + carry) % 10);
				
				remainNum = num1.charAt(remainIndex--) - '0';
				carry = (remainNum + carry) / 10;
			}
			
			// check whether carry remains
			if (carry > 0) sum.add(k++, carry);
		}
		
		String ans = "";
		for (int i = sum.size() - 1; i >= 0; i--)
			ans += sum.get(i);
		return ans;
	}
	
	public String swap(String x, String y) {
		return x;
	}
}
