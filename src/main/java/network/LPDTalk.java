package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/** Talk to a remote lpd aka print server over BSD LPD protocol
 */
public class LPDTalk {
	final static int  PORT = 515;	// from /etc/services
	final static char CHECK = 1;	// CTRL/A
	final static char QUEUE = 2;	// CTRL/B
	final static char LIST_SHORT = 3;	// CTRL/C
	final static char LIST_LONG = 4;	// CTRL/D
	final static char REMOVE = 5;	// CTRL/E
	final static char EOL = '\n';
	static String server = "192.168.1.250";
	Socket s;
	PrintStream out;
	BufferedReader in;
	String queue = "lp";
	
	public static void main(String[] args) throws IOException {
		if (args.length == 1)
			server = args[0];
		
		LPDTalk talk;
//		talk = new LPDTalk();
//		talk.listJobs();
//		talk.teardown();
		
		talk = new LPDTalk();
		//talk.printHelloPS();
		talk.listJobs();
		talk.teardown();
	}

	/**
	 * Set up the socket and streams
	 * * @throws IOException
	 */
	private LPDTalk() throws IOException {
		s = new Socket(server, PORT);
		out = new PrintStream(s.getOutputStream());
		in = new BufferedReader(new InputStreamReader(s.getInputStream()));
	}
	
	private void teardown() throws IOException {
		in.close();
		out.close();
		s.close();
	}
	
	/** Send a line, with EOL and flush */
	private void println(String mesg) {
		out.print(mesg);
		out.print(EOL);
		out.flush();
	}

	/** List the jobs on the print server */
	public void listJobs() throws IOException {
		println(LIST_SHORT + queue);
		String line;
		while ((line = in.readLine()) != null) {
			System.out.println("PrintServer says: " + line);
//			for (int i = 0; i < line.length(); i++) {
//				char c = line.charAt(i);
//				System.out.print(" " + (int)c);
//			}
			System.out.println();
		}
	}
}
