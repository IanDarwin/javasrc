import agents.*;
import servers.*;

import java.lang.reflect.*;
import java.io.*;
import java.rmi.*;
import java.rmi.server.*;

/** This class tries to be all things to all people:
 *	- main program for client to run.
 *  - creator of the "Agent" object to run remotely.
 *	- "server" program for remote to use Client of
 */
public class ClientProgram {

	protected final static String host = "localhost";

	/** This is the main program, just to get things started. */
	public static void main(String[] argv) throws Exception {
		new ClientProgram().do_the_work();
	}

	/** This is the server program part */
	private void do_the_work() throws Exception {

		System.out.println("Client starting");

		// Find the server, and register with it
		System.out.println("Finding server");
		RunServer server = 
			(RunServer)Naming.lookup("rmi://" + host + "/" +
			RunServer.LOOKUP_NAME);

		// Create the Agent as an un-exported Remote
		AgentImpl guts = new AgentImpl();

		// This should cause the server to call us back.
		System.out.println("Passing Agent to server");
		RemoteStub rs = server.connect(guts);
		System.out.println(rs);
		RemoteRef rr = rs.getRef();

		System.out.println("Client program ready.");

		Class[] args = { double.class };
		Method setThreshold = Agent.class.getMethod("setThreshold", args);
		Method getThreshold = Agent.class.getMethod("getThreshold", null);
		//Agent myClient = null;
		for (int i = 0; i < 10; i++) {

			//myClient.setThreshold(10 * i);
			Object[] setArgs = { new Double(10) };
			rr.invoke(rs, setThreshold, setArgs, setThreshold.hashCode());

			//System.out.println("Threshold now " + myClient.getThreshold());
			// ...
			try { Thread.sleep(1000); } catch (Exception ex) { /* */ }
		}
	}
}
