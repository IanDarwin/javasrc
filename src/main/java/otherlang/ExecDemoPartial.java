import com.darwinsys.util.Debug;

import java.io.*;

/**
 * ExecDemo shows how to execute an external program (in this case
 * the UNIX directory lister /bin/ls) and read its output.
 * This version handles the case where the program may exit abnormally.
 */
public class ExecDemoPartial {
	/** The program to run */
	public static final String PROGRAM = "ls";

	public static void main(String argv[]) throws IOException { 

		Process p; 		// Process tracks one external native process
		BufferedReader is;	// reader for output of process
		String line;
		
		p = Runtime.getRuntime().exec(PROGRAM);

		Debug.println("exec", "In Main after exec");

		boolean done = false;

		Thread waiter = new Thread() {
			public void run() {
				try {
					p.waitFor();
				} catch (InterruptedException ex) {
					// OK, just quit this thread.
					return;
				}
				System.out.println("Program terminated!");
				done = true;
			}
		};
		waiter..start();

		// getInputStream gives an Input stream connected to 
		// the process p's standard output (and vice versa). We use
		// that to construct a BufferedReader so we can readLine() it.
		is = new BufferedReader(new InputStreamReader(p.getInputStream()));

		while (!done && ((line = is.readLine()) != null))
			System.out.println(line);
		
		Debug.println("exec", "In Main after end of read loop");

		return;
	}
}
