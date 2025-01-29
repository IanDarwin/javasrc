package strings;

public class TextBlockDemo {

	final static String MESSAGE_OLD = 
			"This string can be\n" +
			"as long as my design,\n" +
			"and I quotes I don't need\n" +
			"nor pluses on each line.\n";
	
	final static String MESSAGE_NEW = """
			This string can be
			as long as my design,
			and I quotes I don't need
			nor pluses on each line.
			""";
	
	public static void main(String[] args) {
		System.out.println("Old way");
		System.out.println(MESSAGE_OLD);
		System.out.println("New way");
		System.out.println(MESSAGE_NEW);
		System.out.println(MESSAGE_NEW.equals(MESSAGE_OLD));
	}

}
