package chatgpt;

import java.io.*;
import java.nio.file.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URISyntaxException;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Calls ChatGPT API directly, using JSON-format requests and responses.
 */
// tag::main[]
public class ChatGptApiDemo {

	final static String URL = "https://api.openai.com/v1/chat/completions";

	public static void main(String[] args) throws Exception {
		System.out.println(chatGPT("What are the great themes in literature?"));
	}

	public static String chatGPT(String prompt) throws IOException, URISyntaxException {
		// Store your API key in a one-line text file:
		String apiKey = Files.readAllLines(Path.of("openai.apikey.txt")).getFirst();
		String model = "gpt-3.5-turbo";

		URL obj = new URI(URL).toURL();
		HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Authorization", "Bearer " + apiKey);
		connection.setRequestProperty("Content-Type", "application/json");

		// Send the request
		var request = new ChatGptRequest(model, "user", prompt);
		connection.setDoOutput(true);
		OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
		writer.write(request.toString());
		writer.flush();
		writer.close();

		// Get a response from ChatGPT
		int n = connection.getResponseCode();
		if (n == 429) {
			System.out.println("No ChatGPT wants to listen to your chuntering.");
			String rah = null;
			if ((rah = connection.getHeaderField("retry-after")) != null) {
				System.out.println("Try again: " + rah);
			}
			try (var br = new BufferedReader(new InputStreamReader(connection.getErrorStream()))) {
				while ((rah = br.readLine()) != null) {
					System.out.println(rah);
				}
			}
			return null;
		}
		System.out.println("Whew! Status was " + n);
		var inputStream = connection.getInputStream();

		if (inputStream != null) { // kluge for compiler
			try (BufferedReader rdr = new BufferedReader(new InputStreamReader(inputStream))) {
				String line;;
				while ((line = rdr.readLine()) != null) {
					System.out.println(line);
				}
			}
			return null;
		}
		var mapper = new ObjectMapper();
        ChatGptResponse resp = mapper.readValue(inputStream, ChatGptResponse.class);
		System.out.println(resp);
		return resp.choices[0].text;
   }

	static record ChatGptRequest(String model, String role, String prompt) {
		public String toString() {
			return 
				"{\"model\": \"%s\", \"messages\": [{\"role\": \"%s\", \"content\": \"%s\"}]}".formatted(
				model, role, prompt);
		}
	}

	/** These could be record not a class, but Jackson doesn't instantiate records */
	static class ChatGptResponse {
        private ChatGptCompletion[] choices;
		public String toString() {
			var sb = new StringBuilder();
			for (ChatGptCompletion comp : choices) {
				sb.append(comp).append('\n');
			}
			return sb.toString();
		}
	}

    static class ChatGptCompletion {
		String text;
		int index;
		Object logprobs;
		String finish_reason;
    }
// end::main[]
}
