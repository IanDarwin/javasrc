package dir_file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Rename a file in Java
 * @author Ian F. Darwin, https://darwinsys.com/
 */
// tag::main[]
public class Rename {
	public static void main(String[] argv) throws IOException {
		
		// Construct the Path object. Does NOT create a file on disk!
		final Path p = Path.of("MyCoolDocument"); // The file we will rename
		
		// Setup for the demo: create a new "old" file
		final Path oldName = Files.exists(p) ? p : Files.createFile(p);
		
		// Rename the backup file to "mydoc.bak"
		// Renaming requires a Path object for the target.
		final Path newName = Path.of("mydoc.bak");
		Files.deleteIfExists(newName); // In case previous run left it there
		Path p2 = Files.move(oldName, newName);
		System.out.println(p + " renamed to " + p2);
	}
}
// end::main[]
