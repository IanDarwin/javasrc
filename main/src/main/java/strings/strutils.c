#include <stdlib.h>
#include <stdio.h>
#include <string.h>

/* revstr - using parallel char[] */
char *revNewArray(char *s) {
	int n = strlen(s);
	char *s2 = malloc(strlen(s) + 1);
	while (*s) {
		s2[n-- -1] = *s++;
	}
	return s2;
}
/* revstr - in-place, using pointer notation instead of [] notation */
char *revInPlace(char *s) {
	s = strdup(s);	// bonus: original may be non-writable
	const int length = strlen(s) - 1;
	int i;
	for (i = 0; i < length/2; i++ ) {
		char c = *(s+i);
		*(s+i) = *(s + length - i);
		*(s + length - i) = c;
	}
	return s;
}
	
int main(int argc, char ** argv) {
        puts(revNewArray("HelloX"));
	puts(revInPlace("HelloX"));
}

