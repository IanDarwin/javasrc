import java.io.*;

/**
 * ExecDemo shows how to execute an external program 
 * read its output, and print its exit status.
 */
public class ExecDemoWait {
	/** The program to run */
	public static String program = "ls";
	/** The arguments to pass it */
	public static String[] newArgs = {};

	public static void main(String argv[]) throws IOException { 

		// A Runtime object has methods for dealing with the OS
		Runtime r = Runtime.getRuntime();
		Process p; 		// Process tracks one external native process
		BufferedReader is;	// reader for output of process
		String line;
		
		if (argv.length != 0) {
			program = argv[0];
			// Make up argv-format (array of String) minus program name.
			newArgs = new String[argv.length -1];
			System.arraycopy(argv, 1, newArgs, 0, argv.length - 1);
		}
		p = r.exec(program, newArgs);

		System.out.println("In Main after exec");

		// getInputStream gives an Input stream connected to 
		// the process p's standard output (and vice versa). We use
		// that to construct a BufferedReader so we can readLine() it.
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
		return;
	}
}
