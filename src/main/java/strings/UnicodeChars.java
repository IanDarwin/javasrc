/**
 * Conversion between Unicode characters and bytes
 * @author Ian F. Darwin, ian@darwinsys.com
 * @version $Id$
 */
public class UnicodeChars {
	public static void main(String argv[]) {
		//+
		StringBuffer b = new StringBuffer();
		for (char c = 'a'; c<'d'; c++) {
			b.append(c);
			System.out.println("Character #" + (c-'a') + " is " + c);
		}
		System.out.println("Accumulated characters are " + b);
		//-
	}
}
