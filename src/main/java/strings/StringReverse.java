import java.util.*;

/**
 * Show String Reversals
 * @version $Id$
 */
public class StringReverse {
	public static void main(String argv[]) {
		//+
		String s = "Father Charles Goes Down And Ends Battle";

		// Put it in the stack frontwards
		Stack stk = new Stack();
		StringTokenizer st = new StringTokenizer(s);
		while (st.hasMoreTokens()) stk.push(st.nextElement());

		// Print the stack backwards
		System.out.print('"' + s + '"' + " backwards by word is:\n\t\"");
		while (!stk.empty()) { 
			System.out.print(stk.pop());
			System.out.print(' ');
		}
		System.out.println('"');
		//-
	}
}
