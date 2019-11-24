package strings;

public class Equality {
	public static void main(String[] args) { 
		new Equality().run();
	}

	public void run() {
		String x = "hello";

		String y = Equality2.getString();
	
		// Assuming Equality2 uses a String constant, this prints true,true
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
