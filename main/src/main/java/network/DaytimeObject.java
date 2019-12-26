package network;

import java.io.*;
import java.net.*;
import java.time.*;

/**
 * DaytimeObject - connect to the non-standard Time (LocalDateTime object) service.
 * @author Ian F. Darwin
 */
public class DaytimeObject {
	/** The TCP port for my object time service. */
	public static final short TIME_PORT = 1951;

	public static void main(String[] argv) {
		String hostName = argv.length == 0 ? "localhost" : argv[0];

		// Tag here as we only need to show diff from prior example
		// tag::main[]
		try (Socket sock = new Socket(hostName, TIME_PORT);) {
			ObjectInputStream is = new ObjectInputStream(new 
				BufferedInputStream(sock.getInputStream()));

			// Read and validate the Object
			Object o = is.readObject();
			if (o == null) {
				System.err.println("Read null from server!");
			} else if ((o instanceof LocalDateTime)) {

				// Valid, so cast to LocalDateTime, and print
				LocalDateTime d = (LocalDateTime) o;
				System.out.println("Server host is " + hostName);
				System.out.println("Time there is " + d.toString());

			} else {
				throw new IllegalArgumentException(
					String.format("Wanted LocalDateTime, got %s, a %s", o, o.getClass()));
			}
		// end::main[]
		} catch (ClassNotFoundException e) {
			throw new IllegalArgumentException("ERROR: Can't load java.time.LocalDateTime!!");
		} catch (IOException e) {
			throw new IllegalArgumentException("ERROR: Can't read LocalDateTime from server!");
		}
	}
}
