import java.io.*;
import java.util.*;

/**
 * Get a list of files, and check if any files are missing.
 * @author Ian F. Darwin, ian@darwinsys.com
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

	protected ArrayList listFromFile;
	protected ArrayList listFromDir = new ArrayList();

	protected void getListFromFile() {
		listFromFile = new ArrayList();
		BufferedReader is;
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
		}
	}

	/** Get list of names from the directory */
	protected void getListFromDirectory() {
		listFromDir = new ArrayList();
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
