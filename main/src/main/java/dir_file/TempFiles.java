package dir_file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Work with temporary files in Java.
 * @author Ian F. Darwin, https://darwinsys.com/
 */
// tag::main[]
public class TempFiles {
	public static void main(String[] argv) throws IOException {

		// 1. Making an existing file temporary
		// Construct a File object for the backup created by editing
		// this source file. The file probably already exists.
		// My editor creates backups by putting ~ at the end of the name.
		File bkup = new File("Rename.java~");
		// Arrange to have it deleted when the program ends.
		bkup.deleteOnExit();

		// 2. Create a new temporary file.

		// Make a file object for foo.tmp, in the default temp directory
		Path tmp = Files.createTempFile("foo", "tmp");
		// Report on the filename that it made up for us.
		System.out.println("Your temp file is " + tmp.normalize());
		// Arrange for it to be deleted at exit.
		tmp.toFile().deleteOnExit();
		// Now do something with the temporary file, without having to
		// worry about deleting it later.
		writeDataInTemp(tmp);
	}

	public static void writeDataInTemp(Path tempFile) throws IOException {
		// This version is dummy. Use your imagination.
		Files.writeString(tempFile, "This is a temp file");
	}
}
// end::main[]
