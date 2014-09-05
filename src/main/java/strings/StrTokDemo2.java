package strings;

import java.util.StringTokenizer;

/**
 * Simple StringTokenizer demo program.
 * StringTokenizer is "discouraged" in new code, but not yet deprecated.
 */ 
public class StrTokDemo2 {
	public static void main(String[] argv) {
		StringTokenizer st = 
			new StringTokenizer("Hello, World|of|Java", ", |");

		while (st.hasMoreElements())
			System.out.println("Token: " + st.nextElement());
	}
}
