package lang;

/**
 * Show static initializers. These are run when the class is loaded.
 *
 * Uses Reflection instead of calling constructor just to show ordering.
 *
 * @author Ian F. Darwin, https://darwinsys.com/
 */
public class StaticIniDemo {

	public static class InnerDemo {

		/** A static initializer - called when class is loaded. */
		static {
			System.out.println("InnerDemo: static initializer");
		}

		/** A constructor -- called when object is instantiated. */
		public InnerDemo() {
			System.out.println("InnerDemo: Constructor");
		}
	}

	public static void main(String[] a) {
		try {
			System.err.println("Main: About to load class");
			Class<?> c = Class.forName("lang.StaticIniDemo$InnerDemo");
			System.err.println("Main: About to construct instance of " + c);
			Object sd = c.getConstructor().newInstance();
			System.err.println("Object is " + sd);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
