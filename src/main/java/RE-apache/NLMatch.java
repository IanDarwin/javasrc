import org.apache.regexp.*;

/**
 * Show line ending matching using RE class.
 * @author Ian F. Darwin, ian@darwinsys.com
 * @version $Id$
 */
public class NLMatch {
	public static void main(String[] argv) throws RESyntaxException {

		String input = "I dream of engines\nmore engines, all day long";

		String[] patt = {
			"engines\nmore engines",
			"engines$^more engines"
		};

		for (int i = 0; i < patt.length; i++) {
			boolean found;
			RE r = new RE(patt[i]);
			System.out.println("PATTERN " + patt[i]);

			found = r.match(input);
			System.out.println("DEFAULT match " + found);

			r.setMatchFlags(RE.MATCH_MULTILINE);
			found = r.match(input);
			System.out.println("MATCH_MULTILINE match was " + found);
		}
	}
}
