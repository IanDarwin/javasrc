import java.io.*;
import java.util.*;
import java.util.zip.*;
import java.lang.reflect.*;

/**
 * APIFormatter reads one or more Zip files, gets all entries from each
 * and, for each entry that ends in ".class", loads it with Class.forName()
 * and hands it off to a printClass(Class c) method declared in a subclass.
 * XXX TODO - use a ClassLoader so user doesn't have to add to CLASSPATH.
 * @author	Ian Darwin, Ian@DarwinSys.com
 * @version	$Id$
 */
public abstract class APIFormatter {

	/** True if we are doing classpath, so only do java. and javax. */
	protected static boolean doingStandardClasses = true;
	
	/** Simple main program, construct self, process each .ZIP file
	 * found in CLASSPATH or in argv.
	 */
	public static void main(String[] argv) {
		CrossRef xref = new CrossRef();
		xref.doArgs(argv);
	}

	protected int doArgs(String[] argv) {
		/** Counter of fields/methods printed. */
		int n = 0;

		if (argv.length == 0) {
			// No arguments, look in CLASSPATH
			String s = System.getProperties().getProperty("java.class.path");
			//  break apart with path sep.
			String pathSep = System.getProperties().
				getProperty("path.separator");
			StringTokenizer st = new StringTokenizer(s, pathSep);
			// Process each classpath
			while (st.hasMoreTokens()) {
				String cand = st.nextToken();
				System.err.println("Trying path " + cand);
				if (cand.endsWith(".zip") || cand.endsWith(".jar"))
					processOneZip(cand);
			}
		} else {
			// We have arguments, process them as zip files
			doingStandardClasses = false;
			for (int i=0; i<argv.length; i++)
				processOneZip(argv[i]);
		}

		return n;
	}

	/** For each Zip file, for each entry, xref it */
	public void processOneZip(String classes) {
			ArrayList entries = new ArrayList();

			try {
				ZipFile zippy = 
					new ZipFile(new File(classes));
				Enumeration all = zippy.entries();
				// For each entry, get its name and put it into "entries"
				while (all.hasMoreElements()) {
					entries.add(((ZipEntry)(all.nextElement())).getName());
				}
			} catch (IOException err) {
				System.err.println("IO Error: " + err);
				return;
			}

			// Sort the entries (by class name)
			Collections.sort(entries);

			// Process the entries
			for (int i=0; i< entries.size(); i++) {
				doClass((String)entries.get(i));
			}
	}

	/** Format the fields and methods of one class, given its name.
	 */
	protected void doClass(String zipName) {
		if (System.getProperties().getProperty("debug.names") != null)
			System.out.println("doClass(" + zipName + ");");

		// Ignore package/directory, other odd-ball stuff.
		if (zipName.endsWith("/")) {
			System.err.println("Starting directory " + zipName);
			return;
		}
		// Ignore META-INF stuff
		if (zipName.startsWith("META-INF/")) {
			return;
		}
		// Ignore images, HTML, whatever else we find.
		if (!zipName.endsWith(".class")) {
			System.err.println("Ignoring " + zipName);
			return;
		}
		// If doing CLASSPATH, Ignore com.sun.* which are "internal API".
		if (doingStandardClasses && zipName.startsWith("com.sun")){
			return;
		}
	
		// Convert the zip file entry name, like
		//	java/lang/Math.class
		// to a class name like
		//	java.lang.Math
		String className = zipName.replace('/', '.').
			substring(0, zipName.length() - 6);	// 6 for ".class"
		if (System.getProperties().getProperty("debug.names") != null)
			System.err.println("ZipName " + zipName + 
				"; className " + className);
		try {
			Class c = Class.forName(className);
			printClass(c);
		} catch (ClassNotFoundException e) {
			System.err.println("Error: Class " + 
				className + " not found!");
		} catch (Exception e) {
			System.err.println(e);
		}
		// System.err.println("in gc...");
		System.gc();
		// System.err.println("done gc");
	}

	protected abstract void printClass(Class ClassName);
}
