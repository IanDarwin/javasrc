package langchain4j;

import ai.Constants;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;

// tag::main[]

/**
 * Simple demo of a chat-gpt text prompt and response.
 */
public class LangChain4JMixedMedia {

    public static void main(String[] args) {

        String apiKey = Constants.getOpenAPIKey();
        ChatLanguageModel model = OpenAiChatModel.withApiKey(apiKey);

        String themes = model.generate(Constants.TEXT_PROMPT);

        System.out.println(themes);
    }
}
// end::main[]
