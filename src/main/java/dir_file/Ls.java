package dir_file;

/** Simple directory lister.
 * @author Ian Darwin, http://www.darwinsys.com/
 */
// BEGIN main
public class Ls {
	public static void main(String args[]) {
		String[] dirs = new java.io.File(".").list(); // Get list of names
		java.util.Arrays.sort(dirs);		// Sort it (see <<javacook-structure-SECT-8>>)
		for (String dir : dirs) {
            System.out.println(dir);    	// Print the list
		}
	}
}
// END main
