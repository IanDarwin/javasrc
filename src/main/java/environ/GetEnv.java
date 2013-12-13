package environ;

/**
 * Demo of System.getenv().
 * This very basic OS-dependent method worked in 1.1 (and 1.2?), was 
 * deprecated in 1.3, throws an exception in 1.4, and WORKS AGAIN in 1.5.
 */
// BEGIN main
public class GetEnv {
	public static void main(String[] argv) {		
		System.out.println("System.getenv(\"PATH\") = " + System.getenv("PATH"));
	}
}
// END main
