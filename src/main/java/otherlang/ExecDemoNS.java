import java.io.*;

/**
 * ExecDemoNS shows how to execute a 32-bit Windows program from within Java.
 */
public class ExecDemoNS {
	public final static String HELPFILE = "/acme_widgets/help/index.htm";
	public static void main(String av[]) throws IOException { 
		// A Runtime object has methods for dealing with the OS
		Runtime r = Runtime.getRuntime();
		// A process object tracks one external running process
		Process p;
		DataInputStream is;
		String fname;
		
		// Start Netscape from Java Applications
		// A Java Applets would not be allowed to, but would not need to :-)
		p = r.exec("c:/program files/netscape/communicator/program/netscape.exe"+
			" " + "file:///C|" + HELPFILE);

		// System.out.println("In main after exec");

		try {
			p.waitFor();	// wait for process to complete
		} catch (InterruptedException e) {
			System.err.println(e);
			return;
		}
		System.out.println("In Main, process is done");
		return;
	}
}
