package regex;

import java.util.regex.*;

/**
 * Show line ending matching using RE class.
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
// BEGIN main
public class NLMatch {
	public static void main(String[] argv) {

		String input = "I dream of engines\nmore engines, all day long";
		System.out.println("INPUT: " + input);
		System.out.println();

		String[] patt = {
			"engines.more engines",
			"ines\nmore",
			"engines$"
		};

		for (int i = 0; i < patt.length; i++) {
			System.out.println("PATTERN " + patt[i]);

			boolean found;
			Pattern p1l = Pattern.compile(patt[i]);
			found = p1l.matcher(input).find();
			System.out.println("DEFAULT match " + found);

			Pattern pml = Pattern.compile(patt[i], 
				Pattern.DOTALL|Pattern.MULTILINE);
			found = pml.matcher(input).find();
			System.out.println("MultiLine match " + found);
			System.out.println();
		}
	}
}
// END main
