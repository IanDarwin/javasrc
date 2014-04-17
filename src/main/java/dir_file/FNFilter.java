package dir_file;

import java.io.*;
import java.util.Arrays;

/**
 * FNFilter - Ls directory lister modified to use FilenameFilter
 * @author Ian Darwin
 */
// BEGIN main
public class FNFilter {
	public static void main(String argh_my_aching_fingers[]) {

		// Generate the selective list, with a one-use File object.
		String[] dirs = new java.io.File(".").list(new OnlyJava());
		Arrays.sort(dirs);		// Sort it (Data Structuring chapter))
		for (String d : dirs) {
			System.out.println(d);	// Print the list
		}
	}

	/** This class implements the FilenameFilter interface.
	 * The Accept method returns true for .java, .class and .jar files.
	 */
	private static class OnlyJava implements FilenameFilter {
		public boolean accept(File dir, String s) {
			if (s.endsWith(".java") ||
				s.endsWith(".class") ||
				s.endsWith(".jar")) {

				return true;
			}
			// others: projects, ... ?
			return false;
		}
	}
}
// END main
