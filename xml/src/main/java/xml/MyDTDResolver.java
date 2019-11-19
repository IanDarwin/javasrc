package xml;

import java.io.FileReader;
import java.io.IOException;

import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;

public class MyDTDResolver implements EntityResolver {
	public static final String[] dtds = {
		"http://java.sun.com/dtd/web-app_2_3.dtd",
		"http://java.sun.com/dtd/ejb-jar_2_0.dtd",
		"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd",
	};

	public static final String DTDDIR = System.getProperty("user.home") + "/dtds";

	public InputSource resolveEntity (String publicId, String systemId) {
		for (int i=0; i<dtds.length; i++) {
			if (systemId.equals(dtds[i])) {
				// return a local copy
				try {
					String dtdFile = 
						systemId.substring(systemId.lastIndexOf('/'));
						// includes the /
					return new InputSource(
						new FileReader(DTDDIR + dtdFile));
				} catch (IOException ex) {
					System.err.println("+================================+");
					System.err.println("DTD ERROR: " + ex.toString());
					System.err.println("... Trying to get from web...");
					System.err.println("+================================+");
					return null;
				}
			}
		}
		// Not matched any of the ones in the array.
		System.out.println("Warning: Did not resolve " + systemId);
		return null;
	}
}
