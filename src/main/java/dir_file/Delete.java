package dir_file;

import java.io.File;
import java.io.IOException;

/**
 * Delete a file from within Java
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
public class Delete {
	public static void main(String[] argv) throws IOException {

		// Construct a File object for the backup created by editing
		// this source file. The file probably already exists.
		// My editor creates backups by putting ~ at the end of the name.
		File bkup = new File("Delete.java~");
		// Now, delete it:
		bkup.delete();
	}
}
