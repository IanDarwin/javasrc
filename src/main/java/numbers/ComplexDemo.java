package numbers;

/** A class to show uses of my Complex Number class;
 * see ComplexTest for actual unit tests.
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
// BEGIN main
public class ComplexDemo {
	/** The program */
	public static void main(String[] args) {
		Complex c = new Complex(3,  5);
		Complex d = new Complex(2, -2);
		System.out.println(c);
		System.out.println(c + ".getReal() = " + c.getReal());
		System.out.println(c + " + " + d + " = " + c.add(d));
		System.out.println(c + " + " + d + " = " + Complex.add(c, d));
		System.out.println(c + " * " + d + " = " + c.multiply(d));
		System.out.println(Complex.divide(c, d));
	}
}
// END main
