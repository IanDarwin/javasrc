package logging;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * Specify our own java.util.logging config file.
 * Must be invoked by the user application!
 * Use instead of -Djava.util.logging.config.file=fullPathToLogConfig
 * or modifying the JRE to install your logging config there.
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
