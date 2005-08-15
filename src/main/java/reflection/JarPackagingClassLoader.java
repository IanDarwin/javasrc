package introspection;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * A Useful ClassLoader - it (will) make a single combined
 * Jar of ALL the classes used in your program.
 * <br/>
 * NOT WORKING!!
 * <ol>
 * <li>Doesn't seem correctly to load classes from Jar
 * <li>Need to implement the code for saving into a new Jar
 * 	(i.e., the main reason for this program's existence).
 * </ol>
 */
public class JarPackagingClassLoader extends ClassLoader {
	
	private static final String DEMO_CLASS_TO_LOAD = "introspection.MultiDemo";
	
	/** The Hashtable to keep track of classes, to avoid re-loading them */
	protected Map<String, Class> loadedClassesCache = new HashMap<String, Class>();

	/** The Cache of Jar files we've opened */
	protected Map<String, JarFile> jarsCache = new HashMap<String, JarFile>();

	/** "load", that is, make up, the data for the class 
	 * @throws ClassNotFoundException
	 */
	private byte[] genClassData(String className) throws ClassNotFoundException {
		String cPath = System.getProperty("java.class.path");
		String[] cPaths = cPath.split(":");	
		String fileName = className.replaceAll("\\.", "/") + ".class";
		System.out.println("Munged fileName = " + fileName);
		for (String cPathName : cPaths) {
			if (cPathName.endsWith(".jar")) {
				JarFile jf = jarsCache.get(cPathName);
				if (jf == null) {
					try {
						jf = new JarFile(cPathName);
						jarsCache.put(cPathName, jf);
						JarEntry je = jf.getJarEntry(fileName);
						if (je == null) {
							// not found in this JAR, try next...
							continue;
						}
						long classSize = je.getSize();
						if (classSize > Integer.MAX_VALUE) {
							throw new ClassFormatError(className + " Jar entry too big");
						}
						InputStream is = jf.getInputStream(je);
						byte[] data = new byte[(int)classSize];
						is.read(data);
						System.out.println("[Loaded class " + className +
							" from jar " + cPathName);
						return data;
					} catch (IOException e) {
						System.err.printf("Warning: ClassPath Entry %s missing", cPathName);
						continue;
					}
				}
				
			} else {
				File f = new File(cPathName + "/" + fileName);
				if (f.exists()) {
					
					long dataLength = f.length();
					byte[] bd = new byte[(int)dataLength];
					try {
						InputStream is = new FileInputStream(f);
						is.read(bd);
						is.close();
						System.out.println("[Loaded " + className +
							" from file " + f.getAbsolutePath());
						return bd;
					} catch (IOException ex) {
						throw new ClassNotFoundException(ex.toString());
					}	
				}
			} 
		}

		// If still here, we didn't find it.
		throw new ClassNotFoundException(className);
	}

	@Override
	public synchronized Class<?> loadClass(String className, boolean resolve) 
			throws ClassNotFoundException {
		System.out.printf("JarPackagingClassLoader.loadClass(%s)\n", className);
		/** We can expect to be called to resolve at least the target's
		 * superclass (java.lang.Object). Fortunatetely, we can just
		 * use super.findSystemClass() to load such things...
		 */
		if (className.startsWith("java.") || className.startsWith("javax.")) {
			System.out.println("loadClass: SystemLoading " + className);
			return findSystemClass(className);
		}
		Class c = loadedClassesCache.get(className);
		if (c == null) {
			System.out.println("loadClass: About to genClassData " + className);
			byte mydata[] = genClassData(className);
			System.out.println("loadClass: About to defineClass " + className);
			c = defineClass(className, mydata, 0, mydata.length);
			
			loadedClassesCache.put(className, c);
		} else
			System.out.println("loadClass: found " + className + " in cache.");
		if (resolve) {
			System.out.println("loadClass: About to resolveClass " + className);
			resolveClass(c);
		}
		return c;
	}

	public static void main(String[] args) {
		System.out.println("JarPackagingClassLoader.main()");
		JarPackagingClassLoader loader = new JarPackagingClassLoader();
		String classToLoad =
			args.length == 0 ? DEMO_CLASS_TO_LOAD : args[0];
		try {
			/* Load the target class */

			System.out.println("About to load class  Demo");
			Class c = loader.loadClass(classToLoad, true);
			System.out.printf("About to instantiate class %s%n", classToLoad);
			Object demo = c.newInstance();
			System.out.println("Got Demo class instantiated: " + demo);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Could not load/instantiate test class");
		}
	}
}
