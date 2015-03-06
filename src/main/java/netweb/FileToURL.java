package netweb;

import java.io.File;
import java.net.URL;

/** Convert a filename to a URL using the Java 2 java.io.File.toURL()
 */
public class FileToURL
{
	public static void main(String[] argv)
	throws java.net.MalformedURLException {
		@SuppressWarnings("deprecation")
		URL u = new File("GetResource.java").toURL();
		System.out.println(u);
	}
}
