import java.io.*;

/**
 * This program shows how to use getSerialVersionUID for a class that
 * isn't in a package and thus not findable to the normal "serialver" tool.
 *
 * @author Ian Darwin, ian@darwinsys.com
 */
public class GetSerVersUID {
	static final String ident = "$Id$";
	public static void main(String av[]) throws Exception {

		// First we construct a Class object for the given class
		Class cl = Class.forName("Candidate");

		// Then an ObjectStreamClass for the given class
		ObjectStreamClass ocl = ObjectStreamClass.lookup(cl);

		// And use the ObjectStreamClass to get the Class'
		// unique SerialVersionUID
		System.out.println("For class " + cl);
		System.out.println("static final long serialVersionUID = " +
			ocl.getSerialVersionUID() + "L;"); // must be long

		// And just for reference...
		System.out.println("(Must range from " + Long.MIN_VALUE +
			" to " + Long.MAX_VALUE + ".)");
	}
}
