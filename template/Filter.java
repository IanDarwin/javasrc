package template;

import java.io.*;

/** Filter - template line-mode filter.
 *
 * @author Ian F. Darwin, http://www.darwinsys.com/
 * @version $Id$
 */

public class Filter {

    /** Process one file
	 */
    protected void process(String fileName, LineNumberReader is) {
        try {
            String inputLine;

            while ((inputLine = is.readLine()) != null) {
				// This template Null Filter copies to stdout.
				// XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
				// Typically this is all you need to change.
				// XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
				System.out.println(inputLine);
            }
            is.close();
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }
    }

	/** This simple main program looks after filenames and
	 * opening files and such like for you.
	 */
    public static void main(String[] av) {
        Filter o = new Filter();
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
