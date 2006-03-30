package logging;

import org.apache.log4j.Logger;

public class Log4JDemo2 {
	public static void main(String[] args) {

		Logger myLogger = Logger.getLogger("com.darwinsys");

		try {
			Object o = new Object();
			if (o != null) {	// bogus, just to show logging
				throw new IllegalArgumentException("Just testing");
			}
			myLogger.info("I created an object: " + o);
		} catch (Exception ex) {
			// XXX
			myLogger.error("Caught Exception: " + ex);
		}
	}
}
