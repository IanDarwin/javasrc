package dir_file;

import java.io.File;
import java.io.IOException;

/**
 * Create one or more files by name.
 * The final "e" is omitted in homage to the underlying UNIX system call.
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
public class Creat {
	public static void main(String[] argv) throws IOException {

		// Ensure that a filename (or something) was given in argv[0]
		if (argv.length == 0) {
			System.err.println("Usage: Creat filename");
			System.exit(1);
		}

		for (int i = 0; i< argv.length; i++) {
			// Constructing a File object doesn't affect the disk, but
			// the createNewFile() method does.
			new File(argv[i]).createNewFile();
		}
	}
}
