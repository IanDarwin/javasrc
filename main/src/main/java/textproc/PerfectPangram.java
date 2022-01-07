package textproc;

import java.io.*;
import java.nio.file.*;
import java.util.Arrays;

/**
 * Find perfect pangrams of the given length.
 * A pangram is a word that uses all the letters of a given alphabet,
 * a term oft used in puzzles like Spelling Bee to refer to a word
 * that uses all letters in the given alphabet.
 * A perfect pangram is a pangram that uses each letter exactly once.
 */
public class PerfectPangram {
	private final boolean DEBUG = false;
	public static final String DEF_ALPHABET = "abcdefghijklmnopqrstuvwxyz";
	public static final boolean DEFAULT_SEEK_PERFECTION = false;
	private boolean perfect = false;
	final char[] alphabet;
	private final int alphabetLen;

	public PerfectPangram(String alphabet, boolean perfect) {
		this.alphabet = alphabet.toCharArray();
		this.alphabetLen = alphabet.length();
		this.perfect = perfect;
	}

	public PerfectPangram(String alphabet) {
		this(alphabet, DEFAULT_SEEK_PERFECTION);
	}

	public PerfectPangram(boolean perfect) {
		this(DEF_ALPHABET, perfect);
	}

	public PerfectPangram() {
		this(DEF_ALPHABET, DEFAULT_SEEK_PERFECTION);
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
		if (perfect && cand.length() != alphabetLen)
			return false;
		var found = new boolean[26];
		int matchedCount = 0;
		for (char c : cand.toCharArray()) {
			if (!inDict(c))
				return false;
			if (perfect && found[c-'a']) {
				// no dupes in perfect pangrams
				return false;
			}
			if (!found[c-'a'])
				matchedCount++;
			found[c-'a'] = true;
		}
		if (perfect)
			return matchedCount == alphabetLen;	// all used?
		if (DEBUG) {
			System.out.printf("Input %s, alphabet %s, matchedCount %d\n",
					cand, new String(alphabet), matchedCount);
			for (char t : alphabet) {
				System.out.print(t + ": " + (found[t - 'a'] ? 'T' : 'F') + ' ');
			}
			System.out.println();
		}
		return matchedCount == alphabetLen;
	}

	public boolean pangramSentence(String cand) {
		return pangram(cand.toLowerCase().replaceAll(" ", ""));
	}

	private boolean inDict(char c) {
		for (char a : alphabet) {
			if (a == c)
				return true;
		}
		return false;
	}
}
