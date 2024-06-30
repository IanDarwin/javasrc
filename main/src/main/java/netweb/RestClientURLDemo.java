package netweb;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URLConnection;

// tag::main[]
public class RestClientURLDemo {
	public static void main(String[] args) throws Exception {
		HttpURLConnection conn = (HttpURLConnection)
			URI.create(HttpClientDemo.urlString +
				HttpClientDemo.keyword).toURL()
				.openConnection();
		try (BufferedReader is =
			new BufferedReader(
				new InputStreamReader(conn.getInputStream()))) {
			String line;
			while ((line = is.readLine()) != null) {
				System.out.println(line);
			}
		}
	}
}
// end::main[]
