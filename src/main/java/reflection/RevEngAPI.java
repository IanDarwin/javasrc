import java.io.*;
import java.lang.reflect.*;

/** Make up a compilable version of a given Sun or other API, 
 * so developers can compile against it without a licensed copy. In Sun's case,
 * all public API info is public on Sun's web site, so this does not disclose
 * anything that is Sun Confidential.
 * <p>This is a clean-room implementation: I did not look at the code
 * for Sun's javap or any similar tool in preparing this program.
 * XXX TODO:<ul>
 * <li>Class printing: add superclasses.
 * <li>Collapse common code in printing Constructors and Methods
 * <li>Method printing: add exceptions
 * <li>Arguments: Handle arrays (names begin [L)
 * <li>Provide default (0, false, null) based on type; use in return statements
 *		and in assigment to protected final variables.
 * </ul>
 * @author Ian Darwin, http://www.darwinsys.com/
 * @version $Id$
 */
public class MakeAPI extends APIFormatter {

	public static void main(String[] argv) throws Exception {
		new MakeAPI().doArgs(argv);
	}

	private final String[] argNames = {
		"a", "b", "c", "d", "e", "f", "g", "h"
	};

	/** NOT THREAD SAFE */
	private String className;
	private int classNameOffset;

	/** Generate a .java file for the outline of the given class. */
	public void doClass(Class c) throws IOException {
		className = c.getName();
		// pre-compute offset for stripping package name
		classNameOffset = className.lastIndexOf('.') + 1;

		// Inner class
		if (className.indexOf('$') != -1)
			return;

		// get name, as String, with . changed to /
		String slashName = className.replace('.','/');
		String fileName = slashName + ".java";

		System.out.println(className + " --> " + fileName);

		String dirName = slashName.substring(0, slashName.lastIndexOf("/"));
		new File(dirName).mkdirs();

		// create the file.
		PrintWriter out = new PrintWriter(new FileWriter(fileName));

		// If in a package, say so.
		Package pkg;
		if ((pkg = c.getPackage()) != null) {
			out.println("package " + pkg.getName() + ';');
			out.println();
		}
		// print class header
		int cMods = c.getModifiers();
		printMods(cMods, out);
		out.print("class ");
		out.print(trim(c.getName()));
		out.print(' ');
		// XXX get superclass 
		out.println('{');

		// print constructors
		Constructor[] ctors = c.getDeclaredConstructors();
		for (int i=0; i< ctors.length; i++) {
			if (i == 0) {
				out.println();
				out.println("\t// Constructors");
			}
			Constructor cons = ctors[i];
			int mods = cons.getModifiers();
			if (Modifier.isPrivate(mods))
				continue;
			out.print('\t');
			printMods(mods, out);
			out.print(trim(cons.getName()) + "(");
			Class[] classes = cons.getParameterTypes();
			for (int j = 0; j<classes.length; j++) {
				if (j > 0) out.print(", ");
				out.print(trim(classes[j].getName()) + ' ' + argNames[j]);
			}
			out.println(") {");
			out.print("\t}");
		}

		// print method names
		Method[] mems = c.getDeclaredMethods();
		for (int i=0; i< mems.length; i++) {
			if (i == 0) {
				out.println();
				out.println("\t// Methods");
			}
			Method m = mems[i];
			if (m.getName().startsWith("access$"))
				continue;
			int mods = m.getModifiers();
			if (Modifier.isPrivate(mods))
				continue;
			out.print('\t');
			printMods(mods, out);
			out.print(m.getReturnType());
			out.print(' ');
			out.print(trim(m.getName()) + "(");
			Class[] classes = m.getParameterTypes();
			for (int j = 0; j<classes.length; j++) {
				if (j > 0) out.print(", ");
				out.print(trim(classes[j].getName()) + ' ' + argNames[j]);
			}
			out.println(") {");
			out.println("\treturn;");
			out.println("\t}");
		}

		// print fields
		Field[] flds = c.getDeclaredFields();
		for (int i=0; i< flds.length; i++) {
			if (i == 0) {
				out.println();
				out.println("\t// Fields");
			}
			Field f = flds[i];
			int mods = f.getModifiers();
			if (Modifier.isPrivate(mods))
				continue;
			out.print('\t');
			printMods(mods, out);
			out.print(trim(f.getType().getName()));
			out.print(' ');
			out.print(f.getName());
			if (Modifier.isFinal(mods)) {
				try {
					out.print(" = " + f.get(null));
				} catch (IllegalAccessException ex) {
					out.print("; // " + ex.toString());
				}
			}
			out.println(';');
		}
		out.println("}");
		//out.flush();
		out.close();
	}

	private String trim(String theName) {
		return theName.startsWith(className) ?
			theName.substring(classNameOffset) : theName;
	}

	private class ModInfo {
		int val;
		String name;
		ModInfo(int v, String n) {
			val = v;
			name = n;
		}
	}

	private ModInfo[] modInfo = {
		new ModInfo(16, "final"),
		new ModInfo(2, "private"),
		new ModInfo(1, "public"),
		new ModInfo(4, "protected"),
		new ModInfo(1024, "abstract"),
		new ModInfo(8, "static"),
		new ModInfo(32, "synchronized"),
		new ModInfo(256, "native"),
		new ModInfo(128, "transient"),
		new ModInfo(64, "volatile"),
		new ModInfo(2048, "strict"),
	};

	private void printMods(int mods, PrintWriter out) {
		for (int i=0; i < modInfo.length; i++) {
			if ((mods & modInfo[i].val) == modInfo[i].val) {
				out.print(modInfo[i].name);
				out.print(' ');
			}
		}
	}

	public void startFile() {
		// save filename as project name
	}

	public void endFile() {
		// generate a trivial "build.xml" for Ant to create the jar file.
	}
}
