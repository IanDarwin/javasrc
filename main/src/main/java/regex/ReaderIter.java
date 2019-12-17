package regex;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Print all the strings that match a given pattern from a file.
 */
// tag::main[]
public class ReaderIter {
	public static void main(String[] args) throws IOException {
		// The RE pattern
		Pattern patt = Pattern.compile("[A-Za-z][a-z]+");
		// See the I/O chapter
		// For each line of input, try matching in it.
		Files.lines(Path.of(args[0])).forEach(line -> {
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
		});
	}
}
// end::main[]
