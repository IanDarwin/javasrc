package dir_file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

// tag::main[]
/** Create file(s) by name. Final "e" omitted in homage to UNIX system call. */
public class Creat {
	public static void main(String[] argv) throws IOException {

		// Ensure that a filename (or something) was given in argv[0]
		if (argv.length == 0) {
			throw new IllegalArgumentException("Missing args. Usage: Creat filename");
		}

		for (String arg : argv) {
			// Constructing a Path object doesn't affect the disk, but
			// the Files.createFile() method does.
			final Path p = Path.of(arg);
			final Path created = Files.createFile(p);
			System.out.println(created);
		}
	}
}
// end::main[]
