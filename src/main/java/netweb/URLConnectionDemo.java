package netweb;

import java.net.*;
import java.io.*;

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
	        System.out.println("Adding cookie");
	        conn.addRequestProperty("Cookie", "JSESSIONID=551014ECE1008B90A66F2EF73A95A751");
	        conn.addRequestProperty("Cookie", "test=no");
	        conn.connect();
	        BufferedReader in = new BufferedReader(
	        		new InputStreamReader(conn.getInputStream()));
	        System.out.println("Reading response");
	        String inputLine;

	        while ((inputLine = in.readLine()) != null)
	            System.out.println(inputLine);

	        in.close();
	        System.out.println("Done");
	    }
	

}
