import java.util.*;

/** Show using a StringTokenizer including getting the delimiters back */
public class StrTokDemo4 {
	public final static int MAXFIELDS = 5;

	/** Processes one String, returns it as an array of fields */
	public static String[] process(String line) {
		String[] results = new String[MAXFIELDS];

		// Unless you ask StringTokenizer to give you the tokens,
		// it silently discards multiple null tokens.
		StringTokenizer st = new StringTokenizer(line, "|", true);

		int i = 0;
		// stuff each token into the current user
		while (st.hasMoreTokens()) {
			String s = st.nextToken();
			if (s.equals("|")) {
				if (i++>=MAXFIELDS)
					throw new IllegalArgumentException("Input line " +
						line + " has too many fields");
				continue;
			}
			results[i] = s;
		}
		return results;
	}

	public static void print(String input, String[] outputs) {
		System.out.println("Input: " + input);
		for (int i=0; i<outputs.length; i++)
			System.out.println("Output " + i + " was: " + outputs[i]);
	}

	public static void main(String a[]) {
		print("A|B|C|D", process("A|B|C|D"));
		print("A||C|D", process("A||C|D"));
		print("A|||D|E", process("A|||D|E"));
	}
}
