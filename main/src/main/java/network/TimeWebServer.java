import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Redirect;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Optional;

void main(String[] args) throws Exception {

// This object should be kept for the life of an application
HttpClient client = HttpClient.newBuilder()
	.followRedirects(Redirect.NORMAL)
	.version(Version.HTTP_1_1)
	.build();

String variant = args.length == 1 ? args[0] : "Some kind";

int MAX = 5000;

String URL_STR = "http://localhost:8080/";

long start_t = System.currentTimeMillis();

for (int i = 0; i < MAX; i++) {
	fetch(URL_STR, client);
}

long end_t = System.currentTimeMillis();

System.out.printf("Time for %d fetches using %s was %d mSec",
	MAX, variant, end_t - start_t);

client.close();

}

Optional<String> fetch(String urlString, HttpClient client) throws Exception {

	// Build the HttpRequest object to "GET" the urlString
	HttpRequest req =
		HttpRequest.newBuilder(URI.create(urlString))
		.header("User-Agent", "Dept of Silly Walks")
		.GET()
		.build();

	// Send the request - synchronously
	HttpResponse<String> resp = 
		client.send(req, BodyHandlers.ofString());

	// Collect the results
	if (resp.statusCode() == 200) {
		String response = resp.body();
		return Optional.of(response);
	} else {
		throw new RuntimeException("ERROR: Status %d on request %s".formatted(
			resp.statusCode(), urlString));
	}
}
