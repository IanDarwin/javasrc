package strings;

/** 
 * Given the problem of "I have a String with a fixed number of
 * Integers in it, how do I extract them?". Here's a good
 * readable solution.
 */
public class StringSplitInts {
	public static void main(String[] args) {
		String in = "1  4   5   6   7  908  1231  23  53a";
		for (String next : in.split("\\s")) {
			try {
				int n = Integer.parseInt(next);
				System.out.println(next + " --> " + n);
			} catch (NumberFormatException ex) {
				System.out.println(next + " NOT A NUMBER (" + next + ")");
			}
		}
	}
}
