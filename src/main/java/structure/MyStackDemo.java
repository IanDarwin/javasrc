package structure50;

public class MyStackDemo {
	public static void main(String[] args) {
		MyStack<String> ms1 = new MyStack<String>();
		ms1.push("billg");
		ms1.push("scottm");

		while (ms1.hasNext()) {
			String name = ms1.pop();
			System.out.println(name);
		}

		// Old way of using Collections: not type safe.
		MyStack ms2 = new MyStack();
		ms2.push("billg");               // EXPECT WARNING
		ms2.push("scottm");              // EXPECT WARNING
		ms2.push(new java.util.Date());  // EXPECT WARNING
		
		// Show that it is broken 
		try {
			String bad = (String)ms2.pop();
			System.err.println("Didn't get expected exception!");
		} catch (ClassCastException ex) {
			System.out.println("Did get expected exception.");
		}

		// Removed the brokenness, print rest of it.
		while (ms2.hasNext()) {
			String name = (String)ms2.pop();
			System.out.println(name);
		}
	}
}
