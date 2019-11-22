package regex;

import java.util.regex.*;

// tag::main[]
/**
 * To perform multiple distinct substitutions in the same String,
 * you need a loop, and must call reset() on the matcher.
 */
public class MultiReplace {
	public static void main(String[] args) {

		Pattern patt = Pattern.compile("cat|dog");
		String line = "The cat and the dog never got along well.";
		System.out.println("Input: " + line);
		Matcher matcher = patt.matcher(line);
		while (matcher.find()) {
			String found = matcher.group(0);
			String replacement = computeReplacement(found);
			line = matcher.replaceFirst(replacement);
			matcher.reset(line);
		}
		System.out.println("Final: " + line);
	}

	static String computeReplacement(String in) {
		switch(in) {
		case "cat": return "feline";
		case "dog": return "canine";
		default: return "animal";
		}
	}
}
// end::main[]
