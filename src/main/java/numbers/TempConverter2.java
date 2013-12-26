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

	protected void print(float f, float c) {
		System.out.printf("%6.2f %6.2f%n", f, c);
	}

	protected void start() {
		System.out.println("Fahr    Centigrade");
	}

	protected void end() {
		System.out.println("-------------------");
	}
}
// END main
