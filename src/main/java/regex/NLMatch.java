import java.util.regex.*;

/**
 * Show line ending matching using RE class.
 * @author Ian F. Darwin, ian@darwinsys.com
 * @version $Id$
 */
public class NLMatch {
	public static void main(String[] argv) throws PatternSyntaxException {

		String input = "I dream of engines\nmore engines, all day long";
		System.out.println("INPUT: " + input);
		System.out.println();

		String[] patt = {
			"engines\nmore engines",
			"engines$"
		};

		for (int i = 0; i < patt.length; i++) {
			System.out.println("PATTERN " + patt[i]);

			boolean found = Pattern.matches(patt[i], input);
			System.out.println("DEFAULT match " + found);

			r.setMatchFlags(RE.MATCH_MULTILINE);
			found = r.match(input);
			System.out.println("MATCH_MULTILINE match was " + found);
			System.out.println();
		}
	}
}
