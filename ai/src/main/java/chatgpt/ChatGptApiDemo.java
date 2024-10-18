package chatgpt;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import ai.Constants;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

/**
 * Calls ChatGPT API directly, using JSON-format requests and responses.
 */
public class ChatGptApiDemo {
	// tag::main[]
	final static String URL = "https://api.openai.com/v1/chat/completions";
	final static boolean DUMP_RAW = false;

	public static void main(String[] args) throws Exception {
		System.out.println(chatGPT(Constants.TEXT_PROMPT));
	}

	public static String chatGPT(String prompt)
			throws IOException, URISyntaxException {
		String apiKey = Constants.getOpenAPIKey();
		String model = "gpt-4o";
		HttpClient client = HttpClient.newHttpClient();

		URI uri = new URI(URL);

		// Send the request
		var request = new ChatGptRequest(model, "user", prompt);
		String json = request.toString();
		System.out.println("Sending this: " + json);

        HttpRequest webRequest = HttpRequest.newBuilder()
				.uri(uri)
				.header("Content-Type", "application/json")
				.header("Authorization", "Bearer " + apiKey)
				.POST(HttpRequest.BodyPublishers.ofString(json))
				.build();
		HttpResponse<String> response = null;
		try {
			response = client.send(webRequest, HttpResponse.BodyHandlers.ofString());
			System.out.println("Response code: " + response.statusCode());
			System.out.println("Response body: " + response.body());
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}

		// Get a response from ChatGPT
		int n = response.statusCode();
		switch(n) {
			case 429:
				System.out.println("No ChatGPT wants to listen to your chuntering.");
				String rah = null;
				if ((rah = response.headers().map().get("retry-after").getFirst()) != null) {
					System.out.println("Try again: " + rah);
				}
				System.out.println("Response body: " + response.body());
				return null;
			case 500: case 501: case 502:
				System.out.println("GPT Server error " + n + " " + response.body());
				return null;
			default:
				System.out.println("HTTP Status was " + n);
		}

		if (DUMP_RAW) {
			System.out.println("Response body: " + response.body());
			return "Answer dumped, no JSON parsing done.";
		} else {
			var mapper = new ObjectMapper();
			// Guard against fields added as GPT evolves
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			ChatGptResponse resp = mapper.readValue(response.body(), ChatGptResponse.class);
			System.out.println(resp);
			return resp.choices[0].message.content;
		}
   }

	record ChatGptRequest(String model, String role, String prompt) {
		public String toString() {
			return """
				{"model": "%s", "messages": [{"role": "%s", "content": "%s"}]}"""
				.formatted(model, role, prompt);
		}
	}

	/** These could be records or classes */
	static class ChatGptResponse {
		public String id;
		public ChatGptChoice[] choices;
		public String object;
		public long created;
		public String model;
		public ChatGptUsage usage;
		public String system_fingerprint;

		public String toString() {
			var sb = new StringBuilder();
			for (ChatGptChoice comp : choices) {
				sb.append(comp).append('\n');
			}
			return sb.toString();
		}
	}

	static class ChatGptChoice{
		public int index;
		public ChatGptMessage message;
		public Object logprobs;
		public String finish_reason;
	}

	static class ChatGptMessage {
		public String role;
		public String content;
		public String refusal;
	}

	static class ChatGptUsage {
		public int prompt_tokens;
		public Object prompt_tokens_details;
		public int completion_tokens;
		public Object completion_tokens_details;
		public int total_tokens;
	}
// end::main[]
}
