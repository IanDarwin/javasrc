package patterns.creation;

public class MyRenderer implements MessageRenderer {
	String message = "Hello";
	public void renderMessage() {
		System.out.println(message);
	}

}
