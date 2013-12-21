package logging;

import java.io.IOException;

/* A simple example of using the NetLog program.
 * Unrealistic in that it's standalone; this API is
 * intended for use inside another program, possibly
 * a servlet or EJB.
 */
// BEGIN main
public class NetLogSimple {

	public static void main(String[] args) throws IOException {

		System.out.println("NetLogSimple: Starting...");

		// Get the connection to the NetLog
		NetLog nl = new NetLog();

		// Show sending a String
		nl.log("Hello Java");

		// Show sending Objects
		nl.log(new java.util.Date());
		nl.log(nl);

		// Show sending null and "" (normally an accident...)
		nl.log(null);
		nl.log("");

		// All done, close the log
		nl.close();

		System.out.println("NetLogSimple: Done...");
	}
}
// END main
