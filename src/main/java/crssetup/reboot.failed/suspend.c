#include <stdio.h>
#include <wintypes.h>
#include <Windows32/Defines.h>
#include <Windows32/Functions.h>

void do_suspend_95() {
	if(!SetSystemPowerState(FALSE, TRUE)){
        printf("Failed to initiate SUSPEND - Does your system support APM functions?");
	}
}

int main(int argc, char *argv[]) {
	do_suspend_95();
	return 0;
}
