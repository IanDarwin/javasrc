package lang;

/**
 * Can an application change its CLASSPATH?
 */
public class ChangeClassPath {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String oldClassPath = System.getProperty("java.class.path");
		System.out.println(oldClassPath);
		System.setProperty("java.class.path", "/");
		try {
			Class.forName("lang.BuiltinTypes");
			System.out.println("No, Setting class.path does not work");	
		} catch (ClassNotFoundException e) {
			System.out.println("Yes, Caught expected " + e);
		}
	}

}
