package logging;

import java.util.logging.Logger;

// BEGIN main
/** Demonstrate how Java 8 Lambdas avoid extraneous object creation
 * @author Ian Darwin
 */
public class JulLambdaDemo {
	public static void main(String[] args) {

		Logger myLogger = Logger.getLogger("com.darwinsys.jullambda");

		Object o = new Helper();
		
		// If you change the log call from finest to info,
		// you see both the systrace from the toString,
		// and the logging output. As it is here,
		// you don't see either, so the toString() is not called!
		myLogger.finest(() -> "I created this object: " + o);
	}
	
	static class Helper {
		public String toString() {
			System.out.println("JulLambdaDemo.Helper.toString()");
			return "failure!";
		}
	}
}
// END main
