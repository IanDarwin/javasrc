package rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDateTime;

// tag::main[]
public class RemoteDateImpl extends UnicastRemoteObject implements RemoteDate {

	/** Construct the object that implements the remote server.
	 * Called from main, after it has the SecurityManager in place.
	 */
	public RemoteDateImpl() throws RemoteException {
		super();	// sets up networking
	}

	/** The remote method that "does all the work". This won't get
	 * called until the client starts up.
	 */
	public LocalDateTime getRemoteDate() throws RemoteException {
		return LocalDateTime.now();
	}
}
// end::main[]
