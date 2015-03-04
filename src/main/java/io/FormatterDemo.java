package io;

import java.util.Formatter;

/** Demonstrate some usage patterns and format-code examples 
 * of the Formatter class and PrintStream/PrintWriter 
 * printf() methods introduced way back in Java 5.
 */
// BEGIN main
public class FormatterDemo {
	public static void main(String[] args) {

		// The arguments to all these format methods consist of
		// a format code String and 1 or more arguments.
		// Each format code consists of the following:
		// % - code lead-in
		// N$ - which parameter number (1-based) after the code - OPTIONAL
		// N - field width
		// L - format letter (d: decimal(int); f: float; s: general; many more)
		// For the full(!) story, see javadoc for java.util.Formatter.

		// Most general (cumbersome) way of proceding.
		Formatter fmtr = new Formatter();
		Object result = fmtr.format("%1$04d - the year of %2$f", 1956, Math.PI);
		System.out.println(result);

		// Shorter way using static String.format(), and
		// default parameter numbering.
		Object stringResult = String.format("%04d - the year of %f", 1956, Math.PI);
		System.out.println(stringResult);

		// A shorter way using PrintStream/PrintWriter.format, more in line with
		// other languages. But this way you must provide the newline delimiter 
		// using %n (do NOT use \n as that is platform-dependent!).
		System.out.printf("%04d - the year of %f%n", 1956, Math.PI);

		// Format doubles with more control
		System.out.printf("PI is approximately %4.2f%n", Math.PI);
	
		fmtr.close();
	}
}
// END main
