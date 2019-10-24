package email;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 * "Raw" SMTP talker class, usable standalone (as a SendMail(8) backend)
 * or inside applications that need to send mail..
 *
 * Needs more parameterization - bit of a hack to start.
 * Should use same interface as Mailer (subclass, and override doSend()).
 *
 * @author	Ian Darwin
 * @deprecated Use javax.mail instead, which almost certainly provides a more
 * complete implementation of the SMTP specification.
 */
public class RawSender implements SysExits {
	BufferedReader is;
	PrintStream os;
	private boolean debug = true;
	private String host;

	/** A simple main program showing the class in action.
	 * TODO generalize to accept From arg, read msg on stdin
	 */
	public static void main(String[] argv) {
		if (argv.length != 2) {
			System.err.println("Usage: java SmtpTalk host user");
			System.exit(EX_USAGE);
		}

		try {
			RawSender st = new RawSender(argv[0]);

			System.out.println("SMTP Talker ready");

			st.converse("MAILER-DAEMON@daroad.darwinsys.com", 
				argv[1], "Test message", "Hello there");
		} catch (SMTPException ig) {
			System.err.println(ig.getMessage());
			System.exit(ig.getCode());
		}
	}

	/** Constructor taking a server hostname as argument.
	 */
	RawSender(String server) throws SMTPException {
		host = server;
		try {
			@SuppressWarnings("resource")
			Socket s = new Socket(host, 25);
			is = new BufferedReader(
				new InputStreamReader(s.getInputStream()));
			os = new PrintStream(s.getOutputStream());
		} catch (NoRouteToHostException e) {
			die(EX_TEMPFAIL, "No route to host " + host);
		} catch (ConnectException e) {
			die(EX_TEMPFAIL, "Connection Refused by " + host);
		} catch (UnknownHostException e) {
			die(EX_NOHOST, "Unknown host " + host);
		} catch (IOException e) {
			die(EX_IOERR, "I/O error setting up socket streams\n" + e);
		}
	}

	/** Send a command with an operand */
	protected void send_cmd(String cmd, String oprnd) {
		send_cmd(cmd + " " + oprnd);
	}

	/* Send a command with no operand */
	protected void send_cmd(String cmd) {
		if (debug)
			System.out.println(">>> " + cmd);
		os.print(cmd + "\r\n");
	}

	/** Send_text sends the body of the message. */
	public void send_text(String text) {
		os.print(text + "\r\n");
	}

	/** Expect (read and check for) a given reply */
	protected boolean expect_reply(String rspNum) throws SMTPException {
		String s = null;
		try {
			s = is.readLine();
		} catch(IOException e) {
			die(EX_IOERR,"I/O error reading from host " + host + " " + e);
		}
		if (debug) System.out.println("<<< " + s);
		return s.startsWith(rspNum + " ");
	}

	/** Convenience routine to print message & exit, like
	 * K&P error(), perl die(), ...
	 * @param ret Numeric value to pass back
	 * @param msg Error message to be printed on stdout.
	 */
	protected void die(int ret, String msg) throws SMTPException {
		throw new SMTPException(ret, msg);
	}

	/** send one Mail message to one or more recipients via smtp 
	 * to server "host".
	 */
	public void converse(String sender, String recipients,
		String subject, String body) throws SMTPException {

		if (!expect_reply("220")) die(EX_PROTOCOL,
			"did not get SMTP greeting");

		send_cmd("HELO", "darwinsys.com");
		if (!expect_reply("250")) die(EX_PROTOCOL,
			"did not ack our HELO");

		send_cmd("MAIL", "From:<"+sender+">");	// no spaces!
		if (!expect_reply("250")) die(EX_PROTOCOL,
			"did not ack our MAIL command");

		StringTokenizer st = new StringTokenizer(recipients);
		while (st.hasMoreTokens()) {
			String r = st.nextToken();
			send_cmd("RCPT", "To:<" + r + ">");
			if (!expect_reply("250")) die(EX_PROTOCOL,
				"didn't ack RCPT " + r);
		}
		send_cmd("DATA");
		if (!expect_reply("354")) die(EX_PROTOCOL,"did not want our DATA!");

		send_text("From: " + sender);
		send_text("To: " + recipients);
		send_text("Subject: " + subject);
		send_text("");
		send_text(body + "\r");
	
		send_cmd(".");
		if (!expect_reply("250")) die(EX_PROTOCOL,"Mail not accepted");

		send_cmd("QUIT");
		if (!expect_reply("221")) die(EX_PROTOCOL,"Other end not closing down");
	}
}
