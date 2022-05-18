package textproc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Find perfect pangrams of the given length.
 * A pangram is a word that uses all the letters of a given alphabet,
 * a term oft used in puzzles like Spelling Bee to refer to a word
 * that uses all letters in the given alphabet.
 * A perfect pangram is a pangram that uses each letter exactly once.
 * And yes, this program lets you cheat at Spelling Bee, but where's the challenge in that?
 * So, use it to check your work *after* you finish playing.
 * @author Ian Darwin https://darwinsys.com/java/
 */
public class PerfectPangram {
	private final boolean DEBUG = false;
	public static final String DEF_ALPHABET = "abcdefghijklmnopqrstuvwxyz";
	public static final boolean DEFAULT_SEEK_PERFECTION = false;
	private final boolean perfect;
	final char[] alphabet;
	private final int alphabetLen;

	public PerfectPangram(String alphabet, boolean perfect) {
		// In case input alphabet has repeated letters ("confetti")
		boolean[] tmp = new boolean[26];
		for (char c : alphabet.toLowerCase().toCharArray()) {
			tmp[c - 'a'] = true;
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 26; i++) {
			if (tmp[i]) {
				sb.append((char)(i + 'a'));
			}
		}
		this.alphabet = sb.toString().toCharArray();
		this.alphabetLen = this.alphabet.length;
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
