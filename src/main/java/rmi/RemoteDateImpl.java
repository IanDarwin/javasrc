package darwinsys;

import java.rmi.*;
import java.rmi.server.*;
import java.util.*;

public class RemoteDateImpl extends UnicastRemoteObject implements RemoteDate
{
	/* Main, like any normal main, creates an instance of this class.
	 * This one also has to create a SecurityManager and also
	 * register with the RMI server.
	 */
	public static void main(String args[]) {
		// Must create and install a security manager BEFORE
		// trying to create the object itself.
		System.setSecurityManager(new RMISecurityManager());
		try {
			RemoteDateImpl obj = new RemoteDateImpl();
			Naming.rebind("//daroad/DateServer", obj);
			System.out.println("HelloServer bound in registry");
		} catch (Exception e) {
			System.out.println("HelloImpl err: " + e.getMessage());
			e.printStackTrace();
		}
	}

	/** Construct the object that implements the remote server.
	 * Called from main, after it has the SecurityManager in place.
	 */
	public RemoteDateImpl() throws RemoteException {
		super();	// actually sets up networking
	}

	/** The remote method that "does all the work". This wont get
	 * called until the client starts up.
	 */
	public Date getRemoteDate() throws RemoteException {
		return new Date();
	}
}
