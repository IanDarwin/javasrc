package strings;

import java.util.StringTokenizer;

/**
 * Simple StringTokenizer demo program.
 */
public class StrTokDemo {
	public static void main(String[] argv) {
		StringTokenizer st = new StringTokenizer("Hello World of Java");

		while (st.hasMoreTokens())
			System.out.println("Token: " + st.nextToken());
	}
}
