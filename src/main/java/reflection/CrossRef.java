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
 * with sort and awk/perl.
 *
 * @author	Ian Darwin, Ian@DarwinSys.com
 *
 */
public class CrossRef {
	/** Simple main program, construct and start, using argv */
	public static void main(String argv[])
	{
		CrossRef xref = new CrossRef();

		String s = (String)System.getProperties().get("java.class.path");
		// System.out.println("ClassPath is " + s);
		StringTokenizer st = new StringTokenizer(s, ":");
		while (st.hasMoreTokens()) {
			String cand = st.nextToken();
			// System.out.println("Trying path " + cand);
			if (cand.endsWith(".zip"))
				xref.processOneZip(cand);
		}
	}

	/** For each Zip file, for each entry, xref it */
	public void processOneZip(String classes) {
			try {
				ZipFile zippy = 
				new ZipFile(new File(classes));
				Enumeration all = zippy.entries();
				while (all.hasMoreElements())
				 doClass(((ZipEntry)(all.nextElement())).getName());
			} catch (IOException err) {
				System.err.println("IO Error: " + err);
				return;
			}
	}

	/** For one class name (which must be in CLASSPATH!!), 
	 * format its fields.
	 */
	public void doClass(String zipName) {
		// Convert the zip file entry, like
		//	java/lang/Math.class
		// to a class name like
		//	java.lang.Math
		if (!zipName.endsWith(".class"))
			throw new IllegalArgumentException("Unexp. class name " + zipName);
		String className = zipName.replace(/, .).
			substring(0, zipName.length() - 6);	// 6 for ".class"
System.out.println("ZipName " + zipName + "; className " + className);
		try {
			Class c = Class.forName(className);
			printClass(c);
		} catch (ClassNotFoundException e) {
			System.err.println("Error: Class " + 
				className + " not found!");
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	public void printClass(Class c) {
		int i;
		try {
			Field fields[] = c.getFields();
			for (i = 0; i < fields.length; i++) {
				println(fields[i].getName() +
					" field " + c.getName());
			}

			Method methods[] = c.getMethods();
			for (i = 0; i < methods.length; i++) {
				println(methods[i].getName() +
					" method " + c.getName());
			}
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	private final void println(String s) {
		System.out.println(s);
	}
}

