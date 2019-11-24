package environ;

public class PrintClassPath {
	public static void main(String[] args) {
		System.out.println("java.class.path=" +
			System.getProperty("java.class.path", "Could not find class.path"));
	}
}
