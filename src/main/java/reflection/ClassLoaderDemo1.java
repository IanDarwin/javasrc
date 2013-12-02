package reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Hashtable;
import java.util.Map;

/**
 * Demonstration of a ClassLoader
 */
public class ClassLoaderDemo1 extends ClassLoader {
	/** The Hashtable to keep track of classes, to avoid re-loading them */
	protected Map<String,Class<?>> cache = new Hashtable<String,Class<?>>();
	/** data's expected length */
	private final int dataLength = 433;
	/** data, obtained by dumping a compiled .class file */
	private int[] data = {
		202, 254, 186, 190, 0, 3, 0, 45, 0, 31, 
		8, 0, 20, 7, 0, 17, 7, 0, 25, 7, 
		0, 26, 7, 0, 27, 10, 0, 4, 0, 9, 
		9, 0, 5, 0, 10, 10, 0, 3, 0, 11, 
		12, 0, 14, 0, 12, 12, 0, 28, 0, 22, 
		12, 0, 29, 0, 13, 1, 0, 3, 40, 41, 
		86, 1, 0, 21, 40, 76, 106, 97, 118, 97, 
		47, 108, 97, 110, 103, 47, 83, 116, 114, 105, 
		110, 103, 59, 41, 86, 1, 0, 6, 60, 105, 
		110, 105, 116, 62, 1, 0, 4, 67, 111, 100, 
		101, 1, 0, 13, 67, 111, 110, 115, 116, 97, 
		110, 116, 86, 97, 108, 117, 101, 1, 0, 4, 
		68, 101, 109, 111, 1, 0, 9, 68, 101, 109, 
		111, 46, 106, 97, 118, 97, 1, 0, 10, 69, 
		120, 99, 101, 112, 116, 105, 111, 110, 115, 1, 
		0, 10, 72, 101, 108, 108, 111, 32, 74, 97, 
		118, 97, 1, 0, 15, 76, 105, 110, 101, 78, 
		117, 109, 98, 101, 114, 84, 97, 98, 108, 101, 
		1, 0, 21, 76, 106, 97, 118, 97, 47, 105, 
		111, 47, 80, 114, 105, 110, 116, 83, 116, 114, 
		101, 97, 109, 59, 1, 0, 14, 76, 111, 99, 
		97, 108, 86, 97, 114, 105, 97, 98, 108, 101, 
		115, 1, 0, 10, 83, 111, 117, 114, 99, 101, 
		70, 105, 108, 101, 1, 0, 19, 106, 97, 118, 
		97, 47, 105, 111, 47, 80, 114, 105, 110, 116, 
		83, 116, 114, 101, 97, 109, 1, 0, 16, 106, 
		97, 118, 97, 47, 108, 97, 110, 103, 47, 79, 
		98, 106, 101, 99, 116, 1, 0, 16, 106, 97, 
		118, 97, 47, 108, 97, 110, 103, 47, 83, 121, 
		115, 116, 101, 109, 1, 0, 3, 111, 117, 116, 
		1, 0, 7, 112, 114, 105, 110, 116, 108, 110, 
		1, 0, 4, 116, 101, 115, 116, 0, 33, 0, 
		2, 0, 4, 0, 0, 0, 0, 0, 2, 0, 
		9, 0, 30, 0, 12, 0, 1, 0, 15, 0, 
		0, 0, 37, 0, 2, 0, 0, 0, 0, 0, 
		9, 178, 0, 7, 18, 1, 182, 0, 8, 177, 
		0, 0, 0, 1, 0, 21, 0, 0, 0, 10, 
		0, 2, 0, 0, 0, 9, 0, 8, 0, 8, 
		0, 1, 0, 14, 0, 12, 0, 1, 0, 15, 
		0, 0, 0, 29, 0, 1, 0, 1, 0, 0, 
		0, 5, 42, 183, 0, 6, 177, 0, 0, 0, 
		1, 0, 21, 0, 0, 0, 6, 0, 1, 0, 
		0, 0, 7, 0, 1, 0, 24, 0, 0, 0, 
		2, 0, 18
	};

	/** "load", that is, make up, the data for the class */
	private byte[] genClassData(String name) {
		if (dataLength != data.length)
			throw new IllegalArgumentException("data corrupt");
		byte[] bd = new byte[data.length];
		for (int i=0; i<bd.length; i++)
			bd[i] = (byte)data[i];
		return bd;
	}

	public synchronized Class<?> loadClass(String name, boolean resolve) 
			throws ClassNotFoundException { 
		/** We can expect to be called to resolve at least demo's
		 * superclass (java.lang.Object). Fortunatetely, we can just
		 * use super.findSystemClass() to load such things...
		 */
		if (name.startsWith("java.")) {
			System.out.println("loadClass: SystemLoading " + name);
			return findSystemClass(name);
		}
		Class<?> c = (Class<?>)cache.get(name);
		if (c == null) {
			System.out.println("loadClass: About to genClassData " + name);
			byte mydata[] = genClassData(name);
			System.out.println("loadClass: About to defineClass " + name);
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
		System.out.println("ClassLoaderDemo1 starting");
		ClassLoaderDemo1 loader = new ClassLoaderDemo1();
		Class<?> c = null;
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

		/** Try to load some arbitrary class, to see if our
		 * ClassLoader gets called.
		 */
		System.out.println("Trying to load an unrelated class");
		java.awt.image.DirectColorModel jnk = new java.awt.image.DirectColorModel(24,8,8,8);
		System.out.printf("Loaded an unrelated class (%s) - was your ClassLoader called?%n", jnk.getClass().getName());

		/** Try to instantiate a second ClassLoader */
		System.out.println("Trying to install another ClassLoader");
		ClassLoaderDemo1 loader2 = new ClassLoaderDemo1();
		System.out.println("Instantiated another ClassLoader..." + loader2);
	}
}
