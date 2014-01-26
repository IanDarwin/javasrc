package dir_file;

import java.io.File;
import java.io.IOException;

/**
 * Delete a file from within Java
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
// BEGIN main
public class Delete {
	public static void main(String[] argv) throws IOException {

		// Construct a File object for the backup created by editing
		// this source file. The file probably already exists.
		// Some text editors create backups by putting ~ at end of filename.
		File bkup = new File("Delete.java~");
		// Now, delete it:
		bkup.delete();
	}
}
// END main
