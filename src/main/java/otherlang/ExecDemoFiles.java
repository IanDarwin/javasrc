/**
 * Create, list and remove some files
 */
public class ExecDemoFiles {
	public static void main(String av[]) throws Exception { 
		
		// Get and save the Runtime object.
		Runtime rt = Runtime.getRuntime();

		// Create three temporary files
		rt.exec("mktemp 1-XXXXXX");
		rt.exec("mktemp 2-XXXXXX");
		rt.exec("mktemp 3-XXXXXX");
		

		// Run the "ls" (directory lister) program
		// with its output sent into a file
		rt.exec("/bin/ls ?-* | dd of=jnk");
	}
}
