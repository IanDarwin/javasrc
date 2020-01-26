package logging;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

// tag::main[]
public class Log4JDemo2 {
	public static void main(String[] args) {

		Logger theLogger = LogManager.getLogger();

		try {
			Object o = new Object();
			theLogger.info("I created an object: " + o);
			if (o != null) {	// bogus, just to show logging
				throw new IllegalArgumentException("Just testing");
			}
		} catch (Exception ex) {
			theLogger.error("Caught Exception: " + ex, ex);
		}
	}
}
// end::main[]
