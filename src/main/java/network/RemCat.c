/* Remote cat client using TFTP server (UDP socket implementation).
   UNIX Version -- Solution
   Usage: rcat hostname filename
*/

#include <sys/types.h>
#include <sys/fcntl.h>
#include <netinet/in.h>
#include <sys/socket.h>
#include <netdb.h>
#include <stdio.h>

#define TFTP_PORT	69	/* tftps well-known port number */
#define BSIZE		600	/* size of our data buffer */
#define MODE		"octet"

#define OP_RRQ		1	/* TFTP op-codes */
#define OP_DATA		3
#define OP_ACK		4
#define	OP_ERROR	5

main(int argc, char *argv[])
{
    int    sock;			/* Socket descriptor */
    struct sockaddr_in server;		/* Servers address  */
    struct sockaddr_in client;		/* Clients address  */
    struct hostent *host;		/* Server host info  */
    char   buffer[BSIZE], *p;
    int    count, server_len;

    if (argc != 3) {
	fprintf(stderr, "usage: %s hostname filename\n", argv[0]);
	exit(1);
    }

    /* Create a datagram socket */
    sock = socket(AF_INET, SOCK_DGRAM, 0);

    /* Get the servers address */
    host = gethostbyname(argv[1]);
    if (host == NULL) {
	fprintf(stderr, "unknown host: %s\n", argv[1]);
	exit(2);
    }
    server.sin_family      = AF_INET;
    memcpy(&server.sin_addr.s_addr, host->h_addr, host->h_length);
    server.sin_port        = htons(TFTP_PORT);

    /* Build a tftp Read Request packet. This is messy because the
       fields have variable length, so we cant use a structure.
    */
    *(short *)buffer = htons(OP_RRQ);	/* The op-code   */
    p = buffer + 2;
    strcpy(p, argv[2]);			/* The file name */
    p += strlen(argv[2])    + 1;	/* Keep the nul  */
    strcpy(p, MODE);			/* The Mode      */
    p += strlen(MODE) + 1;

    /* Send Read Request to tftp server */
    count = sendto(sock, buffer, p-buffer, 0,
                   (struct sockaddr *)&server, sizeof server);

    /* Loop, collecting data packets from the server, until a short
       packet arrives. This indicates the end of the file.
    */
    do {
	server_len = sizeof server;
	count = recvfrom(sock, buffer, BSIZE, 0,
                         (struct sockaddr *)&server, &server_len);
	if (ntohs(*(short *)buffer) == OP_ERROR) {
	    fprintf(stderr, "rcat: %s\n", buffer+4);
	}
	else {
	    write(1, buffer+4, count-4);
	    /* Send an ack packet. The block number we want to ack is
	       already in the buffer so we just need to change the
	       opcode. Note that the ACK is sent to the port number
	       which the server just sent the data from, NOT to port
	       69
	    */
	    *(short *)buffer = htons(OP_ACK);
	    sendto(sock, buffer, 4, 0, (struct sockaddr *)&server,
                   sizeof server);
	}
    } while (count == 516);
}

