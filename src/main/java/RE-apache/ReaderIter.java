package RE-apache;

import java.io.*;
import org.apache.regexp.*;
import com.darwinsys.util.Debug;

/** Demonstrate the CharacterIterator interface: print
 * all the strings that match a given pattern from a file.
 */
public class ReaderIter {
	public static void main(String[] args) throws Exception {
		// The RE pattern
		RE patt = new RE("[A-Za-z][a-z]+");
		// A FileReader (see the I/O chapter)
		Reader r = new FileReader(args[0]);
		// The RE package ReaderCharacterIterator, a "front end"
		// around the Reader object.
		CharacterIterator in = new ReaderCharacterIterator(r);
		int end = 0;

		// For each match in the input, extract and print it.
		while (patt.match(in, end)) {
			// Get the starting position of the text
			int start = patt.getParenStart(0);
			// Get ending position; also updates for NEXT match.
			end = patt.getParenEnd(0);
			// Print whatever matched.
			Debug.println("match", "start=" + start + "; end=" + end);
			// Use CharacterIterator.substring(offset, end);
			System.out.println(in.substring(start, end));
		}
	}
}
