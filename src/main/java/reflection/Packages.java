/**
 * Show the Packages. Requires JDK1.2.
 * @author Ian F. Darwin, ian@darwinsys.com
 * @version $Id$
 */
public class Packages {
	public static void main(String argv[]) {
		//+
		Packages[] all = java.lang.Package.getPackages();
		System.out.println("all = " + all);
		//-
	}
}
