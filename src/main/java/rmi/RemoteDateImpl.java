package rmi;

package darwinsys.distdate;

import java.rmi.*;
import java.rmi.server.*;
import java.util.*;

public class RemoteDateImpl extends UnicastRemoteObject implements RemoteDate
{
	/** Construct the object that implements the remote server.
	 * Called from main, after it has the SecurityManager in place.
	 */
	public RemoteDateImpl() throws RemoteException {
		super();	// sets up networking
	}

	/** The remote method that "does all the work". This won't get
	 * called until the client starts up.
	 */
	public Date getRemoteDate() throws RemoteException {
		return new Date();
	}
}
