#include <stdio.h>
#include <sys/time.h>
/**
 * Timer for standalone, line-mode application.
 */
int main(int argc, char* argv[]) {
		time_t t0, t1;
		t0=time(NULL);
		printf("C Starts at %ld\n", t0);
		double k;
		for (int i=0; i<1000000; i++)
			k = 2.1 * sqrt((double)i);
		t1=time(NULL);
		printf("C Ends at %ld\n", t0);
		printf("Time=%ld\n", t1-t0);
	}
}
