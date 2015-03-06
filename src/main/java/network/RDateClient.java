package network;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * DaytimeBinary - connect to the standard Time (binary) service.
 * N.B. This is an 'rdate' client by another name.
 * @author Ian F. Darwin
 */
// BEGIN main
public class RDateClient {
	/** The TCP port for the binary time service. */
	public static final short TIME_PORT = 37;
	/** Seconds between 1970, the time base for Date(long) and Time.
	 * Factors in leap years (up to 2100), hours, minutes, and seconds.
	 * Subtract 1 day for 1900, add in 1/2 day for 1969/1970.
	 */
	protected static final long BASE_DAYS = 
		(long)((1970-1900)*365 + (1970-1900-1)/4);

	/* Seconds since 1970 */
	public static final long BASE_DIFF = (BASE_DAYS * 24 * 60 * 60);

	/** Convert from seconds to milliseconds */
	public static final int MSEC = 1000;

	public static void main(String[] argv) {
		String hostName;
		if (argv.length == 0)
			hostName = "localhost";
		else
			hostName = argv[0];

		try (Socket sock = new Socket(hostName,TIME_PORT);) {
			DataInputStream is = new DataInputStream(new 
				BufferedInputStream(sock.getInputStream()));
			// Read 4 bytes from the network, unsigned.
			// Do it yourself; there is no readUnsignedInt().
			// Long is 8 bytes on Java, but we are using the
			// existing time protocol, which uses 4-byte ints.
			long remoteTime = (
				((long)(is.readUnsignedByte()) << 24) |
				((long)(is.readUnsignedByte()) << 16) |
				((long)(is.readUnsignedByte()) <<  8) |
				((long)(is.readUnsignedByte()) <<  0));
			System.out.println("Remote time is " + remoteTime);
			System.out.println("BASE_DIFF is " + BASE_DIFF);
			System.out.println("Time diff == " + (remoteTime - BASE_DIFF));
			Date d = new Date((remoteTime - BASE_DIFF) * MSEC);
			System.out.println("Time on " + hostName + " is " + d.toString());
			System.out.println("Local date/time = " + LocalDateTime.now());
		} catch (IOException e) {
			System.err.println(e);
		}
	}
}
// END main
