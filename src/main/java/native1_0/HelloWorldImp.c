#include <StubPreamble.h>
#include "HelloWorld.h"
#include <stdio.h>

void
HelloWorld_displayHelloWorld(struct HHelloWorld *this_h)
{
	ClassHelloWorld *this;

	printf("Hello from a Native Method\n");
	if (this_h == NULL) {
		fprintf(stderr, "Input pointer is null!\n");
		return;
	}
	this = unhand(this_h);
	printf("Input value is %d\n", this->MyNumber++);
	printf("Input value now %d\n", this->MyNumber);
	return;
}
