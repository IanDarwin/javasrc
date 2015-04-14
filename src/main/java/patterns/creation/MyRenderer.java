package patterns.creation;

public class MyRenderer implements MessageRenderer {
	public void renderMessage(String message) {
		System.out.println(message);
	}

}
