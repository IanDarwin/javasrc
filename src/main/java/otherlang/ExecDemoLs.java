package otherlang;

import com.darwinsys.util.Debug;

import java.io.*;

/**
 * ExecDemo shows how to execute an external program (in this case
 * the UNIX directory lister /bin/ls) and read its output.
 */
// BEGIN main
public class ExecDemoLs {
	/** The program to run */
	public static final String PROGRAM = "ls"; // "dir" for Windows
	/** Set to true to end the loop */
	static volatile boolean done = false;

	public static void main(String argv[]) throws IOException {

		final Process p; 		// Process tracks one external native process
		BufferedReader is;	// reader for output of process
		String line;
		
		p = Runtime.getRuntime().exec(PROGRAM);

		Debug.println("exec", "In Main after exec");

		// Optional: start a thread to wait for the process to terminate.
		// Don't just wait in main line, but here set a "done" flag and
		// use that to control the main reading loop below.
		Thread waiter = new Thread() {
			public void run() {
				try {
					p.waitFor();
				} catch (InterruptedException ex) {
					// OK, just quit.
					return;
				}
				System.out.println("Program terminated!");
				done = true;
			}
		};
		waiter.start();

		// getInputStream gives an Input stream connected to
		// the process p's standard output (and vice versa). We use
		// that to construct a BufferedReader so we can readLine() it.
		is = new BufferedReader(new InputStreamReader(p.getInputStream()));

		while (!done && ((line = is.readLine()) != null))
			System.out.println(line);
		
		Debug.println("exec", "In Main after EOF");

		return;
	}
}
// END main
