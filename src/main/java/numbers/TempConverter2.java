package numbers;


/* Print a table of fahrenheit and celsius temperatures, a bit more neatly.
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
// BEGIN main
public class TempConverter2 extends TempConverter {

	public static void main(String[] args) {
		TempConverter t = new TempConverter2();
		t.start();
		t.data();
		t.end();
	}

	// This is not an @Override method, so it is never called!
	protected void print(float f, float c) {
		System.out.printf("%6.2f %6.2f%n", f, c);
		// This is to prove that it's never called!
		throw new IllegalStateException("Called non-override method");
	}

	@Override
	protected void start() {
		System.out.println("Fahr    Centigrade");
	}

	@Override
	protected void end() {
		System.out.println("-------------------");
	}
}
// END main
