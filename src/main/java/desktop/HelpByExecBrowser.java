import java.io.*;

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
	 * On Win32, we use the "start" command.
	 * For UNIX, we'll try for Netscape or HotJava in the user's path.
	 * For the Mac, not sure what we'll do.
	 */
	public void help() throws IOException {

		// A Runtime object has methods for dealing with the OS
		Runtime r = Runtime.getRuntime();
		// A process object tracks one external running process
		Process p;
		
		// Start Netscape from Java Applications (not Applets though)
		p = r.exec("c:/windows/command/start.exe HelpDemo.htm");

		return;
	}
}
