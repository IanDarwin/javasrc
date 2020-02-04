package logging;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

// tag::main[]
public class Log4JDemo2 {

	private static Logger myLogger = LogManager.getLogger();

	public static void main(String[] args) {

		try {
			Object o = new Object();
			myLogger.info("I created an object: " + o);
			if (o != null) {	// bogus, just to show logging
				throw new IllegalArgumentException("Just testing");
			}
		} catch (Exception ex) {
			myLogger.error("Caught Exception: " + ex, ex);
		}
	}
}
// end::main[]
