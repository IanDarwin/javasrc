package unfinished;

import java.io.*;
import java.util.zip.*;

/** ClassLoader to load classes directly from a ZipFile, assuming that
 * the Zip or Jar file is not on your classpath
 */
public class ZipClassLoader extends ClassLoader {

	private ZipFile zipFile;

	public ZipClassLoader(ZipFile zf) {
		zipFile = zf;
	}

	/** Override of standard findClass method. Not used by the application,
	 * but will be called by JVM for referenced classes so must work. :-)
	 */
	public Class findClass(String className) throws ClassNotFoundException {
		System.out.println("ZipClassLoader: In standard findClass");
		ZipEntry ze = zipFile.getEntry(className);
		if (ze == null) {
			throw new ClassNotFoundException(
			"findClass(string) can't find " + className + " in ZipFile");
		}
		return findClass(className, ze);
	}

	/* load the Class given by the parameters - overload, not override */
	public Class findClass(String className, ZipEntry zipEntry)
	throws ClassNotFoundException {
		System.out.println("ZipClassLoader: In overloaded findClass");
		byte[] bytes;
		try {
			InputStream is = zipFile.getInputStream(zipEntry);
			int size = (int)zipEntry.getSize();
			bytes = new byte[size];
			int n = 0, total = 0;
			while (total < size) {
				n = is.read(bytes, total, size-total);
				total += n;
			}
			is.close();
		} catch (IOException ex) {
			throw new ClassNotFoundException(ex.toString());
		}
		System.out.println("ZipClassLoader: Defining " + className);
		Class c = defineClass(className, bytes, 0, bytes.length);
		return c;
	}

	public Class loadClass(String className) throws ClassNotFoundException {
		System.out.println("ZipClassLoader: In loadClass, calling super(" +
			className + ")");
		return super.loadClass(className);
	}
	
}
