package com.darwinsys.callback;

import com.darwinsys.client.*;

import java.rmi.*;
import java.rmi.server.*;
import java.util.*;

/** This is the main class of the server */
public class TickerServerImpl
	extends UnicastRemoteObject
	implements TickerServer, Runnable
{
	ArrayList list = new ArrayList();

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
