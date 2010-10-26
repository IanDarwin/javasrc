package patterns.creation;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/** A Factory class as per page 517-2-20 that
 * does not need reloading when the file changes.
 * @author Ian Darwin, http://darwinsys.com/
 *
 */
public class SelfReloadingFactory {
	private final static Properties p = new Properties();
	private final static File f = new File("factory.props");
	private static long timestamp;
	
	/** Get a particular kind of bean */
	public static MessageRenderer getMessageRenderer() {
		try {
			return (MessageRenderer) 
				Class.forName(getConfigProperty("renderer")).newInstance();
		} catch (Exception e) {
			throw new RuntimeException("Cant load Renderer " + e, e);
		}
	}

	/** Generic getBean a la Spring */
	public static <T> T getBean(String name, Class<?> T) {
		try {
			return (T) Class.forName(getConfigProperty(name)).newInstance();
		} catch (Exception e) {
			throw new RuntimeException("Cant load Renderer " + e, e);
		}
	}

	/** Reload the config file */
	private static synchronized void reload() throws IOException {
		p.load(new FileInputStream("factory.config"));
	}

	/** Read a property from the Config file, (re)loading it if necessary */
	private static synchronized String getConfigProperty(String name) throws IOException {
		if (f.lastModified() != timestamp) {
			reload();
			timestamp = f.lastModified();
		}
		return p.getProperty(name);
	}
}
