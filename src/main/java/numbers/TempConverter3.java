import java.text.*;

/* Print a table of Fahrenheit and Celsius temperatures, with decimal
 * points lined up.
 * @author Ian F. Darwin, http://www.darwinsys.com/
 * @version $Id$
 */
public class TempConverter3 extends TempConverter2 {
	protected FieldPosition fp;
	protected DecimalFormat dff;

	public static void main(String[] args) {
		TempConverter t = new TempConverter3();
		t.start();
		t.data();
		t.end();
	}

	// Constructor
	public TempConverter3() {
		super();
		dff = new DecimalFormat("#0.0");
		fp = new FieldPosition(NumberFormat.INTEGER_FIELD);
	}

	protected void print(float f, float c) {
		String fs = dff.format(f, new StringBuffer(), fp).toString();
		fs = prependSpaces(4 - fp.getEndIndex(), fs);

		String cs = df.format(c, new StringBuffer(), fp).toString();
		cs = prependSpaces(4 - fp.getEndIndex(), cs);

		System.out.println(fs + "  " + cs);
	}

	protected String prependSpaces(int n, String s) {
		String[] res = {
			"", " ", "  ", "   ", "    ", "     "
		};
		if (n<res.length)
			return res[n] + s;
		throw new IllegalStateException("Rebuild with bigger \"res\" array.");
	}
}
