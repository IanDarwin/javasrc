/*
 * A simple demonstration of the code needed to setup a client connection in C.
 */

/* BEGIN main */
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <netdb.h>
#include <stdio.h>
#include <string.h>
#include <fcntl.h>

int
main(int argc, char *argv[])
{
	char* server_name = "localhost";
	struct hostent *host_info;
	int sock;
	struct sockaddr_in server;

	/* Look up the remote host's IP address */
	host_info = gethostbyname(server_name);
	if (host_info == NULL) {
		fprintf(stderr, "%s: unknown host: %s\n", argv[0], server_name);
		exit(1);
	}

	/* Create the socket */
	if ((sock = socket(AF_INET, SOCK_STREAM, 0)) < 0) {
		perror("creating client socket");
		exit(2);
	}

	/* Set up the server's socket address */
	server.sin_family = AF_INET;
	memcpy((char *)&server.sin_addr, host_info->h_addr,
				     host_info->h_length);
	server.sin_port = htons(80);

	/* Connect to the server */
	if (connect(sock,(struct sockaddr *)&server,sizeof server) < 0) {
		perror("connecting to server");
		exit(4);
	}

	/* Finally, we can read and write on the socket. */
	/* ... */

	(void) close(sock);
}
/* END main */
