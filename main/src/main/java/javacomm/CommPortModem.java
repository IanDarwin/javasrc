package javacomm;

import java.io.IOException;

import javax.swing.JFrame;

/**
 * Subclasses CommPortOpen and adds send/expect handling for dealing
 * with Hayes-type modems.
 *
 * @author	Ian F. Darwin, http://www.darwinsys.com/
 */
public abstract class CommPortModem extends CommPortOpen {

	/** The last line read from the serial port. */
	protected String response;

	/** A flag to control debugging output. */
	protected boolean debug = true;

	public CommPortModem(JFrame f)
		throws Exception {
		super(f);
	}

	/** Send a line to a PC-style modem. Send \r\n, regardless of
	 * what platform we're on, instead of using println().
	 */
	protected void send(String s) throws IOException {
		if (debug) {
			System.out.print(">>> ");
			System.out.print(s);
			System.out.println();
		}
		os.print(s);
		os.print("\r\n");

		// Expect the modem to echo the command.
		if (!expect(s)) {
			System.err.println("WARNING: Modem did not echo command.");
		}

		// The modem sends an extra blank line by way of a prompt.
		// Here we read and discard it.
		String junk = is.readLine();
		if (junk.length() != 0) {
			System.err.print("Warning: unexpected response: ");
			System.err.println(junk);
		}
	}

	/** Read a line, saving it in "response". 
	 * @return true if the expected String is contained in the response, false if not.
	 */
	protected boolean expect(String exp) throws IOException {
		response = is.readLine();
		if (debug) {
			System.out.print("<<< ");
			System.out.print(response);
			System.out.println();
		}
		return response.indexOf(exp) >= 0;
	}
}
