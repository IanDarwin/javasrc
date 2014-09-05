package strings;

import java.util.StringTokenizer;

/** 
 * Given the problem of "I have a String with a fixed number of
 * Integers in it, how do I extract them?". Here's one solution.
 * Note that StringTokenizer is "discouraged" in new code, but not yet deprecated.
 * See StringSplitInts for a better example!
 */
public class StrTokInts {
	public static void main(String[] args) {
		String in = "1  4   5   6   7  908  1231  23  53a";
		StringTokenizer st = new StringTokenizer(in);
		int iKnowHowMany = 9;
		for (int i=0; i<iKnowHowMany; i++) {
			String next = st.nextToken();
			try {
				int n = Integer.parseInt(next);
				System.out.println(i + " --> " + n);
			} catch (NumberFormatException ex) {
				System.out.println(i + " NOT A NUMBER (" + next + ")");
			}
		}
	}
}
