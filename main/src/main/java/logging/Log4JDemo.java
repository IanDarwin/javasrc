package logging;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

// tag::main[]
public class Log4JDemo {

	private static Logger myLogger = LogManager.getLogger();

	public static void main(String[] args) {

		Object o = new Object();
		myLogger.info("I created an object: " + o);

	}
}
// end::main[]
