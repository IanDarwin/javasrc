package strings;

// tag::main[]
public class ForEachChar {
	public static void main(String[] args) {
		String mesg = "Hello world";

		// Does not compile, Strings are not iterable
		// for (char ch : mesg) {
		//		System.out.println(ch);
		// 

		System.out.println("Using toCharArray:");
		for (char ch : mesg.toCharArray()) {
			System.out.println(ch);
		}

		System.out.println("Using Streams:");
		mesg.chars().forEach(c -> System.out.println((char)c));
	}
}
// end::main[]
