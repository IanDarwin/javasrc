/**
 * ExecDemo shows how to execute an external program and read its output.
 */
public class ExecDemoSimple {
	public static void main(String av[]) throws Exception { 
		
		// Run the "ls" (directory lister) program
		Process p = Runtime.getRuntime().exec("/bin/ls");

		p.waitFor();

	}
}
