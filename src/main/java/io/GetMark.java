import java.io.*;

/** GetMark -- get marked lines.
 * In this version, the marks are hard-coded; ideally they would come
 * from a FileProperties object.
 *
 * @author Ian F. Darwin, ian@darwinsys.com
 * @version $Id$
 */

public class GetMark {
	//+
	/** the default starting mark. */
	public final String startMark = "//+";
	/** the default ending mark. */
	public final String endMark = "//-";
	/** True if we are currently inside marks. */
	protected boolean printing = false;
	//-

    /** Get Marked parts of one file, given an open LineNumberReader.
	 */
    public void process(String fileName, LineNumberReader is) {
		//+
        try {
            String inputLine;

            while ((inputLine = is.readLine()) != null) {
				if (inputLine.trim().equals(startMark)) {
					if (printing)
						System.err.println("ERROR: START INSIDE START, " +
							fileName + : + is.getLineNumber());
					printing = true;
				} else if (inputLine.trim().equals(endMark)) {
					if (!printing)
						System.err.println("ERROR: STOP INSIDE STOP, " +
							fileName + : + is.getLineNumber());
					printing = false;
				} else if (printing)
					System.out.println(inputLine);
            }
            is.close();
		//-
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }
    }
	//-

	/** This simple main program looks after filenames and
	 * opening files and such like for you.
	 */
    public static void main(String av[]) {
        GetMark o = new GetMark();
        if (av.length == 0) {
            o.process("standard input", new LineNumberReader(
				new InputStreamReader(System.in)));
		} else {
			for (int i=0; i<av.length; i++)
				try {
					o.process(av[i],
						new LineNumberReader(new FileReader(av[i])));
				} catch (FileNotFoundException e) {
					System.err.println(e);
				}
        }
    }
}
