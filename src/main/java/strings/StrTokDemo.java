/**
 * Simple StringTokenizer demo program.
 */

import java.util.*;

public class StrTokDemo {
	public static void main(String argv[]) {
		StringTokenizer st = new StringTokenizer("Hello, World of Java");
		String s;

		while (st.hasMoreTokens())
			System.out.println("Token: " + st.nextToken());
	}
}
