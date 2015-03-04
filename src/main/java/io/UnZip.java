package io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * UnZip -- print or unzip a JAR or PKZIP file using java.util.zip.
 * Command-line version: extracts files.
 * @author	Ian Darwin, Ian@DarwinSys.com
 */
// BEGIN main
public class UnZip {
	/** Constants for mode listing or mode extracting. */
	public static enum Mode {
		LIST, 
		EXTRACT;
	}
	/** Whether we are extracting or just printing TOC */
	protected Mode mode = Mode.LIST;

	/** The ZipFile that is used to read an archive */
	protected ZipFile zippy;

	/** The buffer for reading/writing the ZipFile data */
	protected byte[] b = new byte[8092];

	/** Simple main program, construct an UnZipper, process each
	 * .ZIP file from argv[] through that object.
	 */
	public static void main(String[] argv) {
		UnZip u = new UnZip();

		for (int i=0; i<argv.length; i++) {
			if ("-x".equals(argv[i])) {
				u.setMode(Mode.EXTRACT);
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

	/** Set the Mode (list, extract). */
	protected void setMode(Mode m) {
		mode = m;
	}

	/** Cache of paths we've mkdir()ed. */
	protected SortedSet<String> dirsMade;

	/** For a given Zip file, process each entry. */
	public void unZip(String fileName) {
		dirsMade = new TreeSet<String>();
		try {
			zippy = new ZipFile(fileName);
			@SuppressWarnings("unchecked")
			Enumeration<ZipEntry> all = (Enumeration<ZipEntry>) zippy.entries();
			while (all.hasMoreElements()) {
				getFile((ZipEntry)all.nextElement());
			}
		} catch (IOException err) {
			System.err.println("IO Error: " + err);
			return;
		}
	}

	protected boolean warnedMkDir = false;

	/** Process one file from the zip, given its name.
	 * Either print the name, or create the file on disk.
	 */
	protected void getFile(ZipEntry e) throws IOException {
		String zipName = e.getName();
		switch (mode) {
		case EXTRACT:
			if (zipName.startsWith("/")) {
				if (!warnedMkDir)
					System.out.println("Ignoring absolute paths");
				warnedMkDir = true;
				zipName = zipName.substring(1);
			}
			// if a directory, just return. We mkdir for every file,
			// since some widely used Zip creators don't put out
			// any directory entries, or put them in the wrong place.
			if (zipName.endsWith("/")) {
				return;
			}
			// Else must be a file; open the file for output
			// Get the directory part.
			int ix = zipName.lastIndexOf('/');
			if (ix > 0) {
				String dirName = zipName.substring(0, ix);
				if (!dirsMade.contains(dirName)) {
					File d = new File(dirName);
					// If it already exists as a dir, don't do anything
					if (!(d.exists() && d.isDirectory())) {
						// Try to create the directory, warn if it fails
						System.out.println("Creating Directory: " + dirName);
						if (!d.mkdirs()) {
							System.err.println(
							"Warning: unable to mkdir " + dirName);
						}
						dirsMade.add(dirName);
					}
				}
			}
			System.err.println("Creating " + zipName);
			FileOutputStream os = new FileOutputStream(zipName);
			InputStream  is = zippy.getInputStream(e);
			int n = 0;
			while ((n = is.read(b)) >0)
				os.write(b, 0, n);
			is.close();
			os.close();
			break;
		case LIST:
			// Not extracting, just list
			if (e.isDirectory()) {
				System.out.println("Directory " + zipName);
			} else {
				System.out.println("File " + zipName);
			}
			break;
		default:
			throw new IllegalStateException("mode value (" + mode + ") bad");
		}
	}
}
// END main
