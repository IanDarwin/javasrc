package logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.Person;

// BEGIN main
public class Slf4jDemo2 {

	final static Logger theLogger = LoggerFactory.getLogger(Slf4jDemo2.class);

	public static void main(String[] args) {

		try {
			Person p = new Person();
			// populate person's fields here...
			theLogger.info("I created an object {}", p);
			
			if (p != null) {	// bogus, just to show logging
				throw new IllegalArgumentException("Just testing");
			}
		} catch (Exception ex) {
			theLogger.error("Caught Exception: " + ex, ex);
		}
	}
}
// END main
