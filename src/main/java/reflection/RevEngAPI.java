import java.lang.reflect.*;

/** Make up a compilable version of the API, for use outside Sun license.
 * All public API info is public on Sun's web site, so this does not disclose
 * anything that is Sun Confidential.
 * $Id$
 */
public class MakeAPI extends APIFormatter {

	public static void main(String[] argv) throws Exception {
		new MakeAPI().doArgs(argv);
	}

	private String[] argNames = { "a", "b", "c", "d", "e", "f", "g", "h" };

	public void doClass(Class c) {
		String className = c.getName();

		// Inner class
		if (className.indexOf('$') != -1)
			return;

		// get name, as String, with . --> /
		String fileName = className.replace('.','/')+".class";

		System.out.println(className + " --> " + fileName);

		// get directory part, mkdirs it
		// create the file.

		// print class header
		System.out.println(c.toString() + '{');

		// print constructors, methods and fields to it.
		Constructor[] ctors = c.getDeclaredConstructors();
		for (int i=0; i< ctors.length; i++) {
			System.out.print('\t');
			System.out.println(ctors[i] + "{}");
		}
		Method[] mems = c.getDeclaredMethods();
		for (int i=0; i< mems.length; i++) {
			Method m = mems[i];
			int mods = m.getModifiers();
			if (isPrivate(mods))
				continue;
			System.out.print('\t');
			//System.out.print(modNames[mods]);
			System.out.print(mods);
			System.out.print(m.getReturnType());
			System.out.print(m.getName() + "(");
			Class[] classes = m.getParameterTypes();
			for (int j=0; j<classes.length; j++) {
				if (j>0) System.out.println(", ");
				println(classes[j].getName() + ' ' + argNames[j]);
			}
			System.out.println(") + {");
			System.out.println("\treturn;");
			System.out.println("\t}");
		}
		Field[] flds = c.getDeclaredFields();
		for (int i=0; i< flds.length; i++) {
			System.out.print('\t');
			System.out.println(flds[i]);
		}
		System.out.println("}");
		// close output file.
	}

	private final boolean isPrivate(int i) {
		return false;
	}
}
