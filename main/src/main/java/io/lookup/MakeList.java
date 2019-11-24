package io.lookup;

import java.io.*;
import java.util.*;
import java.util.zip.*;

/**
 * MakeList makes the properties list for the Lookup program.
 *
 * @author	Ian Darwin, Ian@DarwinSys.com
 */
public class MakeList {
	/** The output file name. */
	public static final String FILENAME = "ClassDoc.properties";

	/** The output file */
	protected PrintWriter file;

	/** The system Properties list */
	protected static Properties sysProps = System.getProperties();

	/** The path separator */
	protected static String pathSep = sysProps.getProperty("path.separator");

	/** Simple main program, construct self, process each .ZIP file
	 * found in CLASSPATH.
	 */
	public static void main(String[] argv) {
		MakeList maker = new MakeList();

		if (argv.length == 0) {
			String s = sysProps.getProperty("java.class.path") +
			pathSep + sysProps.getProperty("sun.boot.class.path");
			// System.err.println("ClassPath is " + s);
			StringTokenizer st = new StringTokenizer(s, pathSep);
			while (st.hasMoreTokens()) {
				String cand = st.nextToken();
				System.err.println("Trying path " + cand);
				if (cand.endsWith(".zip") || cand.endsWith(".jar"))
					maker.processOneZip(cand);
			}
		} else for (int i=0; i<argv.length; i++)
			maker.processOneZip(argv[i]);

		System.err.println("All done!");
	}

	/** Construct a MakeList */
	public MakeList() {
		try {
			file = new PrintWriter(
				new FileWriter(FILENAME));
		} catch (IOException e) {
			System.err.println("ERROR: " + e);
			System.exit(1);
		}
	}

	/** For each Zip file, for each entry, xref it */
	public void processOneZip(String fileName) {
		try {
			@SuppressWarnings("resource")
			ZipFile zippy = 
				new ZipFile(new File(fileName));
			Enumeration<?> all = zippy.entries();
			while (all.hasMoreElements()) {
				doClass(((ZipEntry)(all.nextElement())).getName());
			}
		} catch (IOException err) {
			System.err.println("IO Error: " + err);
			return;
		}
		file.flush();
	}

	/** Format the fields and methods of one class, given its name.
	 */
	protected void doClass(String zipName) {
		if (System.getProperties().getProperty("debug.names") != null)
			System.err.println("doClass(" + zipName + ");");
		// Ignore package/directory, other odd-ball stuff.
		if (zipName.endsWith("/")) {
			// System.err.println("Starting directory " + zipName);
			return;
		}
		if (!zipName.endsWith(".class")) {
			// System.err.println("Ignoring " + zipName);
			return;
		}
		// Ignore sun.* etc.
		if (!zipName.startsWith("java")){
			// System.err.println("Ignoring " + zipName);
			return;
		}
	
		// Convert the zip file entry, like
		//	java/lang/Math.class
		// to a class name like
		//	java.lang.Math
		String className = zipName.replace('/', '.').
			substring(0, zipName.length() - 6);	// 6 for ".class"

		String shortName = className.substring(
			1+className.lastIndexOf('.'));

		file.println(shortName.toLowerCase() + "=" + className);
	}
}
