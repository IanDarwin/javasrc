package netweb;

import java.io.*;
import java.net.http.*;
import java.net.http.HttpClient.*;
import java.net.http.HttpResponse.*;
import java.net.*;

public class HttpClientDemo {

	private static String urlString = 
		"https://suggestqueries.google.com/complete/search?output=toolbar&hl=en&client=firefox&q=";
        // client=firefox makes the output come back in JSON
	private static String keyword = "darwin";

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
			HttpRequest.newBuilder(URI.create(urlString + keyword))
			.header("User-Agent", "Dept of Silly Walks")
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
		// tag::sendAsynch[]

	}
}
