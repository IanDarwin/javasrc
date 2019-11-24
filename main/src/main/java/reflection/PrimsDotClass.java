package reflection;

import java.lang.reflect.Method;

/**
 * Show that you can, in fact, take the .class of a primitive.
 */
public class PrimsDotClass {

	public static void main(String[] args) {
		Class<?> c = int.class;
		System.out.println(c.getName());
		Method[] methods = c.getMethods();
		System.out.println(c.getName() + " has " + methods.length + " methods");
	}
}
