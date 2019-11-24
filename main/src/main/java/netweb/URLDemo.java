package netweb;

import java.net.MalformedURLException;
import java.net.URL;

public class URLDemo {
	public static void main(String[] args) throws MalformedURLException {

		for (String s : args) {
			System.out.println("Trying your URL: " + s);
			URL u = new URL(s);
			System.out.println("Created " + u);
		}

		System.out.println("This should work:");
		URL u = new URL("http://www.darwinsys.com/java/../openbsd/../index.jsp");
		System.out.println("URL: " + u);

		System.out.println("This should fail:");
		URL u2 = new URL("bean:WonderBean");
		System.out.println("URL: " + u2);
	}
}
