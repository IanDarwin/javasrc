package com.darwinsys.client;

import java.rmi.*;

/** Client -- the interface for the client callback */
public interface Client extends Remote {
	public void alert(String mesg) throws RemoteException;
}
