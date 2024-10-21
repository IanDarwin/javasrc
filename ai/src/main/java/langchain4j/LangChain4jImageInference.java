package langchain4j;

import ai.Constants;
import dev.langchain4j.data.image.Image;
import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.ImageContent;
import dev.langchain4j.data.message.TextContent;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.output.Response;
import dev.langchain4j.model.openai.OpenAiChatModelName;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;


// tag::main[]
/**
 * Simple demo of a chat-gpt text+media prompt and response.
 */
public class LangChain4jImageInference {

	public static void main(String[] args) {

		String apikey = Constants.getOpenAPIKey();

		ChatLanguageModel model = OpenAiChatModel.builder()
				.apiKey(apikey)
				.modelName(OpenAiChatModelName.GPT_4_O)
				.maxTokens(100)
				.build();

		var fileName = "mystery-image.png";
		byte[] imageData = null;
		try {
			imageData = Files.readAllBytes(Path.of(fileName));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		Image image = Image.builder()
				.mimeType("image/png")
				.base64Data(Base64.getEncoder().encodeToString(imageData))
				.build();
		UserMessage userMessage = UserMessage.from(
				TextContent.from("Explain this image."),
				ImageContent.from(image)
		);

		Response<AiMessage> response = model.generate(userMessage);

		System.out.println(response.content().text());
	}
}
// end::main[]
