package ejb2.interfaces;

import java.rmi.RemoteException;

public interface MyMethods {

	/** This method will have RemoteException only in the Remote */
	public int computeMeaning() throws RemoteException;

}
