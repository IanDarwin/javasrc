package otherlang;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Logger;

/**
 * ExecDemo shows how to execute an external program (in this case
 * the UNIX directory lister /bin/ls) and read its output.
 */
// tag::main[]
public class ExecDemoLs {
	
	private static Logger logger =
		Logger.getLogger(ExecDemoLs.class.getSimpleName());

	/** The program to run */
	public static final String PROGRAM = "ls"; // "dir" for Windows
	/** Set to true to end the loop */
	static volatile boolean done = false;

	public static void main(String argv[]) throws IOException {

		final Process p; 		// Process tracks one external native process
		BufferedReader is;	// reader for output of process
		String line;
		
		p = Runtime.getRuntime().exec(new String[]{PROGRAM});

		logger.info("In Main after exec");

		try {
			p.waitFor();
		} catch (InterruptedException ex) {
			// Unlikely, but OK, just quit.
			return;
		}

		// getInputStream gives an Input stream connected to
		// the process p's standard output (and vice versa). We use
		// that to construct a BufferedReader so we can readLine() it.

		is = new BufferedReader(new InputStreamReader(p.getInputStream()));

		while (!done && ((line = is.readLine()) != null))
			System.out.println(line);
		
		logger.info("In Main after EOF");

		return;
	}
}
// end::main[]
