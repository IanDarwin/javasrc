import java.util.*;

/**
 * Show String Reversals
 * @version $Id$
 */
public class StringReverse {
	public static void main(String[] argv) {
		//+
		String s = "Father Charles Goes Down And Ends Battle";

		// Put it in the stack frontwards
		Stack myStack = new Stack();
		StringTokenizer st = new StringTokenizer(s);
		while (st.hasMoreTokens()) myStack.push(st.nextElement());

		// Print the stack backwards
		System.out.print('"' + s + '"' + " backwards by word is:\n\t\"");
		while (!myStack.empty()) { 
			System.out.print(myStack.pop());
			System.out.print(' ');
		}
		System.out.println('"');
		//-
	}
}
