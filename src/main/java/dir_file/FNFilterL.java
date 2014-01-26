package dir_file;

import java.io.*;
import java.util.Arrays;

/**
 * FNFilter - Ls directory lister modified to use FilenameFilter
 * @author Ian Darwin
 */
// BEGIN main
public class FNFilterL {
	public static void main(String argh_my_aching_fingers[]) {

		// Generate the selective list, with a Lambda Expression
		String[] dirs = new java.io.File(".").list(
			(dir, s) -> {
				return (s.endsWith(".java") || s.endsWith(".class") || s.endsWith(".jar"))
			}
		);
		Arrays.sort(dirs);		// Sort it (Data Structuring chapter))
		for (String d : dirs) {
			System.out.println(d);	// Print the list
		}
	}
}
// END main
