package lang;

/** What happens if a method declares an unchecked exception?
 * Some people seem to think that declaring a method in a throws
 * clause is what makes it "checked". As we see here, this is not
 * the case; checked-ness has only to do with an exception's place
 * in Java's class inheritance hierarchy.
 */
public class ThrowsUnchecked {

	public static void main(String[] argv) {
		new ThrowsUnchecked().doTheWork();
	}

	/** This method demonstrates calling a method that might throw
	 * an exception, and catching the resulting exception.
	 */
	public void doTheWork() {
		String s = " 42";
		int i = testit(s);	// Note: compiles with no try/catch.
		System.out.println("parseit(" + s + ") returned " + i);
	}

	/** Model of a method that might throw an unchecked exception.
	 * @exception	NumberFormatException	if called with value 1.
	 */
	public int testit(String input) throws NumberFormatException {
		return Integer.parseInt(input);
	}
	// Yes, I know that the testit method is redundant, that
	// Integer.parseInt is the same; it's here for pedagogical reasons
}
