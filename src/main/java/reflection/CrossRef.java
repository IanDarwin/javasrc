import java.io.*;
import java.util.*;
import java.util.zip.*;
import java.lang.reflect.*;

/**
 * CrossRef prints a cross-reference about all classes named in argv.
 * For each class, all public fields and methods are listed.
 * "Reflectance" is used to look up the information.
 *
 * TODO  System.getProperties().get("CLASSPATH"), strtok it.
 *
 * @author	Ian Darwin, Ian@DarwinSys.com
 *
 */
public class CrossRef {
	/** Simple main program, construct and start, using argv */
	public static void main(String argv[])
	{
		(new CrossRef()).processOneZip(argv);
	}

	/** For each Zip file, for each entry, xref it */
	public void processOneZip(String classes[]) {
		for (int i=0; i<classes.length; i++)
			try {
				ZipFile zippy = 
				new ZipFile(new File(classes[i]));
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
System.out.println("ZIpName " + zipName + "; className " + className);
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
					" field-in " + c.getName());
			}

			Method methods[] = c.getMethods();
			for (i = 0; i < methods.length; i++) {
				println(methods[i].getName() +
					" method-in " + c.getName());
			}
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	private final void println(String s) {
		System.out.println(s);
	}
}

