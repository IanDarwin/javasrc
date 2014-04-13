package dir_file;

import java.io.File;
import java.util.Arrays;

/**
 * FNFilter - Ls directory lister with a Lambda FilenameFilter
 * @author Ian Darwin
 */
public class FNFilterL {
	public static void main(String args[]) {

		String dirName = args.length > 0 ? args[0] : ".";
		if (!new File(dirName).exists()) {
			System.err.printf("File %s does not exist", dirName);
			System.exit(1);
		}

		// BEGIN main
		// Generate the selective list, with a Lambda Expression
		String[] dirs = new java.io.File(dirName).list(
			(dir, s) -> {
				return s.endsWith(".java") ||
					s.endsWith(".class") ||
					s.endsWith(".jar");
			}
		);
		Arrays.sort(dirs);		// Sort it (see Data Structuring chapter))
		for (String d : dirs) {
			System.out.println(d);	// Print the list
		}
		// END main
	}
}
