/**
 * A trivial class to show Java Native Interface 1.1 usage from Java.
 * @version	$Id$
 */
public class HelloWorld {
	int myNumber = 42;        // used to show argument passing

	// declare native class
	public native void displayHelloWorld();

	// Application main, call its display method
	public static void main(String args[]) {
		System.out.println("HelloWorld starting...");
		HelloWorld hw = new HelloWorld();
		hw.displayHelloWorld();		// call the native function
		System.out.println("Back in Java, \"myNumber\" now " + hw.myNumber);
	}

	// Static code blocks are executed once, when class file is loaded
	static {
		System.load("libhello.so");
	}
}
