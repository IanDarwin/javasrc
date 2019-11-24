package dir_file;

import java.io.*;

/**
 * FNFilter - directory lister using FilenameFilter
 * @author Ian Darwin
 */

public class FNFilter2 implements FilenameFilter {

	public static void main(String[] av) {
		FNFilter2 ff = new FNFilter2();
		ff.process(".");
	}

	public void process(String dir) {
		String objects[] = (new File(dir)).list(this);

		for (String f : objects) {
			System.out.println(f);
		}
	}

	public boolean accept(File dir, String s) {
		if (s.endsWith(".java"))
			return true;
		// others?
		return false;
	}
}
