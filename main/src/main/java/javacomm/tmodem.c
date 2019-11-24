/*
 * a tiny version of Ward Christensen's MODEM program for UNIX
 *
 * by Andrew Scott Beals
 * sjobrg.andy%mit-oz@mit-mc.arpa
 * last update->4 june 1982
 * code reorganized, cleaned up - ian darwin, 83-05-02, updated for USG UNIX.
 * A.D. 2000 - dragged from the archives for use in Java Cookbook.
 * $Id$
 */

#include <stdio.h>
#include <ctype.h>
#include <sys/signal.h>

#ifdef	USG
#include <termio.h>
#else	USG
#include <sgtty.h>
#endif	USG
#define stty(fd,buf) ioctl(fd,TIOCSETP,buf)
#define gtty(fd,buf) ioctl(fd, TIOCGETP, buf)

#define uchar	unsigned char

#define CPMEOF	26		/* control/z */
#define MAXERRORS 10		/* max number of times to retry one block */
#define SECSIZE	128		/* cpm sector, transmission block */
#define SENTIMOUT 30		/* timeout time in send */
#define	SLEEP	30		/* timeout time in recv */

/* Protocol characters used */

#define	SOH	1	/* Start Of Header */
#define	EOT	4	/* End Of Transmission */
#define	ACK	6	/* ACKnowlege */
#define	NAK	0x15	/* Negative AcKnowlege */


#ifdef	USG
struct termio ttyhold, ttymode;
#else	USG
struct sgttyb ttyhold, ttymode;
#endif	USG

char *progname;

static void timeout(int signum);

main(argc, argv)
int argc;
char **argv;
{
	progname = argv[0];
	/* argc must == 3, i.e., `tmodem -s filename' */
	if (argc != 3) 
		usage();
#ifdef	USG
	if (ioctl(0, TCGETA, &ttymode) != 0)
		error("ioctl failed", "");
	ttyhold = ttymode;
	ttymode.c_iflag |= IGNPAR;
	ttymode.c_iflag &= ~(PARMRK | INPCK | ISTRIP | INLCR | IGNCR
	     | ICRNL | IUCLC | IXON | IXANY | IXOFF);	/* nuke input handling */
	ttymode.c_oflag = 0;	/* nuke all output special processing */
	ttymode.c_cflag &= ~PARENB;
	ttymode.c_lflag &= 
	    ~(ISIG | ICANON | XCASE | ECHO | ECHOE | ECHOK | ECHONL | NOFLSH);
#else	USG
	gtty(0, &ttymode);
	ttyhold = ttymode;
	ttymode.sg_flags |= RAW;
	ttymode.sg_flags &= ~ECHO;
#endif	USG

	if (argv[1][0] != '-')
		usage();
	switch (argv[1][1]){
	case 'r': 
		rec(argv[2]); 
		break;
	case 's': 
		sen(argv[2]); 
		break;
	default: 
		usage();
	}
	die(0);
}

/*
 * send a file to the remote
 */
sen(tfile)
char	*tfile;
{

	register uchar checksum, index, blocknumber, errorcount;
	uchar sector[SECSIZE];
	int foo, nbytes;

#ifdef	USG
	if (ioctl(0, TCSETAW, &ttymode) != 0)
		error("ioctl failed", "");
	(void) fflush(stdout);
#else	USG
	stty(0, &ttymode);
#endif	USG
	if ((foo = open(tfile, 0)) == -1) {
		(void) fprintf(stderr, "can't open %s for send!\r\n", tfile);
		die(1);
	}
	(void) fprintf(stderr, "file open, ready to send\r\n");
	(void) fflush(stdout);
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
			(void) fflush(stdout);
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
		(void) fflush(stdout);
		index = getchar() == ACK;
	}
	(void) fprintf(stderr, "Transmission complete.\r\n");
}

/*
 * receive a file from the remote
 */
rec(tfile)
char	*tfile;
{
	register uchar checksum, index, blocknumber, errorcount, character;
	uchar sector[SECSIZE];
	int foo;

#ifdef	USG
	if (ioctl(0, TCSETAW, &ttymode) != 0)
		error("ioctl failed", "");
	(void) fflush(stdout);
#else	USG
	stty(0, &ttymode);
#endif	USG

	if ((foo = creat(tfile, 0666)) == -1) {
		perror(tfile);
		die(1);
	}
	printf("you have %d seconds...",SLEEP);
	(void) fflush(stdout);
	(void) sleep(SLEEP);	/* wait for the user to get his act together */
#ifdef DEBUG
	(void) fprintf(stderr, "Starting...\r\n");
#endif
	putchar(NAK);
	(void) fflush(stdout);
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
		(void) fflush(stdout);
		blocknumber++;
		if (write(foo, sector, sizeof sector) != sizeof sector)
			warn("write failed, blocknumber %d", blocknumber);
		if (!errorcount)
			continue;
nakit:
		putchar(NAK);
		(void) fflush(stdout);
	}
	(void) close(foo);

	putchar(ACK);	/* tell the other end we accepted his EOT 	*/
	putchar(ACK);
	putchar(ACK);
	(void) fflush(stdout);

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

die(how)
register int how;
{
#ifdef	USG
	if (ioctl(0, TCSETAW, &ttyhold) != 0)
		error("ioctl failed", "");
	(void) fflush(stdout);
#else	USG
	stty(0, &ttyhold);
#endif	USG
	exit(how);
}
