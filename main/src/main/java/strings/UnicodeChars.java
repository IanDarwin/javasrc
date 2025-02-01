package strings;

/**
 * Conversion between Unicode characters and Strings.
 * @author Ian F. Darwin, https://darwinsys.com/
 */
public class UnicodeChars {
	public static void main(String[] argv) {
		// tag::main[]
		// UnicodeChars.java
		StringBuilder b = new StringBuilder();
		for (char c = 'a'; c<'d'; c++) {
			b.append(c);
		}
		b.append('\u00a5');	// Japanese Yen symbol
		b.append('\u01FC');	// Roman AE with acute accent
		b.append('\u0391');	// GREEK Capital Alpha
		b.append('\u03A9');	// GREEK Capital Omega

		for (int i=0; i<b.length(); i++) {
			System.out.printf(
				"Character #%d (%04x) is %c%n",
				i, (int)b.charAt(i), b.charAt(i));
		}
		System.out.println("Accumulated characters are " + b);
		// end::main[]

		// tag::bigChars[]
		// Note: characters above 0xffff need special handling
		// Let's show Santa (0x1f385) + Star (0x2600):
		int[] codePoints = {0x1f385, 0x2600};
		String faceAndStar = new String(codePoints, 0, codePoints.length);
		System.out.println(faceAndStar);	// "🎅☀"
		// end::bigChars[]

	}
}
