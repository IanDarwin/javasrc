/**
 * Template for standalone, line-mode main program.
 * @author Ian F. Darwin, ian@darwinsys.com
 * @version $Id$
 */
public class RegUse1 {
	public static void main(String argv[]) {
		//+
		String patt = "^Q[^u]\\d+\\.";
		boolean found = RE.isMatch(patt, argv[0]);
		System.out.println(patt +
			(found ? "matches " : "doesn't match ") + argv[0]);
		//-
	}
}
