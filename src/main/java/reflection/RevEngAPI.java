import java.io.*;
import java.lang.reflect.*;

/** Make up a compilable version of the API, for use outside Sun license.
 * All public API info is public on Sun's web site, so this does not disclose
 * anything that is Sun Confidential.
 * XXX TODO:<ul>
 * <li>Fixup class names by making package statement, dropping . part of name
 * <li>Handle arrays (names begin [L)
 * </ul>
 * $Id$
 */
public class MakeAPI extends APIFormatter {

	public static void main(String[] argv) throws Exception {
		new MakeAPI().doArgs(argv);
	}

	private String[] argNames = { "a", "b", "c", "d", "e", "f", "g", "h" };

	public void doClass(Class c) throws IOException {
		String className = c.getName();

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

		// print class header
		out.println(c.toString() + '{');

		// print constructors
		Constructor[] ctors = c.getDeclaredConstructors();
		for (int i=0; i< ctors.length; i++) {
			if (i == 0) {
				out.println();
				out.println("\t// Constructors");
			}
			out.print('\t');
			out.println(ctors[i] + "{}");
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
			out.print(' ');
			out.print(m.getReturnType());
			out.print(' ');
			out.print(m.getName() + "(");
			Class[] classes = m.getParameterTypes();
			for (int j = 0; j<classes.length; j++) {
				if (j > 0) out.print(", ");
				out.print(classes[j].getName() + ' ' + argNames[j]);
			}
			out.println(") + {");
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
			out.print(' ');
			out.print(f.getType().getName());
			out.print(' ');
			out.print(f.getName());
			if (Modifier.isPublic(mods) && Modifier.isFinal(mods)) {
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
