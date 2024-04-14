package netweb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

/**
 * TextBrowser - URL/text mode getter.
 * Display the contents of a text-only URL
 * @author	Ian Darwin, https://darwinsys.com/
 */
public class TextBrowser {

	public static void main(String[] av) {
		TextBrowser tb = new TextBrowser();
		if (av.length == 0) {
			System.err.println("Usage: TextBrowser URL [...]");
			System.exit(1);
		}
		else for (int i=0; i<av.length; i++) {
			tb.showDocument(av[i]);
		}
	}

	/** Show one document, by filename */
	protected void showDocument(String loc) {
		URL webURL = null;
		try {
			System.err.println("*** Loading " + loc + "... ***");
			webURL = URI.create(loc).toURL();
			BufferedReader is = new BufferedReader(
				new InputStreamReader(webURL.openStream()));
			String line;
			while ((line = is.readLine()) != null) {
				System.out.println(line);
			}
			is.close();
		} catch (MalformedURLException e) {
			System.err.println("Load failed: " + e);
		} catch (IOException e) {
			System.err.println("IOException: " + e);
		}
	}
}
