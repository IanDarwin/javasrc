/**
 * ExecDemo shows how to execute an external program and read its output.
 */
public class ExecDemoSimple {
	public static void main(String av[]) throws java.io.IOException { 
		
		// Run the "ls" (directory lister) program
		Runtime.getRuntime().exec("/bin/ls");

	}
}
