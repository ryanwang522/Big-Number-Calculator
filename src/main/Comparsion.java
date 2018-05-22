package main;

public class Comparsion implements IOperation {
	
	public char operation;
	
	public Comparsion(char oper) {
		this.operation = oper;
	}
	
	public boolean hasSameSign(String num1, String num2) {
		boolean signOfnum1 = num1.indexOf("-") < 0 ? true : false;
		boolean signOfnum2 = num2.indexOf("-") < 0 ? true : false;
		return signOfnum1 == signOfnum2 ? true : false;
	}
	
	/* Check if num1 < num2, both number can be either negative or positive */
	public boolean isSmaller(String num1, String num2) {
		if (this.hasSameSign(num1, num2)) {
			// ++ or --
			if (num1.indexOf("-") >= 0) {
				/* Both numbers are negative, change both sign of inequation then check again.
				 * Check equivalence first,
				 * then reverse the expression and check again.
				 * e.g. -123 < -456  <-->  123 > 456 */
				if (num1.equals(num2)) return false;
				else return isSmaller(num2.substring(1), num1.substring(1));
			}
			
			if (num1.length() < num2.length()) return true;
			if (num1.length() > num2.length()) return false;
			
			for (int i = 0; i < num1.length(); i++) {
				if (num1.charAt(i) > num2.charAt(i)) return false;
				else if (num1.charAt(i) < num2.charAt(i)) return true;
			}
	
			return false;
		} else {
			// different sign
			boolean signOfnum1 = num1.indexOf("-") < 0 ? true : false;

			/* Positive num is not smaller than negative num, return false.
			 * Negative num is smaller than positive num, return true. */
			return signOfnum1 ? false : true;
		}
	}
	
	public String perform(String num1, String num2) {
		switch (this.operation) {
			case '<':
				return Boolean.toString(this.isSmaller(num1, num2));
			case '>':
				return Boolean.toString(this.isSmaller(num2, num1));
			default:
				return Boolean.toString(num1.equals(num2));
		} 
	}
	
	public String swap(String x, String y) {
		return x;
	}
}
