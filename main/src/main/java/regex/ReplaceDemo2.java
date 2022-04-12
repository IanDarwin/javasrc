package regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Another demo of RE substitution: 
 * change "Ian Darwin" to "Darwin, Ian"
 */
// tag::main[]
public class ReplaceDemo2 {
	public static void main(String[] argv) {

		// Make an RE pattern 
		String patt = "(\\w+)\\s+(\\w+)";

		// A test input.
		String input = "Ian Darwin";
		System.out.println("Input: " + input);

		// Run it from a RE instance and see that it works
		Pattern r = Pattern.compile(patt);
		Matcher m = r.matcher(input);
		m.find();
		System.out.println("Replaced: " + m.replaceFirst("$2, $1"));
		
		// The short inline version:
		// System.out.println(input.replaceFirst("(\\w+)\\s+(\\w+)", "$2, $1"));
	}
}
// end::main[]
