package io;

/**
 * DON'T DO THIS. THIS IS BAD CODE.
 * @author Ian F. Darwin, https://darwinsys.com/
 */
public class BadNewline {
	// tag::main[]
	String myName;
	public static void main(String[] argv) {
		BadNewline jack = new BadNewline("Jack Adolphus Schmidt, III");
		System.out.println(jack);
	}
	/**
	 * DON'T DO THIS. THIS IS BAD CODE.
	 */
	public String toString() {
		return super.toString() + "\n" + myName;
	}

	// The obvious Constructor is not shown for brevity; it's in the code
	// end::main[]
	/* Constructor */
	public BadNewline(String s) {
		myName = s;
	}
}
