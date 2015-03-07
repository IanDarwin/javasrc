package io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.EmptyStackException;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * SpamRat: The Fury.
 * Read a SPAM mail message and generate an EMail compose window to the
 * SPAM-artist's ISP.
 * Of course, you must have saved the email message using a Mail User
 * Agent (MUA, or mail reader program) that preserves all header lines!
 *
 * TODO: 0) extract the ISP's name, do host lookup if numeric,
 *	do whois netblock lookup if that fails.
 *  1) Handle root spammers (use 2nd-last Received): need a heuristic
 *  to figure these out!
 *  2) Have a FileProperties containing known "abuse" type addresses
 *	(some are abuse, some are email-abuse, etc.). And name the
 *	template file.
 *  3) Construct and show a MailComposeBean (in a JFrame) with the
 *  template filled in as much as possible.
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
public class SpamRat {

    public static void main(String[] av) throws IOException {
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

	/** Display an error message. */
	protected void error(String mesg, boolean terminate) {
		System.err.println(mesg);
		// if (myFrame != null) {
			// display it in a dialog.
		// }
		if (terminate) {
			System.exit(0);
		}
	}

	/** the name of the file we are reading */
	String spamFileName;
	/** the Reader for the file we are reading */
	LineNumberReader is;

	/** Constructor
	 * @param fileName - name of file containing a SPAM message.
	 */
	public SpamRat(String fileName) throws IOException {
		File f = new File(spamFileName = fileName);
		if (!f.isFile() || !f.canRead())
			System.err.println("Can't read file " + fileName);
		is = new IndentContLineReader(new FileReader(fileName));
	}

    /** process one file, given an open LineReader */
    public void process() {

		/* and the last shall be first, and the first shall be last */
		findReceived(is);
		for (int i=0; i<3; i++) {
			String line = (String)theStack.pop();
			System.out.println("Last-"+i+" receipt = " + line);

			// Get the IP out of it. Normal lines look like:
			// Received: from once.com (ip-08-14.teleport.com [206.163.123.238])
			//	by kim.teleport.com (8.8.5/8.7.3) with SMTP id MAA17284;
			//	Sat, 5 Jul 1997 12:48:00 -0700 (PDT)
			try {
				handle(line);
			} catch (EmptyStackException e) {
				return;
			} catch (Exception e) {
				System.out.println("** BOGUS ** " + e);
			}
		}
	}

	Stack<String> theStack;
	protected Stack<String> findReceived(LineNumberReader is) {
		theStack = new Stack<>();
        try {
            String line;

            while ((line = is.readLine()) != null &&
				line.length() > 0) {
				// If line begins with "Received:", add it to the stack.
				if (line.startsWith("Received:")) {
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
		return theStack;
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
			System.out.println("Hostname resolves as " + ia1);
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
			System.out.println("Hostname resolves as " + ia2);
		} catch (UnknownHostException e) {
			System.err.println("Warning: IP " + realIP + " did not resolve");
		}

		sendFlame(realName, realIP, fakeName);
	}

	/** Display the response in a mail compose window. */
	protected void sendFlame(String host, String IP, String fakeName) {
		System.out.println("To: abuse@" + host);
		System.out.println("Subject: SPAM from your site!");
		System.out.println("Cc: postmaster");
		System.out.println();
		System.out.println("The attached unsolicited SPAM was injected");
		System.out.println("into the Internet, according to Received: lines,");
		System.out.println("by or through you, " + host + "(" + IP + ")");
		System.out.println("pretending to be " + fakeName);
		System.out.println("Please react accordingly.");
		System.out.println();

		// TODO IOutil.copy(spamFileName, ...);
	}
}
