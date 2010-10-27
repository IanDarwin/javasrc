package patterns.creation;

public class MyOtherRenderer implements MessageRenderer {
	String message = "Hello";
	public void renderMessage() {
		System.out.println(message);
	}

}
