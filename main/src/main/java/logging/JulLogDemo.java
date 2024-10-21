package logging;

// tag::main[]
import java.util.logging.Logger;

public class JulLogDemo {
	public static void main(String[] args) {

		Logger myLogger = Logger.getLogger("com.darwinsys");

		Object o = new Object();
		myLogger.info("I created an object: " + o);
	}
}
// end::main[]
