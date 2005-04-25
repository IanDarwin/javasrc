package email;

import java.io.*;

import com.darwinsys.util.Debug;

/**
 * Read a file and print, using LineReader and System.out
 */
public class DropReceivedLines {

    public static void main(String[] av) {
        DropReceivedLines d = new DropReceivedLines();
		// For stdin, act as a filter. For named files,
		// update each file in place (safely, by creating a new file).
		try {
			if (av.length == 0) 
				d.process(new BufferedReader(
						new InputStreamReader(System.in)), 
						new PrintWriter(System.out));
			else for (int i=0; i<av.length; i++)
				d.process(av[i]);
		} catch (FileNotFoundException e) {
			System.err.println("Can't read file: " + e);
		} catch (IOException e) {
			System.err.println("I/O error " + e);
		}
    }

	protected static File tempFile = new File("holding.tmp");

	/** Process one file given only its name */
	public void process(String fileName) throws IOException {
		File old = new File(fileName);
		String newFileName = fileName + ".TMP";
		File newf = new File(newFileName);
		BufferedReader is =
				new BufferedReader(new FileReader(fileName));
		PrintWriter p = new PrintWriter(new FileWriter(newFileName));
		process(is, p);		// call other process(), below
		p.close();
		old.renameTo(tempFile);
		newf.renameTo(old);
		tempFile.delete();
	}

    /** process one file, given an open LineReader */
    public void process(BufferedReader is, PrintWriter out)
		throws IOException {
        try {
            String lin;

			// If line begins with "Received:", ditch it, and its continuations
            while ((lin = is.readLine()) != null) {
				Debug.println("read", "Read line " + lin);
				if (lin.startsWith("Received:")) {
					do {
						lin = is.readLine();
						Debug.println("read", "\tContin read line " + lin);
					} while (lin.length() > 0 &&
						Character.isWhitespace(lin.charAt(0)));
				}
				out.println(lin);
            }
            is.close();
			out.close();
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }
    }
}
