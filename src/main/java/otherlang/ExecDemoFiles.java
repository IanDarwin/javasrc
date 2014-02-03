package otherlang;

import com.darwinsys.lang.ExecAndPrint;

/**
 * Create, list and remove some files
 */
public class ExecDemoFiles {
	public static void main(String av[]) throws Exception {
		
		// BEGIN main
		// Get and save the Runtime object.
		Runtime rt = Runtime.getRuntime();

		// Create three temporary files (the slow way!)
		rt.exec("mktemp file1");
		rt.exec("mktemp file2");
		rt.exec("mktemp file3");

		// Run the "ls" (directory lister) program
		// with its output sent into a file
		String[] args = { "ls", "-l", "file1", "file2", "file3" };
		ExecAndPrint.run(args);

		rt.exec("rm file1 file2 file3");
		// END main
	}
}
