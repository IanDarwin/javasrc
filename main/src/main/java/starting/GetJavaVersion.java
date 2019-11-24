package starting;

/**
 * Just print the Java runtime version, e.g., 1.3, 1.4, 1.5, ...
 */
public class GetJavaVersion {
	public static void main(String[] args) {
		System.out.println(System.getProperty("java.specification.version"));
	}
}
