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
        if (av.length == 0) 
            d.process(new IndentContLineReader(
					new InputStreamReader(System.in)));
		else for (int i=0; i<av.length; i++)
			try {
				d.process(new IndentContLineReader(new FileReader(av[i])));
			} catch (FileNotFoundException e) {
				System.err.println(e);
			}
    }

    /** process one file, given an open LineReader */
    public void process(LineNumberReader is) {
        try {
            String lin;

			// If line begins with "Received:", ditch it, and its continuations
            while ((lin = is.readLine()) != null) {
				if (!lin.startsWith("Received:"))
					System.out.println(lin);
            }
            is.close();
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }
    }
}
