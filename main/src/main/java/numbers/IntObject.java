package numbers;

/**
 * IntObject -- convert between int and Integer (needed pre-1.5)
 */
public class IntObject {
	public static void main(String[] args) {
		// tag::main[]
		// int to Integer
		Integer wrapped = Integer.valueOf(42);
		System.out.println(wrapped.toString());		// or just "wrapped"
		
		// Integer to int
		int primitive = i1.intValue();
		System.out.println(primitive);
		// end::main[]
	}
}
