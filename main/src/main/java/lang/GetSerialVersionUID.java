package lang;

import java.io.Serializable;
import java.io.ObjectStreamClass;

/**
 * This program shows how to use getSerialVersionUID for a class. Because it
 * uses Class.forName(), it will even operate upon a class that
 * isn't in a package and thus not findable to the normal "serialver" tool.
 *
 * @author Ian Darwin, http://www.darwinsys.com/
 */
public class GetSerialVersionUID implements Serializable {

	private static final long serialVersionUID = 1803149669137612064L;

	public static void main(String[] av) throws Exception {

		// First construct a Class object for the given class
		Class<?> cl = Class.forName(av.length == 0 ? "lang.GetSerVersUID" : av[0]);

		// Then an ObjectStreamClass for the given class
		ObjectStreamClass ocl = ObjectStreamClass.lookupAny(cl);

		// Use the ObjectStreamClass to get the Class' unique SerialVersionUID
		long uid = ocl.getSerialVersionUID();
		if (uid == 0L) {
			System.out.println("Class " + cl + " is not Serializable!");
		} else {
			System.out.println("// For class " + cl.getName());
			System.out.println(
				"static final long serialVersionUID = " + uid + "L;");
		}
	}
}
