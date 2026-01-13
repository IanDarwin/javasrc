package dir_file;

import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;

/**
 * Delete a file from within Java
 * @author Ian F. Darwin, https://darwinsys.com/
 */
// tag::main[]
public class Delete {
	public static void main(String[] argv) throws IOException {

		String fileToDelete = "Delete.java~";
		System.out.println("Deleting " + fileToDelete);
		// Construct a File object for the backup created by editing
		// this source file. The file probably already exists.
		// Some text editors create backups by putting ~ at end of filename.
		Path bkup = Path.of(fileToDelete);
		// Now, delete it:
		if (Files.exists(bkup)) {
			Files.delete(bkup);
			System.out.println("Done");
		} else {
			System.out.println("File not found");
		}
	}
}
// end::main[]
