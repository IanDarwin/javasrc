package lang;

/** Test case for "finally", to show its effect with
 * return or exit.
 */
public class Finally {
	/** Main method, does the work of the testing... */
	public static void main(String[] argv) {

		try {
			throw new Exception();
		} catch (Exception e) {
			System.out.println("Caught Exception");
			return;		// with return, Goodbye appears
			// System.exit(1);	// with exit, Goodbye does not appear
		} finally {
			System.out.println("Goodbye!");
		}
	}
}
