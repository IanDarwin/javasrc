package reflection;

import java.awt.Frame;

/** Demonstrate "class.forName" to create an instance of an object. */
public class ClassForName {
	public static void main(String[] av) {
		Class<?>  c = null;
		Object o = null;
		try {
			// Load the class, return a Class for it
			c = Class.forName("java.awt.Frame");
			// Construct an object, as if new Type()
			o = c.newInstance();
		} catch (Exception e) {
			System.err.println("That didn't work. " +
				" Try something else" + e);
		}
		if (o != null && o instanceof Frame) {
			Frame f = (Frame)o;
			f.setTitle("Testing");
			f.setVisible(true);
		} else throw new 
			IllegalArgumentException("Huh? What gives?");
	}
}
