package packaging;

import java.io.*;
import java.util.*;

/** Show using classLoader.getResource() to load a file.
 * This is required under Java Web Start.
 */
public class GetResourceDemo {
	public static void main(String[] args) {
		new GetResourceDemo().demo();
	}

	/** The demo itself */
	public void demo() {
		// BEGIN main
		// Find the ClassLoader that loaded us.
		// Regard it as the One True ClassLoader for this app.
		ClassLoader loader = this.getClass().getClassLoader();

		// Use the loader's getResource() method to open the file.
		InputStream is = loader.getResourceAsStream("widgets.properties");
		if (is == null) {
			System.err.println("Can't load properties file");
			return;
		}

		// Create a Properties object
		Properties p = new Properties();

		// Load the properties file into the Properties object
		try {
			p.load(is);
		} catch (IOException ex) {
			System.err.println("Load failed: " + ex);
			return;
		}

		// List it to confirm that we loaded it.
		p.list(System.out);
		// END main
	}
}
