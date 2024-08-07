package chatgpt;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URISyntaxException;

import ai.Constants;
import com.fasterxml.jackson.databind.ObjectMapper;

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

		URL obj = new URI(URL).toURL();
		HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Authorization", "Bearer " + apiKey);
		connection.setRequestProperty("Content-Type", "application/json");

		// Send the request
		var request = new ChatGptRequest(model, "user", prompt);
		connection.setDoOutput(true);
		OutputStreamWriter writer =
			new OutputStreamWriter(connection.getOutputStream());
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
			try (var br = 
				new BufferedReader(new InputStreamReader(connection.getErrorStream()))) {
				while ((rah = br.readLine()) != null) {
					System.out.println(rah);
				}
			}
			return null;
		}
		System.out.println("HTTP Status was " + n);
		var inputStream = connection.getInputStream();

		if (DUMP_RAW) {
			try (BufferedReader rdr =
						 new BufferedReader(new InputStreamReader(inputStream))) {
				String line;
				while ((line = rdr.readLine()) != null) {
					System.out.println(line);
				}
				return "Answer dumped, no JSON parsing done.";
			}
		} else {
			var mapper = new ObjectMapper();
			ChatGptResponse resp = mapper.readValue(inputStream, ChatGptResponse.class);
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

	/** These could be a record not a class, but Jackson doesn't instantiate records */
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
	}

	static class ChatGptUsage {
		public int prompt_tokens;
		public int completion_tokens;
		public int total_tokens;
	}
// end::main[]
}
