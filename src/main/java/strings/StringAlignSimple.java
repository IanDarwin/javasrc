package strings;

/* Align a page number on a 70-character line. */
// BEGIN main
public class StringAlignSimple {

	public static void main(String[] args) {
		// Construct a "formatter" to center strings.
		StringAlign formatter = new StringAlign(70, StringAlign.Justify.CENTER);
		// Try it out, for page "i"
		System.out.println(formatter.format("- i -"));
		// Try it out, for page 4. Since this formatter is
		// optimized for Strings, not specifically for page numbers,
		// we have to convert the number to a String
		System.out.println(formatter.format(Integer.toString(4)));
	}
}
// END main
