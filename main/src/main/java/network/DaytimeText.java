package network;

import java.io.*;
import java.net.*;

/**
 * DaytimeText - connect to the standard Daytime (ascii) service.
 * @author Ian F. Darwin
 */
// tag::main[]
public class DaytimeText {
	public static final short TIME_PORT = 13;

	public static void main(String[] argv) {
		String server_name = argv.length == 1 ? argv[0] : "localhost";

		try (Socket sock = new Socket(server_name,TIME_PORT);
			BufferedReader is = new BufferedReader(new 
				InputStreamReader(sock.getInputStream()));) {
			String remoteTime = is.readLine();
			System.out.println("Time on " + server_name + " is " + remoteTime);
		} catch (IOException e) {
			System.err.println(e);
		}
	}
}
// end::main[]
