package environ;

import java.util.*;
/**
 * Try adding one or more item(s) to class path.
 */
public class SysPropSet {

	static Properties p = System.getProperties();

	public static void main(String[] argv) {
		System.out.println("System Properties:");
		System.out.println("java.class.path now = " + getClassPath());
		p.setProperty("java.class.path",
			 getClassPath() + ';' + "C:/jdk1.2/lib/tools.jar");
		System.out.println("java.class.path now = " + getClassPath());
		try {
			Class.forName("sun.tools.javap.JavaP");
		} catch (Exception e) {
			System.err.println(e);
			return;
		}
		System.out.println("Got it!!");
	}
	
	static String getClassPath() {
		return p.getProperty("java.class.path", null);
	}
}
