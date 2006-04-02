package dir_file;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Get a list of files that should be present, and check
 * if any files are missing from the disk.
 * @author Ian F. Darwin, http://www.darwinsys.com/
 * @version $Id$
 */
public class CheckFiles {
	public static void main(String[] argv) {
		CheckFiles cf = new CheckFiles();
		System.out.println("CheckFiles starting.");
		cf.getListFromFile();
		cf.getListFromDirectory();
		cf.reportMissingFiles();
		System.out.println("CheckFiles done.");
	}
	public String FILENAME = "filelist.txt";

	protected ArrayList<String> listFromFile = new ArrayList<String>();
	protected ArrayList<String> listFromDir = new ArrayList<String>();

	protected void getListFromFile() {
		BufferedReader is = null;
		try {
			is = new BufferedReader(new FileReader(FILENAME));
			String line;
			while ((line = is.readLine()) != null)
				listFromFile.add(line);
		} catch (FileNotFoundException e) {
			System.err.println("Can't open file list file.");
			return;
		} catch (IOException e) {
			System.err.println("Error reading file list");
			return;
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				// nothing to do
			}
		}
	}

	/** Get list of names from the directory */
	protected void getListFromDirectory() {
		String[] l = new java.io.File(".").list();
		for (int i=0; i<l.length; i++)
			listFromDir.add(l[i]);
	}

	protected void reportMissingFiles() {
		for (int i=0; i<listFromFile.size(); i++)
			if (!listFromDir.contains(listFromFile.get(i)))
				System.err.println("File " + listFromFile.get(i) + " missing.");
	}
}
