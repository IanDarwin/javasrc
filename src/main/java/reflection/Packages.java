package reflection;

/**
 * Show the Packages. Requires JDK1.2.
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
public class Packages {
	public static void main(String[] argv) {
		// BEGIN main
		java.lang.Package[] all = java.lang.Package.getPackages();
		for (int i=0; i<all.length; i++)
			System.out.println(all[i]);
		// END main
	}
}
