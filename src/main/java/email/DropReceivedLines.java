import java.io.*;

/**
 * Read a file and print, using LineReader and System.out
 * NOT WORKING: need to use BufferedReader for non-header text.
 */
public class DropReceivedLines {

    public static void main(String av[]) {
        DropReceivedLines d = new DropReceivedLines();
		// For stdin or each file, build a IndentContLineReader
		// to treat the Received: lines as one.
		try {
			if (av.length == 0) 
				d.process(new BufferedReader(
						new InputStreamReader(System.in)), 
						new PrintWriter(System.out));
			else for (int i=0; i<av.length; i++)
				d.process(av[i]);
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
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
    public void process(BufferedReader ins, PrintWriter out)
		throws IOException {
		IndentContLineReader is = new IndentContLineReader(ins);
        try {
            String lin;

			// If line begins with "Received:", ditch it, and its continuations
            while ((lin = is.readLine()) != null) {
				if (!lin.startsWith("Received:"))
					out.println(lin);
            }
            is.close();
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }
    }
}
