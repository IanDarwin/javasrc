import java.util.logging.Logger;
import java.util.logging.LogRecord;
import java.util.logging.Level;

public class Log14Demo2 {
	public static void main(String[] args) {

		Logger myLogger = Logger.getLogger("com.darwinsys");

		try {
			Object o = new Object();
			if (o != null) {	// bogus, just to show logging
				throw new IllegalArgumentException("Just testing");
			}
			myLogger.info("I created an object: " + o);
		} catch (Throwable t) {
			LogRecord msg = new LogRecord(Level.SEVERE,
				"Caught exception ");
			msg.setThrown(t);
			myLogger.log(msg);
		}
	}
}
