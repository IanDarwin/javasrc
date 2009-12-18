package netweb;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

/**
 * Demonstrate reading a URL's data from a URLConnection
 */
public class URLConnectionDemo {
	
	    public static void main(String[] args) throws Exception {
	    	String host = null;
	      	host = "127.0.0.1";
	        URL url = new URL("http://" + host + ":8080/MySSOAuth");
	        URLConnection conn = url.openConnection();
	        conn.setDoInput(true);
	        conn.setDoOutput(true);
	        conn.setAllowUserInteraction(true);
	        System.out.println("Adding cookie");
	        conn.addRequestProperty("Cookie", "JSESSIONID=551014ECE1008B90A66F2EF73A95A751");
	        conn.addRequestProperty("Cookie", "test=no");
	        
	        conn.connect();
		
	        System.out.println("Sending POST body");
	        PrintWriter out = new PrintWriter(conn.getOutputStream());
	        out.println("<cat:findCatalogItem>");
	        out.println("	<productId>3012</productId>");
	        out.println("</cat:findCatalogItem>");
	        out.close();

	        System.out.println("Reading response");
	        BufferedReader in = new BufferedReader(
	        		new InputStreamReader(conn.getInputStream()));
	        String inputLine;

	        while ((inputLine = in.readLine()) != null) {
	            System.out.println(inputLine);
	        }

	        in.close();
	        System.out.println("Done");
	    }
	

}
