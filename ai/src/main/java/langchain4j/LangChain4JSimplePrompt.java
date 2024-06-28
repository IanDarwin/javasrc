package langchain4j;

import ai.Constants;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;

public class LangChain4JSimplePrompt {

    public static void main(String[] args) {

        String apiKey = Constants.getChatGptKey();
        ChatLanguageModel model = OpenAiChatModel.withApiKey(apiKey);

        String themes = model.generate(Constants.PROMPT);

        System.out.println(themes);
    }
}
