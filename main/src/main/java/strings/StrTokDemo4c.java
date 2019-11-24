package strings;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/** Show using a StringTokenizer including getting the delimiters back
 * StringTokenizer is "discouraged" in new code, but not yet deprecated.
 * This final version returns a List instead of converting to an array.
 */
public class StrTokDemo4c {
	/** The delimiter used between fields on input. */
	public final static String DELIM = "|";

	/** Processes one String, returns it as an array of fields */
	public static List<String> process(String line) {
		// A ArrayList is an array-like container that grows dynamically;
		// see the chapter on Java Collections.
		ArrayList<String> results = new ArrayList<String>();

		// Unless you ask StringTokenizer to give you the tokens,
		// it silently discards multiple null tokens.
		StringTokenizer st = new StringTokenizer(line, DELIM, true);

		int i = 0;
		// Stuff each token into the Vector
		while (st.hasMoreTokens()) {
			String s = st.nextToken();
			if (s.equals(DELIM)) {
				// Just ignore the "tokens" that consist of DELIM
				// But leave a blank entry corresponding to the missing field
				if (results.size() < ++i)
					results.add(null);
				continue;
			}
			results.add(s);
		}
		
		return results;
	}

	public static void printResults(String input, List<String> outputs) {
		System.out.println("Input: " + input);
		for (int i=0; i < outputs.size(); i++)
			System.out.println("Output " + i + " was: " + outputs.get(i));
	}

	public static void main(String[] a) {
		printResults("A|B|C|D", process("A|B|C|D"));
		printResults("A||C|D", process("A||C|D"));
		printResults("A|||D|E", process("A|||D|E"));
	}
}
