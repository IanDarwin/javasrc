import org.xml.sax.*;
import java.io.*;

public class MyDTDResolver implements EntityResolver {
	public static final String WEBAPPDTD =
		"http://java.sun.com/dtd/web-app_2_3.dtd";
	public static final String DTDDIR = "/home/ian/dtds";
	public static final String DTDFILE = "web-app_2_3.dtd";

	public InputSource resolveEntity (String publicId, String systemId) {
		if (systemId.equals(WEBAPPDTD)) {
			// return a local copy
			try {
				return new InputSource(
					new FileReader(DTDDIR + "/" + DTDFILE));
			} catch (IOException ex) {
				System.err.println("+================================+");
				System.err.println("DTD ERROR: " + ex.toString());
				System.err.println("... Trying to get from web...");
				System.err.println("+================================+");
				return null;
			}
		} else {
			  // use the default behaviour
			return null;
		}
	}
}
