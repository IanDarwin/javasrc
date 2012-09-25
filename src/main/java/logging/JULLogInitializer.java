package logging;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * Alternate, contankerous way to specify our own java.util.logging config file.
 * ************  DO NOT USE AS AN EXAMPLE **************
 * This is NOT how you should specify logging in a standalone app, because it
 * must be invoked by the user application!
 * You should instead use -Djava.util.logging.config.file=fullPathToLogConfig
 * -or- set this in a System Property as Log14Demo2 does - best way!
 * However, feel free to use this when the simpler method, 
 * System.setProperty("java.util.logging.config.file", realPath),
 * fails, e.g., because the web container has already  intitialized the java.util.logging package
 * and hence the system property is ignored. 
 * @author Ian Darwin
 */
public class JULLogInitializer {

	private static final String LOGGING_CONFIG_FILE = "logging.properties";
	
	public static void init() {
		LogManager mgr = LogManager.getLogManager();
		InputStream is = null;
		try {
			Class<?> c = JULLogInitializer.class;
			is = c.getClassLoader().getResourceAsStream(c.getPackage().getName() + "/" + LOGGING_CONFIG_FILE);
			if (is == null) {
				Logger.getAnonymousLogger().severe("Could not load " + LOGGING_CONFIG_FILE);
				return;
			}
			mgr.readConfiguration(is);
			Logger.getAnonymousLogger().info("LOGGING INITIALIZED");
		} catch (IOException e) {
			System.err.println("Failed to load ");
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException tlnwr) {
					// tough luck, not worth reporting
				}
			}
		}
	}
}
