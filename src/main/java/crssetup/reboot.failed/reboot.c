#include <stdio.h>
#include <wintypes.h>
#include <Windows32/Defines.h>
#include <Windows32/Functions.h>

// The reboot_95 function - for now its a main program, but it
// will become a function called from CrsSetup.

// This FAILS right now since it runs as a DOS app, isolated from
// the "real" Win32 subsystem.

// We use ExitWindowsEx from -luser32 to shutdown.

// Special thanks to "Exploder" from Fred McLain (mclain@halcyon.com)
// from which we almost figured out how to do the shutdown/reboot function
// after changing TCP/IP and other Registry settings. 
// AND to Joe Hance <joe@bysnet.com> whose excellent 
// NetSwitcher provided additional clues along the way...
// Joe also provided the interim version "rebooter" that we actually use...

void do_reboot_95() {
	UINT flags;
	// The only uncertainty is the exact flags.

	// #1
	// In theory this should do it. In fact it hangs, since the
	// program needs to be finished before the reboot will complete.
	// You need to kill the program to get out of this state.
	// flags = EWX_REBOOT;

	// #2
	// OK, next try, try FORCE and REBOOT. Quickly kills the application,
	// but does not reboot the system. Does cause Explorer restart
	// (the insipid bouncing yellow arrow).
	// flags = EWX_REBOOT | EWX_FORCE ;

	// #3
	// McLain claims you need EWX_SHUTDOWN as well, does not seem
	// to make a difference (behaves same as #2 above). I may
	// have misunderstood his mail.
	// flags = EWX_REBOOT | EWX_FORCE | EWX_SHUTDOWN;

	// #4
	// Just try a real shutdown to see if it works.
	flags = EWX_SHUTDOWN | EWX_FORCE;

	if (!ExitWindowsEx(flags, NULL)) {
		DWORD dwError = GetLastError();
		fprintf(stderr, "ExitWindowsEx() failed with error code %ld", dwError);
	}
}

int main(int argc, char *argv[]) {
	do_reboot_95();
	return 0;
}
