package logging;

import java.util.logging.Logger;

// tag::main[]
public class JulLogDemo {
	public static void main(String[] args) {

		Logger myLogger = Logger.getLogger("com.darwinsys");

		Object o = new Object();
		myLogger.info("I created an object: " + o);
	}
}
// end::main[]
