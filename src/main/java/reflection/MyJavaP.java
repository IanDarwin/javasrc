import java.io.*;
import java.util.*;
import java.lang.reflect.*;

/**
 * JavaP prints structural information about classes.
 * For each class, all public fields and methods are listed.
 * "Reflectance" is used to look up the information.
 *
 * @author	Ian Darwin, Ian@DarwinSys.com
 * @version	$Id$
 */
public class MyJavaP {

	/** A "Modifier" object, to decode modifiers of fields/methods */
	Modifier m = new Modifier();

	/** Simple main program, construct self, process each class name
	 * found in argv.
	 */
	public static void main(String[] argv) {
		MyJavaP pp = new MyJavaP();

		if (argv.length == 0) {
			System.err.println("Usage: javap className [...]");
			System.exit(1);
		} else for (int i=0; i<argv.length; i++)
			pp.doClass(argv[i]);
	}

	/** Format the fields and methods of one class, given its name.
	 */
	protected void doClass(String className) {

		try {
			Class c = Class.forName(className);
			System.out.println(m.toString(c.getModifiers()) + ' ' + c + " {");
			int i, mods;
			Field fields[] = c.getFields();
			for (i = 0; i < fields.length; i++) {
				if (!m.isPrivate(fields[i].getModifiers())
				 && !m.isProtected(fields[i].getModifiers()))
					System.out.println("\t" + fields[i]);
			}

			Method methods[] = c.getMethods();
			for (i = 0; i < methods.length; i++) {
				if (!m.isPrivate(methods[i].getModifiers())
				 && !m.isProtected(methods[i].getModifiers()))
					System.out.println("\t" + methods[i]);
			}
		} catch (ClassNotFoundException e) {
			System.err.println("Error: Class " + 
				className + " not found!");
		} catch (Exception e) {
			System.err.println(e);
		} finally {
			System.out.println("}");
		}
	}
}
