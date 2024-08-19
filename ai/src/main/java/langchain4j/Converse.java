package langchain4j;

import ai.Constants;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;

interface Chatterer {
	String speak(String response);
}

public class Converse {

	public static void main(String[] args) { 

        String apiKey = Constants.getOpenAPIKey();
        ChatLanguageModel model = OpenAiChatModel.withApiKey(apiKey);

		Chatterer chatter = AiServices.builder(Chatterer.class)
			.chatLanguageModel(model)
			.chatMemory(MessageWindowChatMemory.withMaxMessages(10))
			.build();

		System.out.println(">>> " + Constants.TEXT_PROMPT);
		String list = chatter.speak(Constants.TEXT_PROMPT);
		System.out.println(list);
		System.out.println();

		final String moreOnThird = "Tell me more about the third item";
		System.out.println(">>> " + moreOnThird);
		String third = chatter.speak(moreOnThird);
		System.out.println(third);
		System.out.println();

		final String whosOnFirst = "What more can you say about the first item";
		System.out.println(">>> " + whosOnFirst);
		String first = chatter.speak(whosOnFirst);
		System.out.println(first);
	}
}
