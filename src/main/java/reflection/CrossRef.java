import java.io.*;
import java.util.*;
import java.util.zip.*;
import java.lang.reflect.*;

/**
 * CrossRef prints a cross-reference about all classes named in argv.
 * For each class, all public fields and methods are listed.
 * "Reflectance" is used to look up the information.
 *
 * It is expected that the output will be post-processed e.g.,
 * with sort and awk/perl. Try: 
	java CrossRef | 
		uniq | # squeeze out polymorphic forms early
		sort | awk '$2=="method" { ... }' > crossref-methods.txt
 * The part in "{ ... }" is left as an exercise for the reader. :-(
 *
 * @author	Ian Darwin, Ian@DarwinSys.com
 * @version	$Id$
 */
public class CrossRef {
	/** Counter of fields/methods printed. */
	protected static int n = 0;

	/** True if we are doing classpath, so only do java. and javax. */
	protected static boolean doingStandardClasses = true;
	
	/** Simple main program, construct self, process each .ZIP file
	 * found in CLASSPATH or in argv.
	 */
	public static void main(String[] argv) {
		CrossRef xref = new CrossRef();

		xref.doArgs(argv);
	}

	protected void doArgs(String[] argv) {

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

		System.err.println("All done! Found " + n + " entries.");
		System.exit(0);
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

	/**
	 * Print the fields and methods of one class.
	 */
	protected void printClass(Class c) {
		int i, mods;
		startClass(c);
		try {
			Object[] fields = c.getDeclaredFields();
			Arrays.sort(fields);
			for (i = 0; i < fields.length; i++) {
				Field field = (Field)fields[i];
				if (!Modifier.isPrivate(field.getModifiers())
				 && !Modifier.isProtected(field.getModifiers()))
					putField(field, c);
				else System.err.println("private field ignored: " + field);
			}

			Method methods[] = c.getDeclaredMethods();
			// Arrays.sort(methods);
			for (i = 0; i < methods.length; i++) {
				if (!Modifier.isPrivate(methods[i].getModifiers())
				 && !Modifier.isProtected(methods[i].getModifiers()))
					putMethod(methods[i], c);
				else System.err.println("pvt: " + methods[i]);
			}
		} catch (Exception e) {
			System.err.println(e);
		}
		endClass();
	}

	/** put a Field's information to the standard output.
	 * Marked protected so you can override it (hint, hint).
	 */
	protected void putField(Field fld, Class c) {
		println(fld.getName() + " field " + c.getName() + " ");
		++n;
	}
	/** put a Method's information to the standard output.
	 * Marked protected so you can override it (hint, hint).
	 */
	protected void putMethod(Method method, Class c) {
		String methName = method.getName();
		println(methName + " method " + c.getName() + " ");
		++n;
	}
	/** Print the start of a class. Unused in this version,
	 * designed to be overridden */
	protected void startClass(Class c) {
	}

	/** Print the end of a class. Unused in this version,
	 * designed to be overridden */
	protected void endClass() {
	}

	/** Convenience routine, short for System.out.println */
	protected final void println(String s) {
		System.out.println(s);
	}
}
