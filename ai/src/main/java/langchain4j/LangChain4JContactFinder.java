package langchain4j;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.UserMessage;

import ai.Constants;

// tag::main[]
public class LangChain4JContactFinder {

	record Contact(					// <1>
		String firstName,
		String lastName,
		String company,
		String street,
		String city,
		String state,
		String code) {

		@Override
		public String toString() {			// <2>
			return String.format("""
				Contact {
					name    : %s %s
					company : %s
					street  : %s
					place   : %s, %s %s
				}""",
				firstName, lastName, company,
				street, city, state, code);
		}
	}

	interface ContactFinder {				// <3>

		@UserMessage("Extract information about a person from {{it}}")
		Contact extractContactFrom(String text); // <4>
	}

	public static void main(String[] args) {

		ChatLanguageModel chatLanguageModel = OpenAiChatModel.builder()
				.apiKey(Constants.getOpenAPIKey())
				.responseFormat("json_object") // <5>
				.build();					// <6>

		ContactFinder finder = 				// <7>
			AiServices.create(ContactFinder.class, chatLanguageModel);

		String[] texts = {					// <8>
				"John Doe, 123 Main St, Anytown, AZ 87654",
				"Mary Smith, 456 Cherry Lane, Someplace CA 90210",
				"John Henry Acme Widgets General Delivery Southampton MA",
		};

		for (String text : texts) {			// <9>
			Contact ctc = finder.extractContactFrom(text);
			System.out.println(ctc);
		}
	}
}
// end::main[]
