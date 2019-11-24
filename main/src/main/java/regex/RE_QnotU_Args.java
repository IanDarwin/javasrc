package regex;

import java.util.regex.*;

/**
 * Match the "Q[^u] pattern against strings from command line.
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
public class RE_QnotU_Args {
	public static void main(String[] argv) {
		String patt = "^Q[^u]\\d+\\.";
		Pattern r = Pattern.compile(patt);
		for (int i=0; i<argv.length; i++) {
			Matcher m = r.matcher(argv[i]);
			boolean found = m.lookingAt();
			System.out.println(patt +
				(found ? " matches " : " doesn't match ") + argv[i]);
		}
	}
}
