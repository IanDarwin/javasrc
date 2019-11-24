package functional;

import java.util.function.IntUnaryOperator;

/**
 * A very silly demo of using a Functional Interface
 * @author Ian Darwin
 */
public class ProcessIntsIntUnaryOperator {
	static int[] integers = { 0, 1, 2, 3, 4, 5 };
	
	/** Function to be called with an int and a Function;
	 * just apply the function to the int and return the result
	 */
	static int doTheMath(int n, IntUnaryOperator func) {
		return func.applyAsInt(n);
	}
	
	public static void main(String[] args) {

		int total = 0;
		for (int i : integers) {
			// Call doTheMath with 'i' and a Lambda for n^2 +1
			total += doTheMath(i, k -> k * k + 1 );
		}
		System.out.println(total);
	}
}
