package com.darwinsys.client;

import java.rmi.*;

/** ClientInterface -- the interface for the client callback */
public interface ClientInterface extends Remote {
	public void alert(String mesg) throws RemoteException;
}
