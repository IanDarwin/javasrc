package logging;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

// tag::main[]
public class JulLogDemo2 {
	public static void main(String[] args) {

		// Must be a full path; does not use getResourceAsStream()
		final Path path = Path.of("target/classes" + "/" +
				"logging.properties");
		if (!Files.exists(path)) {
			System.out.printf("path %s non-existent\n", path);
		}
		System.setProperty("java.util.logging.config.file", path.toString());
		System.out.println(System.getProperty("java.util.logging.config.file"));

		Logger logger = Logger.getLogger("com.darwinsys");

		try {
			Object o = new Object();
			logger.info("I created an object: " + o);
			if (o != null) {  // just a demo to show exception logging
				throw new IllegalArgumentException("Just testing");
			}
		} catch (Exception t) {
			logger.log(Level.SEVERE, "Caught Exception", t);
		}
	}
}
// end::main[]
