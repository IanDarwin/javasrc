import java.io.*;
import java.net.*;
import java.util.*;

/** Trivial Chat Server to go with our Trivial Chat Client.
 *
 * Does not implement any form of "anonymous nicknames" - this is
 * a good thing, given how a few people have abused anonymous 
 * chat rooms in the past.
 *
 * WARNING -- this code has NOT been fully vetted for Thread-safeness.
 * DO NOT BUILD ANYTHING CRITICAL BASED ON THIS until you have done so.
 * See the Scott Oaks or Doug Lea books on Threaded Java for design issues.
 * YOU HAVE BEEN WARNED!!
 *
 * @author	Ian F. Darwin, ian@darwinsys.com
 */
public class ChatServer {
	/** What I call myself in system messages */
	final static String IDENT = "ChatMaster";
	/** What goes between any handle and the message */
	final static String SEP = ": ";
	/** The Server Socket */
	ServerSocket servSock;
	/** The list of my current clients */
	Vector clients;

	/** Main just constructs a ChatServer, which should never return */
	public static void main(String argv[]) {
		System.out.println("DarwinSys ChatRoom Server 0.1 starting...");
		ChatServer w = new ChatServer();
		System.out.println("**ERROR* Chat Server 0.1 quitting");
	}

	/** Construct (and run!) a Chat Service */
	ChatServer() {
		clients = new Vector();
		try {
			servSock = new ServerSocket(ChatRoom.PORTNUM);
			System.out.println("DarwinSys Chat Server Listening on port " +
				ChatRoom.PORTNUM);
			while (true) {
				Socket us = servSock.accept();
				String hostName = us.getInetAddress().getHostName();
				System.out.println("Accepted from " + hostName);
				ChatHandler cl = new ChatHandler(us, hostName);
				clients.addElement(cl);
				cl.start();
				if (clients.size() == 1)
					cl.send(IDENT, "Welcome! you're the first one here");
				else {
					cl.send(IDENT, "Welcome! you're the latest of " +
						clients.size() + " users.");
					cl.broadcast(IDENT, cl.handle + 
						" joins us, for a total of " + 
						clients.size() + " users");
				}
			}
		} catch(IOException e) {
			System.err.println(e);
			System.exit(0);
			return;
		}
	}

	/** Handle one conversation */
	class ChatHandler extends Thread {
		/** BufferedReader for reading from socket */
		protected BufferedReader is;
		/** PrintWriter for sending lines on socket */
		protected PrintWriter pw;
		/** String handle */
		String handle;

		/* Construct a Chat Handler */
		public ChatHandler(Socket sock, String clnt) throws IOException {
			handle = clnt;
			is = new BufferedReader(
				new InputStreamReader(sock.getInputStream()));
			pw = new PrintWriter(sock.getOutputStream(), true);
		}

		/** Each ChatHandler is a Thread, so here's the run() method,
		 * which handles this conversation.
		 */
		public void run() {
			String line;
			try {
				while ((line = is.readLine()) != null) {
					broadcast(handle, line);
				}
			} catch (IOException e) {
				// null
			} finally {
				// the sock ended, so we're done, bye now
				// Can NOT send a good-bye message, until we have
				// a simple command-based protocol in place.
				System.out.println(handle + SEP + "All Done");
				clients.removeElement(this);
				if (clients.size() == 0) {
					System.out.println(IDENT + SEP +
						"I'm so lonely I could cry...");
				} else if (clients.size() == 1) {
					ChatHandler last = (ChatHandler)clients.elementAt(0);
					last.send(IDENT,
						"Hey, you're talking to yourself again");
				} else {
					broadcast(IDENT,
						"There are now only " + clients.size() + " users");
				}
			}
		}

		/** Send one message to this user */
		public void send(String sender, String mesg) {
			pw.println(sender + SEP + mesg);
		}
		
		/** Send one message to all users */
		public void broadcast(String sender, String mesg) {
			System.out.println("Broadcasting " + sender + SEP + mesg);
			for (int i=0; i<clients.size(); i++) {
				ChatHandler sib = (ChatHandler)clients.elementAt(i);
				System.out.println("Sending to " + sib);
				sib.send(sender, mesg);
			}
			System.out.println("Done broadcast");
		}

		/** Present this ChatHandler as a String */
		public String toString() {
			return "ChatHandler[" + handle + "]";
		}
	}
}
