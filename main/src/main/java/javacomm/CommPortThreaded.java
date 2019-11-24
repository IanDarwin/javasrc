package javacomm;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;

/**
 * This program tries to do I/O in each direction using a separate Thread.
 * @author	Ian F. Darwin, http://www.darwinsys.com/
 */
public class CommPortThreaded extends CommPortOpen {

	public static void main(String[] ap) throws Exception {
		CommPortThreaded cp;
		try {
			cp = new CommPortThreaded();
			cp.converse();
		} catch(Exception e) {
			System.err.println("You lose!");
			System.err.println(e);
		}
	}

	public CommPortThreaded() throws Exception {
		super(null);
	}

	/** This version of converse() just starts a Thread in each direction.
	 */
	protected void converse() throws IOException {

		new DataThread(is, System.out).start();
		new DataThread(new DataInputStream(System.in), os).start();
	}

	/** This inner class handles one side of a conversation. */
	class DataThread extends Thread {
		BufferedReader inStream;
		PrintStream pStream;

		/** Construct this object */
		DataThread(InputStream is, PrintStream os) {
			inStream = new BufferedReader(new InputStreamReader(is));
			pStream = os;
		}
		DataThread(BufferedReader is, PrintStream os) {
			inStream = is;
			pStream = os;
		}

		/** A Thread's run method does the work. */
		public void run() {
			byte ch = 0;
			try {
				while ((ch = (byte)inStream.read()) != -1)
					pStream.print((char)ch);
			} catch (IOException e) {
				System.err.println("Input or output error: " + e);
				return;
			}
		}
	}
}
