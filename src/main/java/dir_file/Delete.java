import java.io.*;

/**
 * Delete a file from within Java
 * @author Ian F. Darwin, ian@darwinsys.com
 * @version $Id$
 */
public class Rename {
	public static void main(String argv[]) throws IOException {

		// Construct a File object for the backup created by editing
		// this source file. The file probably already exists.
		// My editor creates backups by putting ~ at the end of the name.
		File bkup = new File("Rename.java~");
		// Quick, now, delete it immediately:
		bkup.delete();
	}
}
