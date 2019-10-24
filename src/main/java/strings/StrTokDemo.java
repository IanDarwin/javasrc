package strings;

import java.util.StringTokenizer;

/**
 * Simple StringTokenizer demo program.
 * StringTokenizer is "discouraged" in new code, but not yet deprecated.
 */
public class StrTokDemo {
	public static void main(String[] argv) {
		StringTokenizer st = new StringTokenizer("Hello World of Java");

		while (st.hasMoreTokens())
			System.out.println("Token: " + st.nextToken());
	}
}
