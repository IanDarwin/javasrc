package structure;

// tag::main[]
public class InterfaceAdditions implements Demo {
	void main() { // Instance main in a named class
		// Call public interface method in implementing class
		System.out.println(getStatus());
		// Call static interface method
		System.out.println(Demo.getPresent());
		// Following won't compile, private method
		// var s = innerStatus(); 
	}
}

interface Demo {

	default String getStatus() { return innerStatus(); }

	static String getPresent() { return "Freebie"; }

	private String innerStatus() { return "OK"; }
}

class InterfaceChangesII implements Demo {
	// Forllowing is a valid override, but must be public
	public String getStatus() { return "Unknown"; }
}
// end::main[]
