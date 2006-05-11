package netweb;

import java.net.*;
import java.io.*;

/**
 * Browser0 - Get the contents of a URL, write to stdout
 */
public class Browser0 {
	public static void main(String[] av) {
		new Browser0(av);
	}

	Browser0(String av[]) {
		String loc = null;
		switch(av.length) {
			case 0: loc = "http://localhost/"; break;
			case 1: loc = av[0]; break;
			default:
				System.err.println("Usage: getFromURL [url]");
				return;
		}
		try {
			URL Web = new URL(loc);
			BufferedReader is = new BufferedReader(
				new InputStreamReader(
				Web.openStream()));
			String line;
			while ((line = is.readLine()) != null)
				System.out.println(line);
			is.close();
		} catch (MalformedURLException e) {
			System.out.println("MalformedURLException: " + e);
		} catch (IOException e) {
			System.out.println("IOException: " + e);
		}
	}
}
