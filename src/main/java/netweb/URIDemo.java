package netweb;

import java.net.URI;
import java.net.URL;
import java.net.URISyntaxException;
import java.net.MalformedURLException;

// BEGIN main
public class URIDemo {
	public static void main(String[] args)
	throws URISyntaxException, MalformedURLException {

		URI u = new URI("https://darwinsys.com/java/../openbsd/../index.jsp");
		System.out.println("Raw: " + u);
		URI normalized = u.normalize();
		System.out.println("Normalized: " + normalized);
		final URI BASE = new URI("https://darwinsys.com");
		System.out.println("Relativized to " + BASE + ": " + BASE.relativize(u));

		// A URL is a type of URI
		URL url = new URL(normalized.toString());
		System.out.println("URL: " + url);

		// Demo of non-URL but valid URI
		URI uri = new URI("bean:WonderBean");
		System.out.println(uri);
	}
}
// END main
