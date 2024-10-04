package strings;

/**
 * Demonstration of "interning", that is, Java makes and caches
 * unique string literals, to save memory.
 * The == operator is true if both arguments refer to the same
 * physical object in memory, which likely means they were interned.
 */
public class Equality {
	void main(){ 
		String x = "hello";

		// Two string literals in same class, must print true,true
		compare(x, "hello");

		String y = Equality2.getString();
	
		// Since Equality2 uses a String constant, this prints true,true
		compare(x, y);

		// A "new String" is uniquely created, so this prints false, true
		compare(x, new String(y));

		// The substring operator creates a new String, prints false, true
		compare(x, "hello world".substring(0, 5));

		// The intern() operator returns a string from the pool of unique
		// strings, so this should print true, true.
		compare(x, new String(x).intern());
	}

	public void compare(String s1, String s2) {

		System.out.print("==:       ");
		System.out.println(s1 == s2);
		System.out.println(".equals():" + s1.equals(s2));
		System.out.println(); 
	}
}

class Equality2 {
	public static String getString() {
		return "hello";
	}
}
