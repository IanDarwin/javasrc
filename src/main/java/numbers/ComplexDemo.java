/** A class to test Complex Numbers. 
 * @author Ian F. Darwin, ian@darwinsys.com
 * @version $Id$
 */
public class ComplexDemo {
	/** The program */
	public static void main(String args[]) {
		Complex c = new Complex(3,  5);
		Complex d = new Complex(2, -2);
		System.out.println(c + ".getReal() = " + c.getReal());
		System.out.println(c + " + " + d + " = " + c.add(d));
		System.out.println(c + " + " + d + " = " + Complex.add(c, d));
		System.out.println(c + " * " + d + " = " + c.multiply(d));
	}
}
