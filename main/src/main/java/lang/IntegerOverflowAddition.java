package lang;

/**
 * What happens whe we add two reasonable-looking ints?
 */
public class IntegerOverflowAddition {
	public static void main(String[] args) {
		int i1 = 2000000000; 
		int i2 = 1000000000; 
		int sum = i1 + i2;
		System.out.printf("%d + %d = %d - really?%n", i1, i2, sum);
	}
}
