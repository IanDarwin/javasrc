package rmi.callback;

import java.rmi.Remote;
import java.rmi.RemoteException;

/** Client -- the interface for the client callback */
public interface Client extends Remote {
	public void alert(String mesg) throws RemoteException;
}
