import java.io.*;

/** Print to the standard output */
public class PrintStandardOutput {

	public static void main(String[] args) {
		// Just a String to include in printouts.
		String myAnswer = "No, and that's final,";

		// Print to standard output
		System.out.println("Hello World of Java");
		// Print several things concatendated.
		System.out.println("The answer is " + myAnswer + " at this time."); 

		// Print to standard output using a Writer
		PrintWriter pw = new PrintWriter(System.out);
		pw.println("The answer is " + myAnswer + " at this time."); 

		// Caveat printing ints and chars together
		int i = 42;
		pw.println(i + '=' + " the answer.");	// WRONG
		pw.println("Note: " + i + '=' + " the answer.");	// OK

		// Some of the workarounds for the caveat above:
		pw.println(i + "=" + " the answer.");	// using quotes
		pw.println(i + ('=' + " the answer."));	// parenthesis

		pw.close();		// If you open it, you close it.
	}
}
