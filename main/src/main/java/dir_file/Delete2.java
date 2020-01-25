package dir_file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Delete a file from within Java, with error handling.
 * Setup: On *Nix:
 touch a; mkdir b; mkdir c; touch c/d
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
// tag::main[]
public class Delete2 {

	static boolean hard = false; // True for delete, false for deleteIfExists

	public static void main(String[] argv) {
		for (String arg : argv) {
			if ("-h".equals(arg)) {
				hard = true;
				continue;
			}
			delete(arg);
		}
	}

	public static void delete(String fileName) {
		// Construct a File object for the file to be deleted.
		final Path target = Path.of(fileName);

		// Now, delete it:
		if (hard) {
			try {
				System.out.print("Using Files.delete(): ");
				Files.delete(target);
				System.err.println("** Deleted " + fileName + " **");
			} catch (IOException e) {
				System.out.println("Deleting " + fileName + " threw " + e);
			}
		} else {
			try {
				System.out.print("Using deleteIfExists(): ");
				if (Files.deleteIfExists(target)) {
					System.out.println("** Deleted " + fileName + " **");
				} else {
					System.out.println(
						"Deleting " + fileName + " returned false.");
				}
			} catch (IOException e) {	
				System.out.println("Deleting " + fileName + " threw " + e);
			}
		}
	}
}
// end::main[]
