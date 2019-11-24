package netweb;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

// tag::main[]
public class RestClientURLDemo {
	public static void main(String[] args) throws Exception {
		URLConnection conn = new URL(
			HttpClientDemo.urlString + HttpClientDemo.keyword)
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
// end::main[]
