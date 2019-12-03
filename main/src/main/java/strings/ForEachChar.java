package strings;

// tag::main[]
public class ForEachChar {
	public static void main(String[] args) {
		String s = "Hello world";
		// for (char ch : s) {		 // Does not compile
		//		System.out.println(ch);
		// 
		for (char ch : s.toCharArray()) {
			System.out.println(ch);
		}
	}
}
// end::main[]
