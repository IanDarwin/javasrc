package numbers;

/**
 * Show the logarithm to base e of a number
 * @author Ian F. Darwin, https://darwinsys.com/
 */
public class Logarithm {
	public static void main(String[] argv) {
		// tag::main[]
		double someValue;

		// compute someValue...
		someValue = 0;

		double log_e = Math.log(someValue);
		System.out.println("Log(e) of " + someValue + " is " + log_e);
		// end::main[]
	}
}
