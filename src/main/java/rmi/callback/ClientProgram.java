package com.darwinsys.client;

import com.darwinsys.callback.*;

import java.io.*;
import java.rmi.*;
import java.rmi.server.*;

/** This class tries to be all things to all people:
 *	- main program for client to run.
 *	- "server" program for remote to use ClientInterface of
 */
public class ClientProgram 
	extends UnicastRemoteObject 
	implements ClientInterface
{

	/** No-argument constructor required as we are a Remote Object */
	public ClientProgram() throws RemoteException {
	}

	/** This is the main program, just to get things started. */
	public static void main(String[] argv) throws IOException, NotBoundException {
		new ClientProgram().do_the_work();
	}

	/** This is the server program part */
	private void do_the_work() throws IOException, NotBoundException {

		// First, register us with the RMI registry
		Naming.rebind("Client", this);
	
		// Now, find the server, and register with it
		RegisterInterface server = 
			(RegisterInterface)Naming.lookup("Server");
		// This should cause the server to call us back.
		server.register(this);
	}

	/** This is the client callback */
	public void alert(String message) throws RemoteException {
		System.out.println(message);
	}
}
