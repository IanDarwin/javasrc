package lang;

/**
 * Test if you can use a null reference to find a static method.
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
public class NullStaticMethod2 {
	public static void invoke() {
		System.out.println("Invoked even though null");
	}
}
