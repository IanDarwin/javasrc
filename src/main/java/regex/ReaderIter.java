import java.util.regex.*;
import java.io.*;

/**
 * Print all the strings that match a given pattern from a file.
 */
public class ReaderIter {
	public static void main(String[] args) throws IOException {
		// The RE pattern
		Pattern patt = Pattern.compile("[A-Za-z][a-z]+");
		// A FileReader (see the I/O chapter)
		Reader r = new FileReader(args[0]);

		// For each match in the input, extract and print it.
		while ((line = r.readLine()) != null) {
			Match m = patt.matcher(line);
			// Get the starting position of the text
			int start = patt.getParenStart(0);
			// Get ending position; also updates for NEXT match.
			end = patt.getParenEnd(0);
			// Print whatever matched.
			System.out.println("start=" + start + "; end=" + end);
			// Use CharacterIterator.substring(offset, end);
			System.out.println(in.substring(start, end));
		}
	}
}
