package reflection;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.List;
import java.util.StringTokenizer;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

/**
 * <p>
 * APIFormatter reads one or more Zip files, gets all entries from each
 * and, for each entry that ends in ".class", loads it with Class.forName()
 * and hands it off to a doClass(Class c) method declared in a subclass.
 * <br/>TODO<br/>
 * Use GETOPT to control doingStandardClasses, verbosity level, etc.
 * @author	Ian Darwin, Ian@DarwinSys.com
 */
public abstract class APIFormatter {

	/** True if we are doing classpath, so only do java. and javax. */
	protected static boolean doingStandardClasses = true;

	protected int doArgs(String[] argv) throws IOException {
		/** Counter of fields/methods printed. */
		int n = 0;

		// TODO: options
		// -b - process bootclasspath
		// -c - process classpath (default)
		// -s - only process "java." and "javax."

		if (argv.length == 0) {
			// No arguments, look in CLASSPATH
			String s = System.getProperty("java.class.path");
			//  break apart with path sep.
			String pathSep = System.getProperty("path.separator");
			StringTokenizer st = new StringTokenizer(s, pathSep);
			// Process each zip in classpath
			while (st.hasMoreTokens()) {
				String thisFile = st.nextToken();
				System.err.println("Trying path " + thisFile);
				if (thisFile.endsWith(".zip") || thisFile.endsWith(".jar"))
					processOneZip(thisFile);
			}
		} else {
			// We have arguments, process them as zip/jar files
			// doingStandardClasses = false;
			for (int i=0; i<argv.length; i++)
				processOneZip(argv[i]);
		}

		return n;
	}

	/** For each Zip file, for each entry, xref it */
	public void processOneZip(String fileName) throws IOException {
		List<ZipEntry> entries = new ArrayList<ZipEntry>();

		try (ZipFile zipFile = new ZipFile(new File(fileName))) {
			// Empty?

			Enumeration<? extends ZipEntry> all = (Enumeration<? extends ZipEntry>) zipFile.entries();

			// Put the entries into the List for sorting...
			while (all.hasMoreElements()) {
				ZipEntry zipEntry = all.nextElement();
				entries.add(zipEntry);
			}

		} catch (ZipException zz) {
			throw new FileNotFoundException(zz.toString() + fileName);
		}
		
			// Sort the entries (by class name)
			Collections.sort(entries, new Comparator<ZipEntry>() {
				public int compare(ZipEntry o1, ZipEntry o2) {
					return o1.getName().compareTo(o2.getName());
				}

			});

			// Process all the entries in this zip.
			for (ZipEntry zipEntry : entries) {
				String zipName = zipEntry.getName();

				// Ignore package/directory, other odd-ball stuff.
				if (zipEntry.isDirectory()) {
					continue;
				}

				// Ignore anything not a Class
				if (!zipName.endsWith(".class")) {
					continue;
				}

				// If doing CLASSPATH, Ignore com.* which are "internal API".
				// 	if (doingStandardClasses && !zipName.startsWith("java")){
				// 		continue;
				// 	}

				// Convert the zip file entry name, like
				//	java/lang/Math.class
				// to a class name like
				//	java.lang.Math
				String className = zipName.replace('/', '.').
					substring(0, zipName.length() - 6);	// 6 for ".class"

				// Now get the Class object for it.
				Class<?> c = null;
				try {
					c = Class.forName(className);
				} catch (ClassNotFoundException ex) {
					System.err.println("Error: " + ex);
				}

				// Hand it off to the subclass...
				doClass(c);
			}
	}

	/** Template Method to do something useful (e.g.,
	 * format the fields and methods) of one Class.
	 */
	protected abstract void doClass(Class<?> c) throws IOException;
}
