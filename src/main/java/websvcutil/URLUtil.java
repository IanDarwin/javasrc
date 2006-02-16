package util;

import java.net.URL;
import java.net.MalformedURLException;

/** Start of a series of methods for fiddling with URLs in a
 * web services context, e.g., change the port and/or host (useful
 * for passing through Axis TCPMON).
 * @author ian
 */
public class URLUtil {
	
	public static String changeURLPort(String oldURL, int newPort) throws MalformedURLException {
		URL u = new URL(oldURL);
		if (!u.getProtocol().startsWith("http"))
			throw new IllegalArgumentException();
		//u.setPort(newPort);
		URL n = new URL(u.getProtocol() + "://" + 
				u.getHost() + ":" + newPort + u.getPath());
		return n.toString();
	}
	
	
}
