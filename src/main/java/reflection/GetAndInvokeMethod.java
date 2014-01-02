package reflection;

import java.lang.reflect.Method;

// BEGIN main
/**
 * Get a given method, and invoke it.
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
public class GetAndInvokeMethod {

	/** This class is just here to give us something to work on,
	 * with a println() call that will prove we got into it.
	 */
	static class X {
		public void work(int i, String s) {
			System.out.printf("Called: i=%d, s=%s%n", i, s);
		}
		// The main code does not use this overload.
		public void work(int i) {
			System.out.println("Unexpected call!");
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
			// arguments, as an Object array.
			Object[] theData = {
				42,
				"Chocolate Chips"
			};

			// The obvious last step: invoke the method.
			// First arg is an instance, null if static method
			worker.invoke(new X(), theData);

		} catch (Exception e) {
			System.err.println("Invoke() failed: " + e);
		}
	}
}
// END main
