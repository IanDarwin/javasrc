package textproc;

import java.io.*;
import java.nio.file.*;

/**
 * Find perfect pangrams of the given length.
 * A pangram is a word that uses all the letters of a given alphabet,
 * a term oft used in puzzles like Spelling Bee to refer to a word
 * that uses all (7) given letters.
 * A perfect pangram is a pangram that uses each letter exactly once.
 */
public class PerfectPangram {
	final static int DEFAULT_LENGTH = 7;
	private static int len = DEFAULT_LENGTH;

	public PerfectPangram(int len) {
		this.len = len;
	}
	public PerfectPangram() {
		// empty
	}

	/** Default main: print picked perfect pangrams purloined from /usr/dict/words */
	public static void main(String[] args) throws IOException {
		PerfectPangram program = new PerfectPangram(
			args.length == 0 ? DEFAULT_LENGTH : Integer.parseInt(args[0]));
		Files.lines(Path.of("/usr/share/dict/words"))
			.map(String::toLowerCase)
			.filter(program::pangram)
			.forEach(System.out::println);
	}

	public boolean pangram(String cand) {
		if (cand.length() != len)
			return false;
		var found = new boolean[26];
		int count = 0;
		for (char c : cand.toCharArray()) {
			if (found[c-'a'])
				// no dupes in perfect pangrams
				return false;
			found[c-'a'] = true;
			count++;
		}
		return count == len;	// all used?
	}
}
