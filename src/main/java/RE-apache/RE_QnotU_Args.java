import org.apache.regexp.*;

/**
 * Simple example of using RE class.
 * @author Ian F. Darwin, ian@darwinsys.com
 * @version $Id$
 */
public class RE_QnotU_Argv {
	public static void main(String[] argv) throws RESyntaxException {
		String patt = "^Q[^u]\\d+\\.";
		RE r = new RE(patt);
		for (int i=0; i<argv.length; i++) {
			boolean found = r.match(argv[i]);
			System.out.println(patt +
				(found ? "matches " : "doesn't match ") + argv[i]);
		}
	}
}
