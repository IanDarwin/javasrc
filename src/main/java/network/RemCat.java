import java.io.*;
import java.net.*;

/**
 * RemCat - remotely cat (DOS 'type') a file, using the TFTP protocol.
 * Inspired by the "rcat" exercise in Learning Tree Course 363, 
 * <I>UNIX Network Programming</I>, by Dr. Chris Brown.
 *
 * Note that the TFTP server is NOT "internationalized"; the name and
 * mode in the protocol are defined in terms of ASCII, not UniCode.
 *
 * @author	Java version by Ian Darwin, ian@darwinsys.com.
 */
public class RemCat {
	public final static int TFTP_PORT = 69;
	protected final String MODE = "octet";

	/** TFTP op-code for a read request */
	public final int OP_RRQ = 1,
		/** TFTP op-code for a read request */
		OP_WRQ = 2,
		/** TFTP op-code for a read request */
		OP_DATA	 = 3,
		/** TFTP op-code for a read request */
		OP_ACK	 = 4,
		/** TFTP op-code for a read request */
		OP_ERROR = 5;
	protected final static int PACKET = 516;	// == 2 + 2 + 512
	protected String host;
	protected InetAddress servAddr;
	protected DatagramSocket sock;
	protected byte buffer[];
	protected DatagramPacket inp, outp;

	public static void main(String argv[]) throws IOException {
		if (argv.length < 2) {
			System.err.println("usage: rcat host filename[...]");
			System.exit(1);
		}
		System.out.println("Java RemCat starting");
		RemCat rc = new RemCat(argv[0]);
		for (int i = 1; i<argv.length; i++) {
			System.out.println("-- Starting file " + argv[0] + 
				":" + argv[i] + "---");
			rc.readFile(argv[i]);
		}
	}

	RemCat(String host) throws IOException {
		super();
		this.host = host;
		servAddr = InetAddress.getByName(host);
		sock = new DatagramSocket();
		buffer = new byte[PACKET];
		inp = new DatagramPacket(buffer, PACKET);
		outp = new DatagramPacket(buffer, PACKET, servAddr, TFTP_PORT);
	}

	void readFile(String path) throws IOException {
		/* Build a tftp Read Request packet. This is messy because the
		 * fields have variable length. Numbers must be in
		 * network order, too; fortunately Java just seems 
		 * naturally smart enough :-) to use network byte order.
		 */
		buffer[0] = 0;
		buffer[1] = OP_RRQ;		// read request
		int p = 2;			// number of chars into buffer
		path.getBytes(0, path.length(), buffer, p); // file name
		p += path.length();
		buffer[p++] = 0;
		MODE.getBytes(0, MODE.length(), buffer, p); // mode
		p += MODE.length();
		buffer[p++] = 0;

		/* Send Read Request to tftp server */
		outp.setLength(p);
		sock.send(outp);

		/* Loop reading data packets from the server until a short
		 * packet arrives; this indicates the end of the file.
		 */
		int len = 0;
		do {
			sock.receive(inp);
			if (buffer[3] == OP_ERROR) {
				System.err.println("rcat: "+new String(buffer));
			}
			else {
				System.err.println("Got a packet of size " +
					inp.getLength());
				/* Ack the packet. The block number we 
				 * want to ack is already in 'buffer' so 
				 * we just change the opcode. The ACK is 
				 * sent to the port number which the server 
				 * just sent the data from, NOT to port 
				 * TFTP_PORT.
				 */
				buffer[1] = OP_ACK;
				outp.setLength(4);
				outp.setPort(inp.getPort());
				sock.send(outp);
				}
			} while (inp.getLength() == PACKET);
			System.err.println("Leaving loop, size " +
					inp.getLength());
	}
}
