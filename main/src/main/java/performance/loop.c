#include <stdio.h>

int call(int i);

/*
 * simple timing tester.
 * to compile: cc -O0 loop.c -o loop
 * to run: time ./loop
 */
int main(int argc, char **argv) {
	int i;
	for (i = 0; i < 10000000; i++) {
		call(i);
	}
}

int call(int i) {
	return ++i;
}
