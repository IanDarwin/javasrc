package netweb;

import java.net.MalformedURLException;
import java.net.URL;

public class ULRDemo {
	public static void main(String[] args) throws MalformedURLException {

		URL u = new URL("http://www.darwinsys.com/java/../openbsd/../index.jsp");
		System.out.println("Raw: " + u);

		URL url = new URL("bean:WonderBean");
		System.out.println(url);
	}
}
