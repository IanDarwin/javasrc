package netweb;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class URLDemo {
	public static void main(String[] args) throws MalformedURLException {

		for (String s : args) {
			System.out.println("Trying your URL: " + s);
			URL u = URI.create(s).toURL();
			System.out.println("Created " + u);
		}

		System.out.println("This should work:");
		URL u = URI.create("https://darwinsys.com/java/../openbsd/../index.jsp").toURL();
		System.out.println("URL: " + u);

		System.out.println("This should fail:");
		URL u2 = URI.create("bean:WonderBean").toURL();
		System.out.println("URL: " + u2);
	}
}
