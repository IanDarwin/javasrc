package signals;

import java.io.*;

/**
 * A trivial class to show usage of Ian Darwin's signal handler.
 */
public class DemoMain extends Thread {

	// Application main, instantiate and call its display method
	public static void main(String[] args) {
		System.out.println("DemoMain starting; args.length="+
		 	args.length+"...");

		DemoMain tm = new DemoMain();
		tm.openFiles();
		LibSignal.setSignalHandler(tm);		// Pass in the Runnable

		// Maybe we don't need to sleep, as we have
		// created a non-daemon thread and not stopped it.
		try { Thread.sleep(60*1000); } catch(InterruptedException e) { }
		System.out.println("All done, bye");
	}

	public void run() {
		System.out.println("Hey, I got into my Java Signal Catcher");
		closeFiles();
		System.exit(1);
	}

	PrintWriter oaf;

	private void openFiles() {
		System.out.println("Opening files");
		try {
			oaf = new PrintWriter(new FileWriter("test.dat"));
			oaf.println("This was written; did it get flushed?");
		} catch(IOException e) {
			System.out.println("IO Error" + e);
		}
	}

	private void closeFiles() {
		System.out.println("Closing files");
		oaf.close();
	}


	// Static code blocks are executed once, when class file is loaded
	static {
		System.load("libsignal.so");
	}
}
