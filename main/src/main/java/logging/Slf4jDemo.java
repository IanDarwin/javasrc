package logging;

// tag::main[]
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Slf4jDemo {
	
	final static Logger theLogger = 
			LoggerFactory.getLogger(Slf4jDemo.class);
	
	public static void main(String[] args) {

		Object o = new Object();
		theLogger.info("I created this object: " + o);

	}
}
// end::main[]
