/**
 * Template for standalone, line-mode main program.
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
