/**
 * A trivial class to show native method usage from Java.
 */
public class HelloWorld {
        int MyNumber = 42;        // used to show argument passing

        // declare native class
        public native void displayHelloWorld();

        // Application main, call its display method
        public static void main(String[] args) {
		System.out.println("HelloWorld starting...");
		HelloWorld hw = new HelloWorld();
                hw.displayHelloWorld();		// call the native function
                System.out.println("Value of MyNumber now " + hw.MyNumber);
        }

        // Static code blocks are executed once, when Class file is loaded
        static {
                System.load("libhello.so");
        }
}

