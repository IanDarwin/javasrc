package reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Hashtable;

/**
 * Simple Demonstration of a ClassLoader
 * WILL NOT COMPILE OUT OF THE BOX -- WILL ONLY WORK AS PART OF
 * THE CLASSLOADER EXERCISE. See ClassLoader1 for a working version.
 */
@SuppressWarnings("unchecked")
public class ClassLoaderDemo0 extends ClassLoader {
	/** The Hashtable to keep track of classes, to avoid re-loading them */
	protected Hashtable<String,Class> cache = new Hashtable<String,Class>();

	/** INSERT HERE THE RESULT OF DUMPING DEMO.CLASS */
	byte[] data /* = ... */;
	int dataLength;

	/** END OF INSERTION OF THE RESULT OF DUMPING DEMO.CLASS */

	/** "load", that is, generate, the data for the class */
	private byte[] genClassData(String name) {
		if (data == null) {
			throw new RuntimeException("You must initialize the data array");
		}
		if (dataLength != data.length)	// EXPECT COMPILE ERROR in javasrc
			throw new IllegalArgumentException(
				"data corrupt, " + dataLength + "!=" + data.length);
		byte[] bd = new byte[data.length];
		for (int i=0; i<bd.length; i++)
			bd[i] = (byte)data[i];
		return bd;
	}

	public synchronized Class<?> loadClass(String name, boolean resolve) 
			throws ClassNotFoundException { 
		Class c = (Class)cache.get(name);
		if (c == null) {
			// System.out.println("loadClass: About to genClassData " + name);
			byte mydata[] = genClassData(name);
			// System.out.println("loadClass: About to defineClass " + name);
			c = defineClass(name, mydata, 0, mydata.length);
			System.out.println("loadClass: storing " + name + " in cache.");
			cache.put(name, c);
		} else
			System.out.println("loadClass: found " + name + " in cache.");
		if (resolve) {
			System.out.println("loadClass: About to resolveClass " + name);
			resolveClass(c);
		}
		return c;
	}

	public static void main(String[] argv) {
		System.out.println("ClassLoaderDemo starting");
		ClassLoaderDemo0 loader = new ClassLoaderDemo0();
		Class c = null;
		Object demo;
		try {
			/* Load the "Demo" class from memory */

			System.out.println("About to load class  Demo");
			c = loader.loadClass("Demo", true);
			System.out.println("About to instantiate class Demo");
			demo = c.newInstance();
			System.out.println("Got Demo class loaded: " + demo);

			/* Now try to call a method */

			Method mi = c.getMethod("test", (Class[])null);
			mi.invoke(demo, (Object[])null);

		} catch (InvocationTargetException e) {
			// The invoked method threw an exception. We get it
			// wrapped up inside another exception, hence the
			// extra call here:
			e.getTargetException().printStackTrace();
			System.out.println("Could not run test method");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Could not run test method");
		}
	}
}
