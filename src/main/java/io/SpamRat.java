import java.io.*;
import java.util.*;

/**
 * SpamRat: The Fury.
 * Read a SPAM mail message and generate an EMail compose window to the
 * SPAM-artist's ISP.
 * This version just prints the Received line with the offending ISP. 
 * TODO: extract the ISP's name, do host lookup if numeric,
 *	do whois netblock lookup if that fails.
 *  2) Have a FileProperties containing known "abuse" type addresses
 *	(some are abuse, some are email-abuse, etc.). And name the
 *	template file.
 *  3) Construct and show a MailComposeBean (in a JFrame) with the
 *  template filled in as much as possible.
 * @author Ian F. Darwin, ian@darwinsys.com
 * @version $Id$
 */
public class SpamRat {

    public static void main(String av[]) {
        SpamRat r = new SpamRat();
		// For stdin or each file, build a IndentContLineReader
		// to treat the Received: lines as one.
        if (av.length == 0) 
            r.process(new IndentContLineReader(
					new InputStreamReader(System.in)));
		else for (int i=0; i<av.length; i++)
			try {
				r.process(new IndentContLineReader(new FileReader(av[i])));
			} catch (FileNotFoundException e) {
				System.err.println(e);
			}
    }

    /** process one file, given an open LineReader */
    public void process(LineNumberReader is) {
		Stack theStack = new Stack();
        try {
            String line;

            while ((line = is.readLine()) != null) {
				// If line begins with "Received:", add it to the stack.
				if (line.startsWith("Received:")) {
					Debug.println("match", "Adding " + line);
					theStack.push(line);
				}
				// If we get a null line we're at the end of the header,
				// just print the last line. Then return, our task is done.
				if (line.length() == 0) {
					if (theStack.size() == 0) {
						System.err.println("No Received headers found");
						return;
					}
					Debug.println("end", "Popping " + theStack.peek());
					System.out.println(theStack.pop());
					return;
				}
            }
            is.close();
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }
    }
}
