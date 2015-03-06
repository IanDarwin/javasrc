package network;

import java.io.*;
import java.net.*;

/**
 * <p>
 * RemCat - remotely cat (DOS type) a file, using the TFTP protocol.
 * Inspired by the "rcat" exercise in Learning Tree Course 363, 
 * <I>UNIX Network Programming</I>, by Dr. Chris Brown.</p>
 * <[>
 * Note that the TFTP server is NOT "internationalized"; the name and
 * mode *in the protocol* are defined in terms of ASCII, not UniCode.</p>
 *
 * @author	Chris R. Brown, original C version
 * @author	Java version by Ian Darwin, http://www.darwinsys.com/.
 */
// BEGIN main
public class RemCat {
	/** The UDP port number */
	public final static int TFTP_PORT = 69;
	/** The mode we will use - octet for everything. */
	protected final String MODE = "octet";

	/** The offset for the code/response as a byte */
	protected final int OFFSET_REQUEST = 1;
	/** The offset for the packet number as a byte */
	protected final int OFFSET_PACKETNUM = 3;

	/** Debugging flag */
	protected static boolean debug = false;

	/** TFTP op-code for a read request */
	public final int OP_RRQ = 1;
	/** TFTP op-code for a read request */
	public final int OP_WRQ = 2;
	/** TFTP op-code for a read request */
	public final int OP_DATA = 3;
	/** TFTP op-code for a read request */
	public final int OP_ACK	= 4;
	/** TFTP op-code for a read request */
	public final int OP_ERROR = 5;

	protected final static int PACKET_SIZE = 516;	// == 2 + 2 + 512
	protected String host;
	protected InetAddress servAddr;
	protected DatagramSocket sock;
	protected byte buffer[];
	protected DatagramPacket inp, outp;

	/** The main program that drives this network client.
	 * @param argv[0] hostname, running TFTP server
	 * @param argv[1..n] filename(s), must be at least one
	 */
	public static void main(String[] argv) throws IOException {
		if (argv.length < 2) {
			System.err.println("usage: rcat host filename[...]");
			System.exit(1);
		}
		if (debug)
			System.err.println("Java RemCat starting");
		RemCat rc = new RemCat(argv[0]);
		for (int i = 1; i<argv.length; i++) {
			if (debug)
				System.err.println("-- Starting file " + 
					argv[0] + ":" + argv[i] + "---");
			rc.readFile(argv[i]);
		}
	}

	RemCat(String host) throws IOException {
		super();
		this.host = host;
		servAddr = InetAddress.getByName(host);
		sock = new DatagramSocket();
		buffer = new byte[PACKET_SIZE];
		outp = new DatagramPacket(buffer, PACKET_SIZE, servAddr, TFTP_PORT);
		inp = new DatagramPacket(buffer, PACKET_SIZE);
	}

	/* Build a TFTP Read Request packet. This is messy because the
	 * fields have variable length. Numbers must be in
	 * network order, too; fortunately Java just seems 
	 * naturally smart enough :-) to use network byte order.
	 */
	void readFile(String path) throws IOException {
		buffer[0] = 0;
		buffer[OFFSET_REQUEST] = OP_RRQ;		// read request
		int p = 2;			// number of chars into buffer

		// Convert filename String to bytes in buffer , using "p" as an
		// offset indicator to get all the bits of this request
		// in exactly the right spot.
		byte[] bTemp = path.getBytes();	// i.e., ASCII
		System.arraycopy(bTemp, 0, buffer, p, path.length());
		p += path.length();
		buffer[p++] = 0;		// null byte terminates string

		// Similarly, convert MODE ("stream" or "octet") to bytes in buffer
		bTemp = MODE.getBytes();	// i.e., ASCII
		System.arraycopy(bTemp, 0, buffer, p, MODE.length());
		p += MODE.length();
		buffer[p++] = 0;		// null terminate

		/* Send Read Request to tftp server */
		outp.setLength(p);
		sock.send(outp);

		/* Loop reading data packets from the server until a short
		 * packet arrives; this indicates the end of the file.
		 */
		do {
			sock.receive(inp);
			if (debug)
				System.err.println(
					"Packet # " + Byte.toString(buffer[OFFSET_PACKETNUM])+
					"RESPONSE CODE " + Byte.toString(buffer[OFFSET_REQUEST]));
			if (buffer[OFFSET_REQUEST] == OP_ERROR) {
				System.err.println("rcat ERROR: " +
					new String(buffer, 4, inp.getLength()-4));
				return;
			}
			if (debug)
				System.err.println("Got packet of size " +
					inp.getLength());

			/* Print the data from the packet */
			System.out.write(buffer, 4, inp.getLength()-4);

			/* Ack the packet. The block number we 
			 * want to ack is already in buffer so 
			 * we just change the opcode. The ACK is 
			 * sent to the port number which the server 
			 * just sent the data from, NOT to port 
			 * TFTP_PORT.
			 */
			buffer[OFFSET_REQUEST] = OP_ACK;
			outp.setLength(4);
			outp.setPort(inp.getPort());
			sock.send(outp);
		} while (inp.getLength() == PACKET_SIZE);

		if (debug)
			System.err.println("** ALL DONE** Leaving loop, last size " +
				inp.getLength());
	}
}
// END main
