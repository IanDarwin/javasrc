import java.io.*;
import java.net.*;

/**
 * DaytimeBinary - connect to the Daytime (ascii) service.
 * @author Ian F. Darwin
 * @version $Id$
 */
public class DaytimeBinary {
	/** The TCP port for the binary time service. */
	public final short TIME_PORT = 37;
	/** Seconds between 1970, the time base for Date(long) and Time.
	 * Factors in leap years, hours, minutes, and seconds. */
	public final int BASE_DIFF = (int)(1970-1900)*365.25*24*60*60;

	public static void main(String argv[]) {
		String hostName;
		if (argv.length == 0)
			hostName = "localhost";
		else
			hostName = argv[0];

		try {
			Socket sock = new Socket(hostName, TIME_PORT);
			DataInputStream is = new DataInputStream(new 
				BufferedReader(sock.getInputStream()));
			int remoteTime = is.readInt();
			Date d = new Date(remoteTime);
			System.out.println("Time on " + hostName + " is " + remoteTime);
		} catch (IOException e) {
			System.err.println(e);
		}
	}
}
