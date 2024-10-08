package dir_file;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Rename "MyCoolDocument" to "mydoc.bak" in Java
 */
// tag::main[]
public class Rename {
	public static void main(String[] argv) throws IOException {
		
		// Construct the Path object. Does NOT create a file on disk!
		final Path p = Path.of("MyCoolDocument"); // The file we will rename
		
		// Setup for the demo: create a new "old" file
		final Path oldName = Files.exists(p) ? p : Files.createFile(p);
		
		// Rename the file to "mydoc.bak"
		// Renaming needs a Path object for the target.
		final Path newName = Path.of("Mydoc.bak");
		Files.deleteIfExists(newName); // In case previous run left it there
		Path p2 = Files.move(oldName, newName);
		System.out.println(p + " renamed to " + p2);

		// What if a directory exists and you try to move a file into it?
		Path target = Path.of("temp");
		if (!Files.isDirectory(target)) {
			Files.createDirectory(target);
		}
		try {
			Files.move(p2, target);
		} catch (java.nio.file.FileAlreadyExistsException ex) {
			System.out.println("Caught expected FileAlreadyExistsException");
		}
		// Cleanup time!
		Files.delete(p2);
		Files.delete(target);
	}
}
// end::main[]
