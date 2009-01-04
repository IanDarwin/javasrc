package rmi.callback;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/** This is the main class of the server */
public class TickerServerImpl
	extends UnicastRemoteObject
	implements TickerServer, Runnable
{
	private static final long serialVersionUID = -464196277362659008L;
	List<Client> list = new ArrayList<Client>();

	/** Construct the object that implements the remote server.
	 * Called from main, after it has the SecurityManager in place.
	 */
	public TickerServerImpl() throws RemoteException {
		super();	// sets up networking
	}

	/** Start background thread to track stocks :-) and alert users. */
	public void start() {
		new Thread(this).start();
	}

	/** The remote method that "does all the work". This won't get
	 * called until the client starts up.
	 */
	public void connect(Client da) throws RemoteException {
		System.out.println("Adding client " + da);
		list.add(da);
	}

	boolean done = false;
	Random rand = new Random();

	public void run() {
		while (!done) {
			try {
				Thread.sleep(10 * 1000);
				System.out.println("Tick");
			} catch (InterruptedException unexpected) {
				System.out.println("WAHHH!");
				done = true;
			}
			Iterator it = list.iterator();
			while (it.hasNext()){
				String mesg = ("Your stock price went " +
					(rand.nextFloat() > 0.5 ? "up" : "down") + "!");
				// Send the alert to the given user.
				// If this fails, remove them from the list
				try {
					((Client)it.next()).alert(mesg);
				} catch (RemoteException re) {
                    System.out.println(
						"Exception alerting client, removing it.");
					System.out.println(re);
					it.remove();
				}
			}
		}
	}
}
