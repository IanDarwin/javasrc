package langchain4j;

import ai.Constants;
import dev.langchain4j.data.image.Image;
import dev.langchain4j.model.image.ImageModel;
import dev.langchain4j.model.openai.OpenAiImageModel;
import dev.langchain4j.model.output.Response;

import java.awt.*;
import java.io.IOException;
import java.net.URI;

public class LangChain4JImageMaker {

// tag::main[]
	public static void main(String[] args) {

		String apiKey = Constants.getChatGptKey();
		ImageModel model = OpenAiImageModel.withApiKey(apiKey);

		Response<Image> response = model.generate(Constants.IMAGE_PROMPT);

		var respUrl = response.content().url();

		System.out.printf("Response URL is %s\n", respUrl);

		if (Desktop.isDesktopSupported()) {
			Desktop dtop = Desktop.getDesktop();
			if (dtop.isSupported(Desktop.Action.BROWSE)) {
                try {
                    dtop.browse(URI.create(respUrl.toString()));
                } catch (IOException ex) {
					System.out.println("Unable to open, due to: " + ex);
                }
            } else {
				System.out.println("Desktop Open_URI Action not supported.");
			}
		} else {
			System.out.println("Desktop not supported");
		}
	}
}
// end::main[]
