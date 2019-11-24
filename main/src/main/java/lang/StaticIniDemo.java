package lang;

/**
 * Show static initializers. These are run when the class is loaded.
 * If you run the main program here, you will see the static
 * initializer before the "About to load class", since the class
 * is loaded before main gets run. If you copy the guts of main into
 * another class, the messages will come out in the "expected" order.
 *
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
public class StaticIniDemo {

	/** A static initializer - called when class is loaded. */
	static {
		System.out.println("In static initializer");
	}

	/** A constructor -- called when object is instantiated. */
	public StaticIniDemo() {
		System.out.println("In Constructor");
	}

	public static void main(String[] a) {
		try {
			System.err.println("About to load class");
			Class<?> c = Class.forName("StaticIniDemo");
			System.err.println("About to construct instance");
			Object sd = c.newInstance();
			System.err.println("Object is " + sd);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
