import java.io.*;
import java.util.*;
import java.util.zip.*;
import java.lang.reflect.*;

/**
 * CrossRef prints a cross-reference about all classes named in argv.
 * For each class, all public fields and methods are listed.
 * "Reflectance" is used to look up the information.
 *
 * It is expected that the output will be post-processed e.g.,
 * with sort and awk/perl. Try: 
	java CrossRef | 
		uniq | # squeeze out polymorphic forms early
		sort | awk '$2=="method" { ... }' > crossref-methods.txt
 * The part in "{ ... }" is left as an exercise for the reader. :-(
 *
 * @author	Ian Darwin, Ian@DarwinSys.com
 * @version	$Id$
 */
public class CrossRef extends APIFormatter {

	/** Simple main program, construct self, process each .ZIP file
	 * found in CLASSPATH or in argv.
	 */
	public static void main(String[] argv) {
		CrossRef xref = new CrossRef();
		xref.doArgs(argv);
	}

	/**
	 * Print the fields and methods of one class.
	 */
	protected void printClass(Class c) {
		int i, mods;
		startClass(c);
		try {
			Object[] fields = c.getDeclaredFields();
			Arrays.sort(fields);
			for (i = 0; i < fields.length; i++) {
				Field field = (Field)fields[i];
				if (!Modifier.isPrivate(field.getModifiers())
				 && !Modifier.isProtected(field.getModifiers()))
					putField(field, c);
				else System.err.println("private field ignored: " + field);
			}

			Method methods[] = c.getDeclaredMethods();
			// Arrays.sort(methods);
			for (i = 0; i < methods.length; i++) {
				if (!Modifier.isPrivate(methods[i].getModifiers())
				 && !Modifier.isProtected(methods[i].getModifiers()))
					putMethod(methods[i], c);
				else System.err.println("pvt: " + methods[i]);
			}
		} catch (Exception e) {
			System.err.println(e);
		}
		endClass();
	}

	/** put a Field's information to the standard output.  */
	protected void putField(Field fld, Class c) {
		println(fld.getName() + " field " + c.getName() + " ");
	}

	/** put a Method's information to the standard output.  */
	protected void putMethod(Method method, Class c) {
		String methName = method.getName();
		println(methName + " method " + c.getName() + " ");
	}

	/** Print the start of a class. Unused in this version,
	 * designed to be overridden */
	protected void startClass(Class c) {
	}

	/** Print the end of a class. Unused in this version,
	 * designed to be overridden */
	protected void endClass() {
	}

	/** Convenience routine, short for System.out.println */
	protected final void println(String s) {
		System.out.println(s);
	}
}
