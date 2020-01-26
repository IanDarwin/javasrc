package logging;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

// tag::main[]
public class Log4JDemo {
	public static void main(String[] args) {

		Logger myLogger = LogManager.getLogger();

		Object o = new Object();
		myLogger.info("I created an object: " + o);

	}
}
// end::main[]
