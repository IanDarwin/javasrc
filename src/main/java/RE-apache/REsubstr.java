/**
 * REsubstr -- demonstrate RE Match -> String.substring()
 * @author Ian F. Darwin, ian@darwinsys.com
 * @version $Id$
 */
public class REsubstr {
	public static void main(String[] argv) {
		//+
		String patt = "Q[^u]\\d+\\.";
		String line = "Order QT300. Now!";
		Match whence = RE.match(patt, line);
		System.out.println("match() returned " + whence);
		System.out.println(patt + " matches " + 
			line.substring(whence.start, whence.end));
		//-
	}
}
