import java.io.*;
import java.net.*;
import java.util.*;

/**
 * DaytimeBinary - connect to the Daytime (ascii) service.
 * @author Ian F. Darwin
 * @version $Id$
 */
public class DaytimeBinary {
	/** The TCP port for the binary time service. */
	public static final short TIME_PORT = 37;
	/** Seconds between 1970, the time base for Date(long) and Time.
	 * Factors in leap years, hours, minutes, and seconds. */
	public static final long BASE_DIFF = (long)((1970-1900)*365.25*24*60*60);
	/** Convert from seconds to milliseconds */
	public static final int MSEC = 1000;

	public static void main(String argv[]) {
		String hostName;
		if (argv.length == 0)
			hostName = "localhost";
		else
			hostName = argv[0];

		try {
			Socket sock = new Socket(hostName, TIME_PORT);
			DataInputStream is = new DataInputStream(new 
				BufferedInputStream(sock.getInputStream()));
			// Unfortunately there is no readUnsignedInt method, so
			// long remoteTime = is.readInt();
			// Can't be used. Instead we must synthesize it:
			long remoteTime = (
				((long)(is.readUnsignedByte() & 0xff) << 24) |
				((long)(is.readUnsignedByte() & 0xff) << 16) |
				((long)(is.readUnsignedByte() & 0xff) <<  8) |
				((long)(is.readUnsignedByte() & 0xff) <<  0));
			System.out.println("Reme time is " + remoteTime);
			System.out.println("BASE_DIFF is " + BASE_DIFF);
			System.out.println("Time diff == " + (remoteTime - BASE_DIFF));
			Date d = new Date((remoteTime - BASE_DIFF) * MSEC);
			System.out.println("Time on " + hostName + " is " + d.toString());
		} catch (IOException e) {
			System.err.println(e);
		}
	}
}
