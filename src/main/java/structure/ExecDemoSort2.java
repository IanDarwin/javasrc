package structure;

/**
 * ExecDemo shows how to execute an external program and read its output.
 * This version tries to let the sort's standard output appear at the terminal.
 */
public class ExecDemoSort2 {
	public static void main(String[] av) { 

		// A Runtime object has methods for dealing with the OS
		Runtime r = Runtime.getRuntime();

		// A process object tracks one external running process
		Process p;

		try {
			// file contains unsorted data
			p = r.exec("sort sortdemo.txt");

			p.waitFor();
		} catch (java.io.IOException  e) {
			System.err.println("I/O error: " + e);
		} catch (InterruptedException e) {
			// nothing to do
		} 
	}
}
