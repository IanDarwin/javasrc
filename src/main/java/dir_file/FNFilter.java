import java.io.*;

/**
 * FNFilter - Ls directory lister modified to use FilenameFilter
 * @author Ian Darwin
 * @version $Id$
 */
public class FNFilter {
	public static void main(String argh_my_aching_fingers[]) {
		// Generate the selective list, with a one-use File object.
		String[] dir = new java.io.File(".").list(new OnlyJava());
		java.util.Arrays.sort(dir);		// Sort it (Data Structuring chapter))
		for (int i=0; i<dir.length; i++)
			System.out.println(dir[i]);	// Print the list
	}
}

/** This class implements the FilenameFilter interface.
 * The Accept method returns true for .java, .class and .jar files.
 */
class OnlyJava implements FilenameFilter {
	public boolean accept(File dir, String s) {
		if (s.endsWith(".java") || s.endsWith(".class") || s.endsWith(".jar"))
			return true;
		// others: projects, ... ?
		return false;
	}
}
