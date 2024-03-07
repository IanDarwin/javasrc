package reflection;

import java.lang.reflect.Method;

// tag::main[]
/**
 * Get a given method, and invoke it.
 * @author Ian F. Darwin, https://darwinsys.com/
 */
public class GetAndInvokeMethod {

	/** This class is just here to give us something to work on,
	 * with a println() call that will prove we got into it.
	 */
	static class X {
		public void work(int i, String s) {
			System.out.printf("Called: i=%d, s=%s%n", i, s);
		}
		// The main code 
		public static void main(String[] args) {
			System.out.println("Main.args = " + args);
		}
	}

	public static void main(String[] argv) {
		try {
			Class<?> clX = X.class; // or Class.forName("X");

			// To find a method we need the array of matching Class types.
			Class<?>[] argTypes = {
				int.class,
				String.class
			};

			// Now find a Method object for the given method.
			Method worker = clX.getMethod("work", argTypes);

			// To INVOKE the method, we need the invocation
			// arguments as an Object array.
			Object[] theData = {
				42,
				"Chocolate Chips"
			};

			// The obvious last step: invoke the method.
			// First arg is an instance, null if static method
			worker.invoke(new X(), theData);

			System.out.println("First Invoke Done");

			// Same deal but for main, a static method taking String[]:
			final Method m = clX.getMethod("main", String[].class);
			final Object[] args = new Object[1];
			args[0] = "Hello World Of Java".split(" ");
			// e.g., args[0] is itself an array
			m.invoke(null, args);
			System.out.println("Second Invoke Done");

		} catch (Exception e) {
			System.err.println("Invoke() failed: " + e);
			e.printStackTrace();
		}
	}
}
// end::main[]
