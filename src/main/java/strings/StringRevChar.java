package strings;

/**
 * Reverse a string by character
 * @author Ian F. Darwin, http://www.darwinsys.com/
 * @version $Id$
 */
public class StringRevChar {
	public static void main(String[] argv) {
		// BEGIN
		String sh = "FCGDAEB";
		System.out.println(sh + " -> " + new StringBuilder(sh).reverse());
		// END
	}
}
