package numbers;

/** 
 * Show uses of my Complex Number class;
 * see ComplexTest for actual unit tests.
 */
// tag::main[]
import com.darwinsys.numbers.Complex;

public class ComplexDemoIan {
	/** The program */
	public static void main(String[] args) {
		// tag::main[]
		Complex c = new Complex(3,  5);
		Complex d = new Complex(2, -2);
		System.out.println("Initial values: c = " + c + ", d = " + d);
		System.out.println(c + ".getReal() = " + c.getReal());
		System.out.println(c + " + " + d + " = " + c.add(d));
		System.out.println(c + " + " + d + " = " + Complex.add(c, d));
		System.out.println(c + " * " + d + " = " + c.multiply(d));
		System.out.println(c + " / " + d + " = " + Complex.divide(c, d));
		// end::main[]
	}
}
// end::main[]
