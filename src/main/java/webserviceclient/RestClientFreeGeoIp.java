package webserviceclient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

// BEGIN main
public class RestClientFreeGeoIp {
	public static void main(String[] args) throws Exception {
		URLConnection conn = new URL(
			"http://freegeoip.net/json/www.oreilly.com")
			.openConnection();
		try (BufferedReader is = 
			new BufferedReader(new InputStreamReader(conn.getInputStream()))) {

			String line;
			while ((line = is.readLine()) != null) {
				System.out.println(line);
			}
		}
	}
}
// END main
