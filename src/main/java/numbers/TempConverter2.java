import java.text.*;
/* Print a table of fahrenheit and celsius temperatures, a bit more neatly.
 * @author Ian F. Darwin, ian@darwinsys.com
 * @version $Id$
 */
public class TempConverter2 extends TempConverter {
	protected DecimalFormat df;

	// Constructor
	public TempConverter2 {
		df = new DecimalFormat("##.###");
	}

	protected void print(float f, float c) {
		System.out.println(i + " f = " + df.format(c) + " c.");
	}

	protec