package rmi;

import java.time.LocalDateTime;

// tag::main[]
/** A statement of what the client & server must agree upon. */
public interface RemoteDate extends java.rmi.Remote {

	/** The method used to get the current date on the remote */
	public LocalDateTime getRemoteDate() throws java.rmi.RemoteException;

	/** The name used in the RMI registry service. */
	public final static String LOOKUPNAME = "RemoteDate";
}
// end::main[]
