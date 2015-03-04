package regex;

import java.util.regex.*;
import java.io.*;

/**
 * Print all the strings that match a given pattern from a file.
 */
// BEGIN main
public class ReaderIter {
	public static void main(String[] args) throws IOException {
		// The RE pattern
		Pattern patt = Pattern.compile("[A-Za-z][a-z]+");
		// A FileReader (see the I/O chapter)
		BufferedReader r = new BufferedReader(new FileReader(args[0]));

		// For each line of input, try matching in it.
		String line;
		while ((line = r.readLine()) != null) {
			// For each match in the line, extract and print it.
			Matcher m = patt.matcher(line);
			while (m.find()) {
				// Simplest method:
				// System.out.println(m.group(0));

				// Get the starting position of the text
				int start = m.start(0);
				// Get ending position
				int end = m.end(0);
				// Print whatever matched.
				// Use CharacterIterator.substring(offset, end);
				System.out.println(line.substring(start, end));
			}
		}
		r.close();
	}
}
// END main
