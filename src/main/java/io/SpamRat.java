import java.io.*;
import java.util.*;
import java.net.*;

/**
 * SpamRat: The Fury.
 * Read a SPAM mail message and generate an EMail compose window to the
 * SPAM-artist's ISP.
 * This version just prints the Received line with the offending ISP. 
 * TODO: 0) extract the ISP's name, do host lookup if numeric,
 *	do whois netblock lookup if that fails.
 *  1) Handle root spammers (use 2nd-last Received): need a heuristic
 *  to figure these out!
 *  2) Have a FileProperties containing known "abuse" type addresses
 *	(some are abuse, some are email-abuse, etc.). And name the
 *	template file.
 *  3) Construct and show a MailComposeBean (in a JFrame) with the
 *  template filled in as much as possible.
 * @author Ian F. Darwin, ian@darwinsys.com
 * @version $Id$
 */
public class SpamRat {

    public static void main(String av[]) throws IOException {
		// For stdin or each file, build a IndentContLineReader
		// to treat the Received: lines as one.
        if (av.length == 0) {
            System.err.println("Usage: SpamRat spamfile [...]");
			return;
		}
		for (int i=0; i<av.length; i++)
			try {
				new SpamRat(av[i]).process();
			} catch (FileNotFoundException e) {
				System.err.println(e);
			}
    }

	/** the name of the file we are reading */
	String spamFileName;
	/** the Reader for the file we are reading */
	LineNumberReader is;

	public SpamRat(String fName) throws IOException {
		File f = new File(spamFileName = fName);
		if (!f.isFile() || !f.canRead())
			System.err.println("Can't read file " + fName);
		is = new IndentContLineReader(new FileReader(fName));
	}

    /** process one file, given an open LineReader */
    public void process() {

		/* and the last shall be first, and the first shall be last */
		String last = findLastReceived(is);
		System.out.println("First receipt = " + last);

		// Get the IP out of it. Normal lines look like:
		// Received: from once.com (ip-pdx08-14.teleport.com [206.163.123.238])
		//	by kim.teleport.com (8.8.5/8.7.3) with SMTP id MAA17284;
		//	Sat, 5 Jul 1997 12:48:00 -0700 (PDT)
		handle(last);
	}

	protected String findLastReceived(LineNumberReader is) {
		Stack theStack = new Stack();
        try {
            String line;

            while ((line = is.readLine()) != null &&
				line.length() > 0) {
				// If line begins with "Received:", add it to the stack.
				if (line.startsWith("Received:")) {
					Debug.println("match", "Adding " + line);
					theStack.push(line);
				}
            }
            is.close();
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }

		if (theStack.size() == 0) {
			System.err.println("No Received headers found");
			return null;
		}
		Debug.println("end", "Popping " + theStack.peek());
		return (String)theStack.pop();
    }

	/** Break out lines like this:
	 * Received: from once.com (ip-pdx08-14.teleport.com [206.163.123.238])
	 *	by kim.teleport.com (8.8.5/8.7.3) with SMTP id MAA17284;
	 *	Sat, 5 Jul 1997 12:48:00 -0700 (PDT)
	 * to find the IP name/address of the victim/villian ISP.
	 */
	void handle(String rl) {
		StringTokenizer w = new StringTokenizer(rl);
		if (!w.nextToken().equalsIgnoreCase("received:"))
			throw new IllegalArgumentException("invalid received line: " + rl);
		if (!w.nextToken().equalsIgnoreCase("from"))
			throw new IllegalArgumentException(
				"missing \"from\" in received line: " + rl);

		String fakeName = w.nextToken(); // possibly-fake name

		String realName  = w.nextToken();
		if (!realName.startsWith("("))
			throw new IllegalArgumentException(
				"bad \"(name\" in received line: " + rl);
		realName = realName.substring(1);

		try {
			InetAddress ia1 = InetAddress.getByName(realName);
		} catch (UnknownHostException e) {
			System.err.println("Warning: Host name " + 
				realName + " did not resolve");
		}

		String realIP    = w.nextToken();
		if (!realIP.startsWith("["))
			throw new IllegalArgumentException(
				"bad \"[IP]\" in received line: " + rl);
		realIP = realIP.substring(1, realIP.length()-1-1);

		try {
			InetAddress ia2 = InetAddress.getByName(realIP);
		} catch (UnknownHostException e) {
			System.err.println("Warning: IP " + realIP + " did not resolve");
		}

		flame(realName, realIP, fakeName);
	}

	protected void flame(String host, String IP, String fakeName) {
		System.out.println("To: abuse@" + host);
		System.out.println("Subject: SPAM from your site!");
		System.out.println("Cc: postmaster");
		System.out.println();
		System.out.println("The attached unsolicited SPAM was injected");
		System.out.println("into the Internet, according to Received: lines,");
		System.out.println("by or through you, " + host + "(" + IP + ")");
		System.out.println("pretending to be " + fakeName);
		System.out.println("Please react accordingly.");

		// TODO IOutil.copy(spamFileName, ...);
	}
}
