package dir_file;

import java.io.*;

/**
 * Delete a file from within Java, with error handling.
 * @author Ian F. Darwin, http://www.darwinsys.com/
 * @version $Id$
 */
public class Delete2 {

	public static void main(String[] argv) {
		for (int i=0; i<argv.length; i++)
			delete(argv[i]);
	}

	public static void delete(String fileName) {
		try {
			// Construct a File object for the file to be deleted.
			File target = new File(fileName);

			if (!target.exists()) {
				System.err.println("File " + fileName + 
					" not present to begin with!");
				return;
			}

			// Quick, now, delete it immediately:
			if (target.delete())
				System.err.println("** Deleted " + fileName + " **");
			else
				System.err.println("Failed to delete " + fileName);
		} catch (SecurityException e) {	
			System.err.println("Unable to delete " + fileName +
				"(" + e.getMessage() + ")");
		}
	}
}
