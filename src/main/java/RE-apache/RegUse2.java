/**
 * Simple use of RE's: save-the-pattern-for-reuse version.
 * @author Ian F. Darwin, ian@darwinsys.com
 * @version $Id$
 */
public class RegUse2 {
	public static void main(String argv[]) {
		//+
		String patt = "^Q[^u]\\d+\\.";
		RE myRE = new RE(patt);
		for (int i=0; i<argv.length; i++) {
			boolean found = RE.isMatch(patt, argv[i]);
			System.out.println(patt +
				(found ? "matches " : "doesn't match ") + argv[i]);
		}
		//-
	}
}
