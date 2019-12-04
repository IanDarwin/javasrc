package javacomm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFrame;

/**
 * Print to a serial port using Java Communications.
 *
 * @author	Ian F. Darwin, http://www.darwinsys.com/
  */
public class ParallelPrint extends CommPortOpen {

	protected static String inputFileName;

	public static void main(String[] argv) throws Exception {

		if (argv.length != 1) {
			System.err.println("Usage: ParallelPrint filename");
			System.exit(1);
		}
		inputFileName = argv[0];

		new ParallelPrint(null).converse();

		System.exit(0);
	}

	/* Constructor */
	public ParallelPrint(JFrame f) throws Exception {
		
		super(f);
	}

	/** 
	 * Hold the (one-way) conversation. 
	 */
	protected void converse() throws IOException {

		// Make a reader for the input file.
		BufferedReader file = new BufferedReader(
			new FileReader(inputFileName));

		String line;
		while ((line = file.readLine()) != null)
			os.println(line);

		// Finally, clean up.
		file.close();
		os.close();
	}
}
