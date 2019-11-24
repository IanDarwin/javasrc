package lang;

/** This shows why a "return" in a "finally" is bad.
 * @author ian darwin
 */
public class ReturnInFinallyIsBad {
	@SuppressWarnings("finally")
	private static String getDestinationType() {
		String ret = "Foo";
		try {
			ret = "Bar";
			throw new RuntimeException("This is a drill");
		} finally {
			// Without @SuppressWarnings("finally"), this warns about:
			// finally block does not complete normally
			// because this return bypasses handling of the
			// RuntimeException (or any other thrown Throwable)
			return ret;
		}
	}

	public static void main(String[] args) {
		try {
			System.out.println(getDestinationType());
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}
}
