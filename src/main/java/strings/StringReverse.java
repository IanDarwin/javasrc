import java.util.*;

/**
 * Show String Reversals
 * @version $Id$
 */
public class StringReverse {
	public static void main(String argv[]) {
		String s = "Hello. I like Java";
		System.out.println('"' + s + '"' + " backwards by character is " + 
			'"' + new StringBuffer(s).reverse() + '"');

		// Put it in the stack frontwards
		Stack stk = new Stack();
		StringTokenizer st = new StringTokenizer(s);
		while (st.hasMoreTokens()) stk.push(st.nextElement());

		// Print the stack backwards
		System.out.print('"' + s + '"' + " backwards by word is: \"");
		while (!stk.empty()) { 
			System.out.print(stk.pop());
			System.out.print(' ');
		}
		System.out.println('"');
	}
}
