package reflection;

import java.util.Random;

/** Demonstrate "class.forName" to create an instance of an object. */
public class ClassForName {
	public static void main(String[] av) {
		Class<?>  c = null;
		Object o = null;
		try {
			// Load the class, return a Class for it
			c = Class.forName("java.util.Random");
			// Construct an object, as if new Type()
			o = c.getConstructor().newInstance();
		} catch (Exception e) {
			System.err.println("That didn't work. " +
				" Try something else" + e);
		}
		if (o != null && o instanceof Random r) {
			System.out.println("R produced this random # " + r.nextDouble());
		} else throw new 
			IllegalArgumentException("Huh? What gives? Not the right type: " + o);
	}
}
