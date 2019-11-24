package lang;

/** Simple demo of exceptions */
public class ExceptionDemo {

	public static void main(String[] argv) {
		new ExceptionDemo().doTheWork();
	}

	/** This method demonstrates calling a method that might throw
	 * an exception, and catching the resulting exception.
	 */
	public void doTheWork() {
		Object o = null;

		for (int i=0; i<5; i++) {
			try {
				o = makeObj(i);
			} catch (IllegalArgumentException e) {
				System.err.println("Error: (" + e.getMessage() + ").");
				return;		// cut off println below if makeObj failed.
			}
			System.out.println(o);	// process the created object in some way
		}
	}

	/** Model of a method that creates and returns an object.
	 * This method is really here to show how you throw exceptions.
	 * @exception	IllegalArgumentException	if called with value 1.
	 */
	public Object makeObj(int type) throws IllegalArgumentException {
		if (type == 1)	// detects an error...
			throw new IllegalArgumentException("Don't like type " + type);
		return new Object();
	}
}
