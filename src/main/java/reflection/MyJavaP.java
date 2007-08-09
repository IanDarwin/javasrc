package reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * JavaP prints structural information about classes.
 * For each class, all public fields and methods are listed.
 * The "Reflection" API is used to look up the information.
 *
 * @version	$Id$
 */
public class MyJavaP {

	/** Simple main program, construct self, process each class name
	 * found in argv.
	 */
	public static void main(String[] argv) {
		MyJavaP pp = new MyJavaP();

		if (argv.length == 0) {
			System.err.println("Usage: MyJavaP className [...]");
			System.exit(1);
		} else for (int i=0; i<argv.length; i++)
			pp.doClass(argv[i]);
	}

	/** Format the fields and methods of one class, given its name.
	 */
	protected void doClass(String className) {
		try {
			Class c = Class.forName(className);
			System.out.println(c + " {");

			int mods;
			Field fields[] = c.getDeclaredFields();
			for (Field f : fields) {
				if (!Modifier.isPrivate(f.getModifiers())
				 && !Modifier.isProtected(f.getModifiers()))
					System.out.println("\t" + f + ";");
			}
			Constructor[] constructors = c.getConstructors();
			for (Constructor con : constructors) {
				System.out.println("\t" + con + ";");
			}
			Method methods[] = c.getDeclaredMethods();
			for (Method m : methods) {
				if (!Modifier.isPrivate(m.getModifiers())) {
					System.out.println("\t" + m + ";");
				}
			}
			System.out.println("}");
		} catch (ClassNotFoundException e) {
			System.err.println("Error: Class " + 
				className + " not found!");
		} catch (Exception e) {
			System.err.println("JavaP Error: " + e);
		}
	}
}
