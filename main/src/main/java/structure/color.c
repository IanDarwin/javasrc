#include <stdio.h>

/*
 * Little program to show what can go wrong with enums in C.
 */

typedef enum { BLACK, RED, ORANGE} COLOR;
typedef enum { READ = 100, UNREAD = 99 } STATE;

void printColor(COLOR c) {
	switch (c) {
	case BLACK: printf("black\n"); break;
	case RED: printf("red\n"); break;
	case ORANGE: printf("orange\n"); break;
	default:
		printf("CAN'T HAPPEN: unknown color %d\n", c);
		break;
	}
}

/*ARGSUSED*/
int main(int argc, char *argv[]) {
	COLOR color;
	color = RED;
	printColor(color);
	color = READ;	/* small typo will have big consequences in C */
	printColor(color);
	return 0;
}



