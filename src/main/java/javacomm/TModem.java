import java.io.*;
import java.text.*;

/**
 * a tiny version of Ward Christensen's MODEM program for UNIX. Written ~ 1980.
 * A.D. 2000 - dragged from the archives for use in Java Cookbook.
 *
 * @author C version by Andrew Scott Beals, sjobrg.andy%mit-oz@mit-mc.arpa, 1982
 * @author Java version by Ian F. Darwin, ian@darwinsys.com
 * $Id$
 */
public class TModem {

	protected final byte CPMEOF = 26;		/* control/z */
	protected final int MAXERRORS = 10;		/* max times to retry one block */
	protected final int SECSIZE	= 128;		/* cpm sector, transmission block */
	protected final int SENTIMOUT = 30;		/* timeout time in send */
	protected final int	SLEEP	= 30;		/* timeout time in recv */

	/* Protocol characters used */

	protected final byte	SOH	= 1;	/* Start Of Header */
	protected final byte	EOT	= 4;	/* End Of Transmission */
	protected final byte	ACK	= 6;	/* ACKnowlege */
	protected final byte	NAK	= 0x15;	/* Negative AcKnowlege */

	protected InputStream inStream;
	protected OutputStream outStream;

	/** Construct a TModem */
	public TModem(InputStream is, OutputStream os) {
		inStream = is;
		outStream = os;
	}

	/** Construct a TModem with default files (stdin and stdout). */
	public TModem() {
		inStream = System.in;
		outStream = System.out;
	}

	/** A main program, for direct invocation. */
	public static void main(String[] argv) throws 
		IOException, InterruptedException {

		/* argc must == 2, i.e., `java TModem -s filename' */
		if (argv.length != 2) 
			usage();

		if (argv[0].charAt(0) != '-')
			usage();

		switch (argv[0].charAt(1)){
		case 'r': 
			new TModem().receive(argv[1]); 
			break;
		case 's': 
			new TModem().send(argv[1]); 
			break;
		default: 
			usage();
		}
		die(0);
	}

	/** A flag used to communicate with inner class IOTimer */
	protected boolean gotChar;

	/** An inner class to provide a read timeout for alarms. */
	class IOTimer extends Thread {
		String message;
		long milliseconds;

		/** Construct an IO Timer */
		IOTimer(long sec, String mesg) {
			milliseconds = 1000 * sec;
			message = mesg;
		}
		
		/** Implement the timer */
		public void run() {
			try {
				Thread.sleep(milliseconds);
			} catch (InterruptedException e) {
				// can't happen
			}
			if (!gotChar)
				System.err.println("Timed out waiting for " + message);
				die(1);
		}
	}

	/*
	 * send a file to the remote
	 */
	void send(String tfile) throws IOException, InterruptedException
	{
		char checksum, index, blocknumber, errorcount;
		byte character;
		byte[] sector = new byte[SECSIZE];
		int nbytes;
		DataInputStream foo;

		foo = new DataInputStream(new FileInputStream(tfile));
		System.err.println( "file open, ready to send");
		errorcount = 0;
		blocknumber = 1;

		// The C version uses "alarm()", a UNIX-only system call,
		// to detect if the read times out. Here we do detect it
		// by using a Thread, the IOTimer class defined above.
		gotChar = false;
		new IOTimer(SENTIMOUT, "NAK to start send").start();

		do {
			character = getchar();
			gotChar = true;
			if (character != NAK && errorcount < MAXERRORS)
				++errorcount;
		} while (character != NAK && errorcount < MAXERRORS);

		System.err.println( "transmission beginning");
		if (errorcount == MAXERRORS) {
			xerror();
		}

		while ((nbytes=inStream.read(sector))!=0) {
			if (nbytes<SECSIZE)
				sector[nbytes]=CPMEOF;
			errorcount = 0;
			while (errorcount < MAXERRORS) {
				System.err.println( "{" + blocknumber + "} ");
				putchar(SOH);	/* here is our header */
				putchar(blocknumber);	/* the block number */
				putchar(~blocknumber);	/* & its complement */
				checksum = 0;
				for (index = 0; index < SECSIZE; index++) {
					putchar(sector[index]);
					checksum += sector[index];
				}
				putchar(checksum);	/* tell our checksum */
				if (getchar() != ACK)
					++errorcount;
				else
					break;
			}
			if (errorcount == MAXERRORS)
				xerror();
			++blocknumber;
		}
		boolean isAck = false;
		while (!isAck) {
			putchar(EOT);
			isAck = getchar() == ACK;
		}
		System.err.println( "Transmission complete.");
	}


	/*
	 * receive a file from the remote
	 */
	void receive(String tfile) throws IOException, InterruptedException
	{
		char checksum, index, blocknumber, errorcount;
		byte character;
		byte[] sector = new byte[SECSIZE];
		DataOutputStream foo;

		foo = new DataOutputStream(new FileOutputStream(tfile));

		System.out.println("you have " + SLEEP + " seconds...");

		/* wait for the user or remote to get his act together */
		gotChar = false;
		new IOTimer(SLEEP, "receive from remote").start(); 

		System.err.println( "Starting...");
		putchar(NAK);
		errorcount = 0;
		blocknumber = 1;
		rxLoop:
		do { 
			character = getchar();
			gotChar = true;
			if (character != EOT) {
				try {
					byte not_ch;
					if (character != SOH) {
						System.err.println( "Not SOH");
						if (++errorcount < MAXERRORS)
							continue rxLoop;
						else
							xerror();
					}
					character = getchar();
					not_ch = (byte)(~getchar());
					System.err.println( "[" +  character + "] ");
					if (character != not_ch) {
						System.err.println( "Blockcounts not ~");
						++errorcount;
						continue rxLoop;
					}
					if (character != blocknumber) {
						System.err.println( "Wrong blocknumber");
						++errorcount;
						continue rxLoop;
					}
					checksum = 0;
					for (index = 0; index < SECSIZE; index++) {
						sector[index] = getchar();
						checksum += sector[index];
					}
					if (checksum != getchar()) {
						System.err.println( "Bad checksum");
						errorcount++;
						continue rxLoop;
					}
					putchar(ACK);
					blocknumber++;
					try {
						foo.write(sector);
					} catch (IOException e) {
						System.err.println("write failed, blocknumber " + blocknumber);
					}
				} finally {
				if (errorcount != 0)
					putchar(NAK);
			}
		}
		} while (character != EOT);

		foo.close();

		putchar(ACK);	/* tell the other end we accepted his EOT 	*/
		putchar(ACK);
		putchar(ACK);

		System.err.println( "Completed.");
	}

	byte getchar() throws IOException {
		return (byte)inStream.read();
	}

	void putchar(int c) throws IOException {
		outStream.write(c);
	}

	/* give user minimal usage message */
	static void usage()
	{
		System.err.println("usage: TModem -r/-s file");
		die(1);
	}

	void xerror()
	{
		System.err.println("too many errors...aborting");
		die(1);
	}

	static void die(int how)
	{
		System.exit(how);
	}
}
