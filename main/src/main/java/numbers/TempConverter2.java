package numbers;


/* Print a table of fahrenheit and celsius temperatures, a bit more neatly.
 * @author Ian F. Darwin, https://darwinsys.com/
 */
// tag::main[]
public class TempConverter2 extends TempConverter {

	public static final String SEP = "-----------------";

	public static void main(String[] args) {
		TempConverter t = new TempConverter2();
		t.start();
		t.data();
		t.end();
	}

	@Override
	protected void print(double f, double c) {
		System.out.printf("%6.2f\u00B0F %6.2f\u00B0C%n", f, c);
	}

	@Override
	protected void start() {
		System.out.println("Fahr      Celsius");
		System.out.println(SEP);
	}

	@Override
	protected void end() {
		System.out.println(SEP);
	}
}
// end::main[]
