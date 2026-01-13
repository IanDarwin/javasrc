package dir_file;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Get a list of files that should be present, and check
 * if any files are missing from the disk.
 * @author Ian F. Darwin, https://darwinsys.com/
 */
public class CheckFiles {
	public static void main(String[] argv) {
		System.out.println("CheckFiles starting.");
		CheckFiles cf = new CheckFiles();
		try {
			var filesToCheck = cf.getListFromConfigFile();
			var filesThatExist = cf.getListFromDirectory();
			cf.reportMissingFiles(filesToCheck, filesThatExist);
			System.out.println("CheckFiles done.");
		} catch (FileNotFoundException e) {
			System.err.println("Can't open file list file.");
			return;
		} catch (IOException e) {
			System.err.println("Error reading file list");
			return;
		}
	}
	public String FILENAME = "filelist.txt";

	protected List<String> getListFromConfigFile() throws IOException {
		var list= new ArrayList<String>();
		Files.lines(Path.of(FILENAME))
			.forEach(line -> list.add(line));
		return list;
	}

	/** Get list of names from the directory */
	protected List<String> getListFromDirectory() {
		var array = new java.io.File(".").list();
		var list = new ArrayList<String>();
		for (String file : array) {
			list.add(file);
		}
		return list;
	}

	protected void reportMissingFiles(
		List<String> listFromFile, List<String> listFromDir) {

		for (String name : listFromFile) {
			if (!listFromDir.contains(name))
				System.err.println("File " + name + " missing.");
		}
	}
}
