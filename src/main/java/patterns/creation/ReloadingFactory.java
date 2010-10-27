package patterns.creation;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

/** A Factory class as per page 517-2-20 that
 * does not need reloading when the file changes.
 * @author Ian Darwin, http://darwinsys.com/
 *
 */
public class ReloadingFactory {
	private final static Properties p = new Properties();
	private final static String CONFIG_FILE = "patterns/creation/factory.config";
	private final static URL fileLoc = ReloadingFactory.class.getClassLoader().getResource(CONFIG_FILE);
	private static File f = new File(fileLoc.getFile());	// contains full path to file!
	private static long timestamp = -1;
	
	public static void main(String[] args) throws Exception {
		MessageRenderer p = ReloadingFactory.getBean("renderer", MessageRenderer.class);
		System.out.println("Factory gave us a " + p.getClass() + " instance");
		System.out.println("You now have 15sec to edit " + f.getAbsolutePath());	
		Thread.sleep(15 * 1000);
		MessageRenderer p2 = ReloadingFactory.getBean("renderer", MessageRenderer.class);
		System.out.println("Factory gave us a " + p2.getClass() + " instance");
	}
	
	/** Get a particular kind of bean, the MessageRenderer instance */
	public static MessageRenderer getMessageRenderer() {
		try {
			return (MessageRenderer) 
				Class.forName(getConfigProperty("renderer")).newInstance();
		} catch (Exception e) {
			throw new RuntimeException("Cant load Renderer " + e, e);
		}
	}

	/** Generic getBean(name, TypeParameter) a la Spring 3.x */
	public static <T> T getBean(String name, Class<?> T) {
		try {
			final String clazzName = getConfigProperty(name);
			if (clazzName == null) {
				throw new RuntimeException("Config property " + name + " not found.");
			}
			return (T) Class.forName(clazzName).newInstance();
		} catch (Exception e) {
			throw new RuntimeException("Can't load Renderer " + e, e);
		}
	}

	/** Read a property from the Config file, (re)loading it if necessary */
	private static synchronized String getConfigProperty(String name) throws IOException {
		if (f.lastModified() != timestamp) {
			reload();
			timestamp = f.lastModified();
		}
		return p.getProperty(name);
	}

	/** Lazily (re)load the config file */
	private static synchronized void reload() throws IOException {
		FileInputStream is = null;
		try {
				is = new FileInputStream(f);
				p.load(is);
		} finally {			
			if (is != null) {
				is.close();
			}
		}
	}

}
