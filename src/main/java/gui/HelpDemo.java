package gui;

import java.io.IOException;

/**
 * ExecDemoHelp shows how to use the Win32 "start" command to load a help file.
 * Written long before JavaHelp API, which probably should be use instead.
 */
public class HelpDemo {
	/** A simple main program, to show calling help(). */
	public static void main(String[] av) throws IOException { 
		new HelpDemo().help();
		return;
	}

	/** 
	 * help() -- start a help viewer.
	 * This is platform-dependent; you should probably use the Java
	 * Desktop Integration Components API (JDIC).
	 */
	@Deprecated
	public void help() throws IOException {

		// A Runtime object has methods for dealing with the OS
		Runtime r = Runtime.getRuntime();
		
		// Start Netscape from Java Applications (not Applets though)
		r.exec("c:/windows/command/start.exe HelpDemo.htm");

		return;
	}
}
