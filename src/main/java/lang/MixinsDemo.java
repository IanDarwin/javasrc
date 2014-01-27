package lang;

// BEGIN main
interface Bar {
	default String filter(String s) {
		return "Filtered " + s;
	}
}

interface Foo {
	default String convolve(String s) {
		return "Convolved " + s;
	}
}

public class MixinsDemo implements Foo, Bar{

	public static void main(String[] args) {
		String input = args.length > 0 ? args[0] : "Hello";
		String output = new MixinsDemo().process(input);
		System.out.println(output);
	}

	private String process(String s) {
		return filter(convolve(s)); // methods mixed in!
	}
}
// END main
