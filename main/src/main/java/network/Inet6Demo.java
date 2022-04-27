package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Inet6Address;
import java.net.Socket;

public class Inet6Demo {
	/** A well-known port that often listens on IPV4 and IPV6 */
	public final static int DAYTIME_PORT = 13;

	/** The server host */
	private final static String HOST = "darian6";

	public static void main(String[] args) throws IOException {

		Inet6Address server = (Inet6Address) Inet6Address.getByName(HOST);
		System.out.println("Lookup: " + server);

		Socket clientSocket;
		// clientSocket = new Socket(server, DAYTIME_PORT);
		clientSocket = new Socket("fe80::203:93ff:fe07:3ef6", DAYTIME_PORT);
		System.out.println(clientSocket);

		BufferedReader is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		String dateString = is.readLine();
		System.out.println(dateString);
		is.close();
		clientSocket.close();
	}
}
