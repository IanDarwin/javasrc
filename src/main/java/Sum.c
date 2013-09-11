#include <stdio.h>

/**
 * Compute a simple checksum from a file.
 */
void process(char* fname, FILE *is) {
	short sum = 1;		/* the checkSum */
	long count = 0;		/* the byte count */
	int b;				/* the byte read from the file */

	while ((b = getc(is)) != EOF) {
		sum += (short)b;
		++count;
	}
	printf("%d %d %s\n", sum, fmt(count), fname);
}

int
fmt(long c) {
	int t = (int)(c/512);
	if ((c%512) != 0)
		t++;
	return t;
}

int
main(int argc, char* av[]) {
	int i;
	switch(argc) {
		case 0: abort();
			break;
		case 1: process("Stdin", stdin);
			break;
		default:
			for (i=1; i<argc; i++) {
				FILE *f = fopen(av[i], "r");
				process(av[i], f);
				close(f);
			}
			break;
	}
	return 0;
}

