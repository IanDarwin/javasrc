package reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * JavaP prints structural information about classes.
 * For each class, all public fields and methods are listed.
 * The "Reflection" API is used to look up the information.
 *
 */
// BEGIN main
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
			Class<? extends Object> c = Class.forName(className);

			final Annotation[] annotations = c.getAnnotations();
			for (Annotation a : annotations) {
				System.out.println(a);
			}

			System.out.println(c + " {");

			Field fields[] = c.getDeclaredFields();
			for (Field f : fields) {
				final Annotation[] fldAnnotations = f.getAnnotations();
				for (Annotation a : fldAnnotations) {
					System.out.println(a);
				}
				if (!Modifier.isPrivate(f.getModifiers()))
					System.out.println("\t" + f + ";");
			}
			
			Constructor<? extends Object>[] constructors = c.getConstructors();
			for (Constructor<? extends Object> con : constructors) {
				System.out.println("\t" + con + ";");
			}
			
			Method methods[] = c.getDeclaredMethods();
			for (Method m : methods) {
				final Annotation[] methodAnnotations = m.getAnnotations();
				for (Annotation a : methodAnnotations) {
					System.out.println(a);
				}
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
// END main
