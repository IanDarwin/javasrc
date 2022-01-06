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
	private boolean perfect = true;
	final char[] alphabet;
	private final int len;

	public PerfectPangram(String alphabet) {
		this.alphabet = alphabet.toCharArray();
		this.len = alphabet.length();
	}

	/** Default main: print picked (perfect?) pangrams purloined from /usr/dict/words */
	public static void main(String[] args) throws IOException {
		if (args.length == 0) {
			// Maybe default to "a..z"?
			System.out.println("Usage: pangram [-p] alphabet");
			System.exit(1);
		}
		PerfectPangram program = new PerfectPangram(args[0]);
		Files.lines(Path.of("/usr/share/dict/words"))
			.map(String::toLowerCase)
			.filter(program::pangram)
			.forEach(System.out::println);
	}

	public boolean pangram(String cand) {
		if (perfect && cand.length() != len)
			return false;
		var found = new boolean[26];
		int matchedCount = 0;
		for (char c : cand.toCharArray()) {
			if (!inDict(c))
				return false;
			if (perfect && found[c-'a'])
				// no dupes in perfect pangrams
				return false;
			found[c-'a'] = true;
			matchedCount++;
		}
		if (perfect)
			return matchedCount == len;	// all used?
		int used = 0;
		for (int i = 0; i < found.length; i++)
			if (found[i])
				++used;
		return matchedCount == len;
	}

	private boolean inDict(char c) {
		for (char a : alphabet) {
			if (a == c)
				return true;
		}
		return false;
	}
}
