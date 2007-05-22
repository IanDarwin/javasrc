package logging;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Log14Demo2 {
	public static void main(String[] args) {

		Logger logger = Logger.getLogger("com.darwinsys");

		try {
			Object o = new Object();
			if (o != null) {	// bogus, just to show logging
				throw new IllegalArgumentException("Just testing");
			}
			logger.info("I created an object: " + o);
		} catch (Exception t) {
			// Long form, more control.
			// LogRecord msg = new LogRecord(Level.SEVERE, "Caught exception");
			// msg.setThrown(t);
			// logger.log(msg);
			// All-in-one call:
			logger.log(Level.SEVERE, "Caught Exception", t);
		}
	}
}
