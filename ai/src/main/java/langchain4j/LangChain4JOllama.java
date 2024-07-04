package langchain4j;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.ollama.OllamaChatModel;
import ai.Constants;

class LangChain4JOllama {

    static String MODEL_NAME = "mistral";

    public static void main(String[] args) {

        ChatLanguageModel model = OllamaChatModel.builder()
                .baseUrl("http://127.0.0.1:11434")
                .modelName(MODEL_NAME)
                .build();

        String answer = model.generate(Constants.TEXT_PROMPT);

        System.out.println(answer);
    }

}
