package otherlang;

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
	/** Set to true to end the loop */
	static boolean done = false;

	public static void main(String argv[]) throws IOException {

		BufferedReader is;	// reader for output of process
		String line;
		
		final Process p = Runtime.getRuntime().exec(PROGRAM);

		Debug.println("exec", "In Main after exec");


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
		waiter.start();

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
