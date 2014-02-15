package regex;

import java.util.regex.*;

// BEGIN main
/**
 * Quick demo of RE substitution: correct U.S. 'favor'
 * to Canadian/British 'favour', but not in "favorite"
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
public class ReplaceDemo {
	public static void main(String[] argv) {

		// Make an RE pattern to match as a word only (\b=word boundary)
		String patt = "\\bfavor\\b";

		// A test input.
		String input = "Do me a favor? Fetch my favorite.";
		System.out.println("Input: " + input);

		// Run it from a RE instance and see that it works
		Pattern r = Pattern.compile(patt);
		Matcher m = r.matcher(input);
		System.out.println("ReplaceAll: " + m.replaceAll("favour"));

		// Show the appendReplacement method
		m.reset();
		StringBuffer sb = new StringBuffer();
		System.out.print("Append methods: ");
		while (m.find()) {
			// Copy to before first match,
			// plus the word "favor"
			m.appendReplacement(sb, "favour");
		}
		m.appendTail(sb);		// copy remainder
		System.out.println(sb.toString());
	}
}
// END main
