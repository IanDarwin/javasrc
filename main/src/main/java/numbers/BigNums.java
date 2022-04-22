package numbers;

import java.math.BigInteger;

/**
 * Demonstrate large numbers.
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
public class BigNums {
	public static void main(String[] argv) {
		// tag::main[]
		System.out.println("Here's Long.MAX_VALUE: " + Long.MAX_VALUE);
		BigInteger bInt = new BigInteger("3419229223372036854775807");
		System.out.println("Here's a bigger number: " + bInt);
		System.out.println("Here it is as a double: " + bInt.doubleValue());
		// end::main[]
	}
}
