import java.io.*;

/**
 * ExecDemo shows the students of Learning Tree Course 471/478
 * how to execute an external program and read its output.
 */
public class ExecDemoSort {
	public static void main(String av[]) throws IOException { 
		// A Runtime object has methods for dealing with the OS
		Runtime r = Runtime.getRuntime();
		// A process object tracks one external running process
		Process p;
		BufferedReader is;
		String fname;
		
		p = r.exec("sort sortdemo.txt");	// contains unsorted data

		// getInputStream gives an Input stream connected to 
		// the process p's standard output (and vice versa). We use
		// that to construct a BufferedReader so we can readLine() it.
		is = new BufferedReader(new InputStreamReader(p.getInputStream()));

		System.out.println("Here is your sorted data:");

		while ((fname = is.readLine()) != null)
			System.out.println(fname);
		
		System.out.println("That is all, folks!");

		return;
	}
}
