package ai;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Constants {
    public static final String PROMPT =
            "What are the great themes in literature?";
    public static final String OPENAI_KEY_FILE = "openai.apikey.txt";

    public static final String getChatGptKey() {
        try {
            return Files.readAllLines(Path.of(OPENAI_KEY_FILE)).getFirst();
        } catch (IOException ex) {
            throw new RuntimeException(
                    "Unable to read API Key File " + OPENAI_KEY_FILE, ex);
        }
    }
}
