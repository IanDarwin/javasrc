package network;

import java.io.*;
import java.net.*;

/**
 * Simple UDP client - contact the standard ascii time service
 * @author Ian Darwin, http://www.darwinsys.com/.
 */
// BEGIN main
public class DaytimeUDP {
	/** The UDP port number */
	public final static int DAYTIME_PORT = 13;

	/** A buffer plenty big enough for the date string */
	protected final static int PACKET_SIZE = 100;

	/** The main program that drives this network client.
	 * @param argv[0] hostname, running daytime/udp server
	 */
	public static void main(String[] argv) throws IOException {
		if (argv.length < 1) {
			System.err.println("usage: java DayTimeUDP host");
			System.exit(1);
		}
		String host = argv[0];
		InetAddress servAddr = InetAddress.getByName(host);
		DatagramSocket sock = new DatagramSocket();
		//sock.connect(servAddr, DAYTIME_PORT);
		byte[] buffer = new byte[PACKET_SIZE];

		// The udp packet we will send and receive
		DatagramPacket packet = new DatagramPacket(
			buffer, PACKET_SIZE, servAddr, DAYTIME_PORT);

		/* Send empty max-length (-1 for null byte) packet to server */
		packet.setLength(PACKET_SIZE-1);
		sock.send(packet);
		System.out.println("Sent request");

		// Receive a packet and print it.
		sock.receive(packet);
		System.out.println("Got packet of size " + packet.getLength());
		System.out.print("Date on " + host + " is " + 
			new String(buffer, 0, packet.getLength()));
		
		sock.close();
	}
}
// END main
