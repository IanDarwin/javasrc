import java.text.*;

/* Print a table of fahrenheit and celsius temperatures, a bit more neatly.
 * @author Ian F. Darwin, ian@darwinsys.com
 * @version $Id$
 */
public class TempConverter2 extends TempConverter {
	protected DecimalFormat df;

	// We don't need a main program; it's inherited from TempConverter.

	// Constructor
	public TempConverter2() {
		df = new DecimalFormat("##.###");
	}

	protected void print(float f, float c) {
		System.out.println(f + " f = " + df.format(c) + " c.");
	}

	protected void start() {
		System.out.println("Fahr    Centigrade.");
	}

	protected void end() {
		System.out.println("-------------------");
	}
}
