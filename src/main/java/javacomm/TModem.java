package javacomm;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * a tiny version of Ward Christensen's MODEM program for UNIX. 
 * Written ~ 1980 by Andrew Scott Beals. Last revised 1982.
 * A.D. 2000 - dragged from the archives for use in Java Cookbook.
 *
 * @author C version by Andrew Scott Beals, sjobrg.andy%mit-oz@mit-mc.arpa.
 * @author Java version by Ian F. Darwin, http://www.darwinsys.com/
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
	protected PrintWriter errStream;

	/** Construct a TModem */
	public TModem(InputStream is, OutputStream os, PrintWriter errs) {
		inStream = is;
		outStream = os;
		errStream = errs;
	}

	/** Construct a TModem with default files (stdin and stdout). */
	public TModem() {
		inStream = System.in;
		outStream = System.out;
		errStream = new PrintWriter(System.err);
	}

	/** A main program, for direct invocation. */
	public static void main(String[] argv) throws 
		IOException, InterruptedException {

		/* argc must == 2, i.e., `java TModem -s filename' */
		if (argv.length != 2) 
			usage();

		if (argv[0].charAt(0) != '-')
			usage();

		TModem tm = new TModem();
		tm.setStandalone(true);

		boolean OK = false;
		switch (argv[0].charAt(1)){
		case 'r': 
			OK = tm.receive(argv[1]); 
			break;
		case 's': 
			OK = tm.send(argv[1]); 
			break;
		default: 
			usage();
		}
		System.out.print(OK?"Done OK":"Failed");
		System.exit(0);
	}

	/* give user minimal usage message */
	protected static void usage()
	{
		System.err.println("usage: TModem -r/-s file");
		// not errStream, not die(), since this is static.
		System.exit(1);
	}

	/** If we're in a standalone app it is OK to System.exit() */
	protected boolean standalone = false;
	public void setStandalone(boolean is) {
		standalone = is;
	}
	public boolean isStandalone() {
		return standalone;
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
		
		public void run() {
	      try {
		    Thread.sleep(milliseconds);
		  } catch (InterruptedException e) {
		  	// can't happen
		  }
		  /** Implement the timer */
		  if (!gotChar)
			errStream.println("Timed out waiting for " + message);
			die(1);
		}
	}

	/*
	 * send a file to the remote
	 */
	public boolean send(String tfile) throws IOException, InterruptedException
	{
		char checksum, index, blocknumber, errorcount;
		byte character;
		byte[] sector = new byte[SECSIZE];
		int nbytes;
		DataInputStream foo;

		foo = new DataInputStream(new FileInputStream(tfile));
		errStream.println( "file open, ready to send");
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

		errStream.println( "transmission beginning");
		if (errorcount == MAXERRORS) {
			xerror();
		}

		while ((nbytes=inStream.read(sector))!=0) {
			if (nbytes<SECSIZE)
				sector[nbytes]=CPMEOF;
			errorcount = 0;
			while (errorcount < MAXERRORS) {
				errStream.println( "{" + blocknumber + "} ");
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
		errStream.println( "Transmission complete.");
		return true;
	}

	/*
	 * receive a file from the remote
	 */
	public boolean receive(String tfile) throws IOException, InterruptedException
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

		errStream.println("Starting receive...");
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
						errStream.println( "Not SOH");
						if (++errorcount < MAXERRORS)
							continue rxLoop;
						else
							xerror();
					}
					character = getchar();
					not_ch = (byte)(~getchar());
					errStream.println( "[" +  character + "] ");
					if (character != not_ch) {
						errStream.println( "Blockcounts not ~");
						++errorcount;
						continue rxLoop;
					}
					if (character != blocknumber) {
						errStream.println( "Wrong blocknumber");
						++errorcount;
						continue rxLoop;
					}
					checksum = 0;
					for (index = 0; index < SECSIZE; index++) {
						sector[index] = getchar();
						checksum += sector[index];
					}
					if (checksum != getchar()) {
						errStream.println( "Bad checksum");
						errorcount++;
						continue rxLoop;
					}
					putchar(ACK);
					blocknumber++;
					try {
						foo.write(sector);
					} catch (IOException e) {
						errStream.println("write failed, blocknumber " + blocknumber);
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

		errStream.println("Receive Completed.");
		return true;
	}

	protected byte getchar() throws IOException {
		return (byte)inStream.read();
	}

	protected void putchar(int c) throws IOException {
		outStream.write(c);
	}

	protected void xerror()
	{
		errStream.println("too many errors...aborting");
		die(1);
	}

	protected void die(int how)
	{
		if (standalone)
			System.exit(how);
		else throw new ProtocolBotchException("Error code " + how);
	}
}
