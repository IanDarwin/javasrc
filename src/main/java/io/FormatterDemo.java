import java.util.Formatter;

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
		Object result = fmtr.format("%1$04d - the year of %2$f", 1951, Math.PI);
		System.out.println(result);

		// A shorter way of doing things. But this
		// way you must provide the newline delimiter
		System.out.format("%1$04d - the year of %2$f%n", 1951, Math.PI);

		// So is this
		System.out.printf("%1$04d - the year of %2$f%n", 1951, Math.PI);

		// Format doubles with more control
		System.out.printf("PI is about %1$4.2f", Math.PI);
	}
}
