import java.io.*;
import java.util.*;
import java.util.zip.*;

/**
 * UnZip -- print or unzip a JAR or PKZIP file using JDK1.1 java.util.zip.
 *
 * Final command-line version: extracts files.
 *
 * @author	Ian Darwin, Ian@DarwinSys.com
 *
 */
public class UnZip {
	public static final int LIST = 0, EXTRACT = 1;
	/** Whether we are extracting or just printing TOC */
	int mode = LIST;

	/** The ZipFile that is used to read an archive */
	ZipFile zippy;

	/** The buffer for reading/writing the ZipFile data */
	byte[] b;

	/** Simple main program, construct an UnZipper, process each
	 * .ZIP file from argv[] through that object.
	 */
	public static void main(String argv[]) {
		UnZip u = new UnZip();

		for (int i=0; i<argv.length; i++) {
			if ("-x".equals(argv[i])) {
				u.setMode(EXTRACT);
				continue;
			}
			String candidate = argv[i];
			// System.err.println("Trying path " + candidate);
			if (candidate.endsWith(".zip") ||
				candidate.endsWith(".jar"))
					u.unZip(candidate);
			else System.err.println("Not a zip file? " + candidate);
		}
		System.err.println("All done!");
	}

	/** Construct an UnZip object */
	UnZip() {
		b = new byte[8092];
	}

	/** Set the Mode (list, extract). */
	protected void setMode(int m) {
		if (m == LIST ||
		    m == EXTRACT)
			mode = m;
	}

	/** For a given Zip file, process each entry. */
	public void unZip(String classes) {
		try {
			zippy = new ZipFile(new File(classes));
			Enumeration all = zippy.entries();
			while (all.hasMoreElements()) {
				getFile(((ZipEntry)(all.nextElement())).getName());
			}
		} catch (IOException err) {
			System.err.println("IO Error: " + err);
			return;
		}
	}

	/** Process one file from the zip, given its name.
	 * Either print the name, or create the file on disk.
	 */
	protected void getFile(String zipName) throws IOException {
		if (mode == EXTRACT) {
			// double-check that the file is in the zip
			// if a directory, mkdir it (remember to
			// create intervening subdirectories if needed!)
			if (zipName.endsWith("/")) {
				pavePath(zipName);
				return;
			}
			// Else must be a file; open the file for output
			System.err.println("Creating " + zipName);
			FileOutputStream os = new FileOutputStream(zipName);
			ZipEntry e = zippy.getEntry(zipName);
			InputStream  is = zippy.getInputStream(e);
			int n = 0;
			while ((n = is.read(b)) >0)
				os.write(b, 0, n);
			is.close();
			os.close();
		} else
			// Not extracting, just list
			System.out.println("File " + zipName);
	}

	/** Pave the path to a directory, making all intervening 
	 * directories if necessary.
	 */
	protected void pavePath(String fqdn) {
		System.out.println("Paving " + fqdn + "...");
		new File(fqdn).mkdirs();
	}
}
