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
	protected final int MAXERRORS = 10;		/* max number of times to retry one block */
	protected final int SECSIZE	= 128;		/* cpm sector, transmission block */
	protected final int SENTIMOUT = 30;		/* timeout time in send */
	protected final int	SLEEP	= 30;		/* timeout time in recv */

	/* Protocol characters used */

	protected final byte	SOH	= 1;	/* Start Of Header */
	protected final byte	EOT	= 4;	/* End Of Transmission */
	protected final byte	ACK	= 6;	/* ACKnowlege */
	protected final byte	NAK	= 0x15;	/* Negative AcKnowlege */

	public static void main(String[] argv) throws 
		IOException, InterruptedExeption {

		/* argc must == 2, i.e., `tmodem -s filename' */
		if (argc != 2) 
			usage();

		if (!argv[0].startsWith('-')
			usage();
		switch (argv[0].charAt(1)){
		case 'r': 
			rec(argv[1]); 
			break;
		case 's': 
			sen(argv[1]); 
			break;
		default: 
			usage();
		}
		die(0);
	}

	/*
	 * send a file to the remote
	 */
	sen(String tfile) throws IOException, InterruptedExeption
	{

		register uchar checksum, index, blocknumber, errorcount;
		uchar sector[SECSIZE];
		int foo, nbytes;

		if ((foo = open(tfile, 0)) == -1) {
			(void) fprintf(stderr, "can't open %s for send!\r\n", tfile);
			die(1);
		}
		(void) fprintf(stderr, "file open, ready to send\r\n");
		errorcount = 0;
		blocknumber = 1;
		(void) signal(SIGALRM, timeout);
		(void) alarm(SENTIMOUT);

		while ((getchar() != NAK) && (errorcount < MAXERRORS))
			++errorcount;
		(void) alarm(0);
	#ifdef DEBUG
		(void) fprintf(stderr, "transmission beginning\r\n");
	#endif
		if (errorcount == MAXERRORS) {
			xerror();
		}
		while (nbytes=read(foo, sector, sizeof sector)) {
			if (nbytes<SECSIZE)
				sector[nbytes]=CPMEOF;
			errorcount = 0;
			while (errorcount < MAXERRORS) {
	#ifdef DEBUG
				(void) fprintf(stderr, "{%d} ", blocknumber);
	#endif
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
		index = 1;
		while (index) {
			putchar(EOT);
			index = getchar() == ACK;
		}
		(void) fprintf(stderr, "Transmission complete.\r\n");
	}

	/*
	 * receive a file from the remote
	 */
	rec(String tfile) throws IOException, InterruptedExeption
	{
		register uchar checksum, index, blocknumber, errorcount, character;
		uchar sector[SECSIZE];
		int foo;

		if ((foo = creat(tfile, 0666)) == -1) {
			perror(tfile);
			die(1);
		}
		printf("you have %d seconds...",SLEEP);
		(void) sleep(SLEEP);	/* wait for the user to get his act together */
	#ifdef DEBUG
		(void) fprintf(stderr, "Starting...\r\n");
	#endif
		putchar(NAK);
		errorcount = 0;
		blocknumber = 1;
		while ((character = getchar()) != EOT) {
			register uchar not_ch;
			if (character != SOH) {
	#ifdef DEBUG
				(void) fprintf(stderr, "Not SOH\r\n");
	#endif
				if (++errorcount < MAXERRORS)
					goto nakit;
				else
					xerror();
			}
			character = getchar();
			not_ch = ~getchar();
	#ifdef DEBUG
			(void) fprintf(stderr, "[%d] ", character);
	#endif
			if (character != not_ch) {
	#ifdef DEBUG
				(void) fprintf(stderr, "Blockcounts not ~\r\n");
	#endif
				++errorcount;
				goto nakit;
			}
			if (character != blocknumber) {
	#ifdef DEBUG
				(void) fprintf(stderr, "Wrong blocknumber\r\n");
	#endif
				++errorcount;
				goto nakit;
			}
			checksum = 0;
			for (index = 0; index < SECSIZE; index++) {
				sector[index] = getchar();
				checksum += sector[index];
			}
			if (checksum != getchar()) {
	#ifdef DEBUG
				(void) fprintf(stderr, "Bad checksum\r\n");
	#endif
				errorcount++;
				goto nakit;
			}
			putchar(ACK);
			blocknumber++;
			if (write(foo, sector, sizeof sector) != sizeof sector)
				warn("write failed, blocknumber %d", blocknumber);
			if (!errorcount)
				continue;
	nakit:
			putchar(NAK);
		}
		(void) close(foo);

		putchar(ACK);	/* tell the other end we accepted his EOT 	*/
		putchar(ACK);
		putchar(ACK);

		(void) fprintf(stderr, "Completed.\r\n");
	}


	/* give message that we timed out, and then die */
	static void timeout(int signum)
	{
		(void) fprintf(stderr, "Timed out waiting for NAK from remote system\r\n");
		die(1);
	}

	/* give user minimal usage message */
	usage()
	{
		(void) fprintf(stderr,"usage: %s -r/-s file\n", progname);
		exit(1);
	}

	xerror()
	{
		(void) fprintf(stderr, "too many errors...aborting\r\n");
		die(1);
	}

	die(int how)
	register int how;
	{
		System.exit(how);
	}
}
