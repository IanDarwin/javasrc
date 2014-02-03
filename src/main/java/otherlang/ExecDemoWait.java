package otherlang;

import java.io.*;

/**
 * ExecDemo shows how to execute an external program
 * read its output, and print its exit status.
 */
public class ExecDemoWait {

	public static void main(String argv[]) throws IOException {

		// BEGIN main
		// A Runtime object has methods for dealing with the OS
		Runtime r = Runtime.getRuntime();
		Process p; 		// Process tracks one external native process
		BufferedReader is;	// reader for output of process
		String line;
		
		// Our argv[0] contains the program to run; remaining elements
		// of argv contain args for the target program. This is just
		// what is needed for the String[] form of exec.
		p = r.exec(argv);

		System.out.println("In Main after exec");

		// getInputStream gives an Input stream connected to
		// the process p's standard output. Just use it to make
		// a BufferedReader to readLine() what the program writes out.
		is = new BufferedReader(new InputStreamReader(p.getInputStream()));

		while ((line = is.readLine()) != null)
			System.out.println(line);
		
		System.out.println("In Main after EOF");
		System.out.flush();
		try {
			p.waitFor();	// wait for process to complete
		} catch (InterruptedException e) {
			System.err.println(e);	// "Can'tHappen"
			return;
		}
		System.err.println("Process done, exit status was " + p.exitValue());
		// END main
	}
}
