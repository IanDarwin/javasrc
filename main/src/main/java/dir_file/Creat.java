package dir_file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

// tag::main[]
/** Create one or more files by name.
 ** The final "e" is omitted in homage to the underlying UNIX system call. */
public class Creat {
	public static void main(String[] argv) throws IOException {

		// Ensure that a filename (or something) was given in argv[0]
		if (argv.length == 0) {
			System.err.println("Usage: Creat filename");
			System.exit(1);
		}

		for (String arg : argv) {
			// Constructing a Path object doesn't affect the disk, but
			// the createNewFile() method does.
			final Path created = Files.createFile(Path.of(arg));
		}
	}
}
// end::main[]
