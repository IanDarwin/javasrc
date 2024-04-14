package netweb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.URISyntaxException;

/**
 * Read a URL's data from a URLConnection
 */
public class URLConnectionDemo {
	
    public static void main(String[] args) throws Exception {
		if (args.length == 0) {
			String host = "192.168.2.42";
			String path = "/andcook/seam/resource/rest/recipe/list";
			String postBody = null;
			String ret = converse(host, 80, path, postBody);
			System.out.println(ret);
		} else {
			for (String url : args) {
				System.out.println(converse(url));
			}
		}
    }
    
    public static String converse(String host, int port, String path, String postBody) throws IOException, URISyntaxException {
		URL url = new URI("http", null, host, port, path, null, null).toURL();
		return converse(url, postBody);
	}

	public static String converse(String url) throws IOException {
		return converse(URI.create(url).toURL(), null);
	}

	public static String converse(URL url, String postBody) throws IOException {
        URLConnection conn = url.openConnection();
        boolean post = postBody != null;
        if (post)
        	conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setAllowUserInteraction(true);
        
        conn.connect();
	
        if (post) {
        	PrintWriter out = new PrintWriter(conn.getOutputStream());
        	out.println(postBody);
        	out.close();			// Important!
        }

        StringBuilder sb = new StringBuilder();
        BufferedReader in = new BufferedReader(
        		new InputStreamReader(conn.getInputStream()));
        String line;

        while ((line = in.readLine()) != null) {
            sb.append(line);
        }

        in.close();
        return sb.toString();
    }
}
