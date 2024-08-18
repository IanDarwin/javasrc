package langchain4j;

import ai.Constants;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;

// tag::main[]
/**
 * Simple demo of a chat-gpt text prompt and response.
 */
public class LangChain4JSimplePrompt {

    public static void main(String[] args) {

        String apiKey = Constants.getOpenAPIKey();
        ChatLanguageModel model = OpenAiChatModel.withApiKey(apiKey);

        String results = model.generate(Constants.TEXT_PROMPT);

        System.out.println(results);
    }
}
// end::main[]
