import java.util.*;

/**
 * Simple StringTokenizer demo program.
 */ 
public class StrTokDemo3 {
	public static void main(String[] argv) {
		StringTokenizer st = 
			new StringTokenizer("Hello, World|of|Java", ", |", true);

		while (st.hasMoreElements())
			System.out.println("Token: " + st.nextElement());
	}
}
