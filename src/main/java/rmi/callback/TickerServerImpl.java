package darwinsys.callback;

import darwinsys.client.*;

import java.rmi.*;
import java.rmi.server.*;
import java.util.*;

public class RegisterImpl 
	extends UnicastRemoteObject 
	implements RegisterInterface, Runnable
{
	ArrayList list = new ArrayList();

	/** Construct the object that implements the remote server.
	 * Called from main, after it has the SecurityManager in place.
	 */
	public RegisterImpl() throws RemoteException {
		super();	// sets up networking

		// Start background thread to track stocks :-) and alert users.
		new Thread(this).start();
	}

	/** The remote method that "does all the work". This won't get
	 * called until the client starts up.
	 */
	public void register(ClientInterface da) throws RemoteException {
		list.add(da);
	}

	boolean done = false;
	Random rand = new Random();

	public void run() {
		while (!done) {
			try {
				Thread.sleep(10 * 1000);
				for (int i=0; i<list.size(); i++) {
					String mesg = ("Your stock price went " +
						(rand.nextFloat() > 0.5 ? "up" : "down") + "!");
					((ClientInterface)list.get(i)).alert(mesg);
				}
			} catch (RemoteException re) {
				System.out.println("RemoteException when alerting client.");
				done = true;
			} catch (InterruptedException unexpected) {
				System.out.println("WAHHH!");
				done = true;
			}
		}
	}
}
