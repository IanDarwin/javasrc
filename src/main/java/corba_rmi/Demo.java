package corba_rmi;

import java.rmi.*;

public interface Demo extends Remote {
	public String getNext() throws RemoteException;
}
