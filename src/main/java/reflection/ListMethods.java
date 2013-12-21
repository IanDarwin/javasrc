package reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * List the Constructors and methods
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
// BEGIN main
public class ListMethods {
	public static void main(String[] argv) throws ClassNotFoundException {
		if (argv.length == 0) {
			System.err.println("Usage: ListMethods className");
			return;
		}
		Class<?> c = Class.forName(argv[0]);
		Constructor<?>[] cons = c.getConstructors();
		printList("Constructors", cons);
		Method[] meths = c.getMethods();
		printList("Methods", meths);
	}
	static void printList(String s, Object[] o) {
		System.out.println("*** " + s + " ***");
		for (int i=0; i<o.length; i++)
			System.out.println(o[i].toString());
	}
}
// END main
