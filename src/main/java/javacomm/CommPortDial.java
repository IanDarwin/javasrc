import java.io.*;
import javax.comm.*;
import java.util.*;

/**
 * Dial a phone using the Java Communications Package.
 *
 * @author	Ian F. Darwin, ian@darwinsys.com
 */
public class CommPortDial extends CommPortOpen {
	protected static String number = "000-0000";

	public static void main(String ap[])
		throws IOException, NoSuchPortException,PortInUseException {
		if (ap.length == 1)
			number = ap[0];
		new CommPortDial().converse();
		System.exit(0);
	}

	protected void converse() throws IOException {

		String resp;		// the modem response.

		// Send the reset command
		os.println("ATZ");

		// Read the echo of it.
		resp = is.readLine();
		System.out.println("Modem echoed " + resp);

		// an extra blank line - is it a CRLF issue?
		resp = is.readLine();
		System.out.println("Modem replied " + resp);

		os.println("ATDT" + number);

		// This will get an OK prompt if there's a modem there:
		resp = is.readLine();
		System.out.println("Modem replied " + resp);

		is.close();
		os.close();
	}
}
