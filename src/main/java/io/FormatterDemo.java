import java.util.Formatter;
import java.util.Date;
import java.util.Calendar;

/** Demonstrate some usage patterns and format-code examples 
 * of the Formatter class (new in J2SE 1.5).
 */
public class FormatterDemo {
	public static void main(String[] args) {

		// The arguments to all these format methods consist of
		// a format code String and 1 or more arguments.
		// Each format code consists of the following:
		// % - code lead-in
		// N$ - which parameter number (1-based) after the code
		// N - field width
		// L - format letter (d: decimal(int); f: float; s: general; many more)
		// For the full(!) story, see javadoc for java.util.Formatter.

		Formatter fmtr = new Formatter();
		String result = fmtr.format("%1$04d - the year of %2$f", 1951, Math.PI);
		System.out.println(result);

		// A shorter way of doing things.
		System.out.println(fmtr.format("%1$04d - the year of %2$f", 1951, Math.PI));

		// Even shorter, except that this way you must
		// explicitly add the newline delimiter
		System.out.format(fmtr.format(format("%1$04d - the year of %2$f", 1951, Math.PI));

		// So is this
		System.out.printf(fmtr.format(format("%1$04d - the year of %2$f", 1951, Math.PI));
	}
}
