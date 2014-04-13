package logging;

import java.util.logging.Level;
import java.util.logging.Logger;

// BEGIN main
public class Log14Demo2 {
	public static void main(String[] args) {

		System.setProperty("java.util.logging.config.file", 
			"logging/logging.properties");

		Logger logger = Logger.getLogger("com.darwinsys");

		try {
			Object o = new Object();
			logger.info("I created an object: " + o);
			if (o != null) {	// bogus, just to show logging
				throw new IllegalArgumentException("Just testing");
			}
		} catch (Exception t) {
			// All-in-one call:
			logger.log(Level.SEVERE, "Caught Exception", t);
			// Alternate: Long form, more control.
			// LogRecord msg = new LogRecord(Level.SEVERE, "Caught exception");
			// msg.setThrown(t);
			// logger.log(msg);
		}
	}
}
// END main
