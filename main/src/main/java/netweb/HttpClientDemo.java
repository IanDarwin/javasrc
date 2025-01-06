package netweb;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Redirect;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.charset.StandardCharsets;

/**
 * Simple demo of Java 11+ HttpClient (not the older Apache one)
 */
public class HttpClientDemo {

	public static String urlString = 
		"https://suggestqueries.google.com/complete/search?client=firefox&q=";
	public static String searchString = "darwin";

	public static void main(String[] args)
		throws IOException, InterruptedException {

		// In a real app, we might update urlString from 'args' somehow

		// tag::setup[]
		// This object would be kept for the life of an application
		HttpClient client = HttpClient.newBuilder()
			.followRedirects(Redirect.NORMAL)
			.version(Version.HTTP_1_1)
			.build();

		// Build the HttpRequest object to "GET" the urlString
		HttpRequest req =
			HttpRequest.newBuilder(URI.create(urlString + 
				URLEncoder.encode(searchString, StandardCharsets.UTF_8)))
			.header("User-Agent", "Ministry of Silly Walks")
			.GET()
			.build();
		// end::setup[]

		// tag::sendSynch[]
		// Send the request - synchronously
		HttpResponse<String> resp = 
			client.send(req, BodyHandlers.ofString());

		// Collect the results
		if (resp.statusCode() == 200) {
			String response = resp.body();
			System.out.println(response);
		} else {
			System.out.printf("ERROR: Status %d on request %s\n",
				resp.statusCode(), urlString);
		}
		// end::sendSynch[]

		// tag::sendAsynch[]
		// Send the request - asynchronously
		client.sendAsync(req, BodyHandlers.ofString())
			.thenApply(HttpResponse::body)
			.thenAccept(System.out::println)
			.join();
		// end::sendAsynch[]

		client.close();
	}
}
