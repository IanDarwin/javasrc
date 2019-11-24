package lang;

/**
 * Show one way of ensuring that an app gets run with -ea
 * @author Ian Darwin
 */
public class EnforceRunWithEnableAssert {

	public static void main(String[] args) {
		new EnforceRunWithEnableAssert().process();
	}

	private void process() {
		// Ensure that peeps run this with -ea
		try {
			assert this == null : "Sorry about this assertion fail; it does not indicate an error (see code).";
			System.err.println(getClass().getName() + ": " + "You MUST run this program with the JVM option -ea");
		} catch (AssertionError e) {
			// System.out.println("(Whew!)");
			// do nothing, this catch should happen!
		}
	}
}
