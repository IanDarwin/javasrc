package reflection.cooklet;

/**
 * This is the part of the Cookies application that loads
 * the user-defined subclass.
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
// BEGIN main
public class Cookies {
	public static void main(String[] argv) {
		System.out.println("Cookies Application Version 0.0");
		Cooklet cooklet = null;
		String cookletClassName = argv[0];
		try {
			Class<Cooklet> cookletClass =
				(Class<Cooklet>) Class.forName(cookletClassName);
			cooklet = cookletClass.newInstance();
		} catch (Exception e) {
			System.err.println("Error " + cookletClassName + e);
		}
		cooklet.initialize();
		cooklet.work();
		cooklet.terminate();
	}
}
// END main
