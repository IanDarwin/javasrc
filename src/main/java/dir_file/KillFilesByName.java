package dir_file;

import java.io.*;
/**
 * DANGEROUS Program to remove files matching a name in a directory
 * @author	Ian Darwin, http://www.darwinsys.com/
 */
public class KillFilesByName {
	public static void main(String[] argv) {
		if (argv.length != 2) {
			System.err.println("usage: KillFilesByName dirname pattern");
			System.exit(1);
		}

		File dir = new File(argv[0]);
		if (!dir.exists()) {
			System.out.println(argv[0] + " does not exist");
			return;
		}
		String patt = argv[1];

		String[] info = dir.list();
		for (int i=0; i<info.length; i++) {
			File n = new File(argv[0] + File.separator + info[i]);
			if (!n.isFile()) {	// skip ., .., other directories, etc.
				continue;
			}
			if (info[i].indexOf(patt) == -1) {	// name doesn't match
				continue;
			}
			System.out.println("removing " + n.getPath());
			if (!n.delete())
				System.err.println("Couldn't remove " + n.getPath());
		}
	}
}
