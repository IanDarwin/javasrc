package ejb2.musicrecording.beans;

import java.rmi.RemoteException;

import javax.ejb.EJBObject;

/**
 * Remote business interface for the "put recordings on sale" Facade.
 * @author Ian Darwin
 */
public interface SaleRemote extends EJBObject {

	/** Silly text-message interace, just for pinging the server */
	public String sayHello(String name) throws RemoteException;

	/**
	 * Put all the recordings on sale by the given percentage.
	 * Non-idempotent; at present there is a single price column;
	 * there should be a base price and a "current sale price".
	 */
	public boolean markDown(double delta) throws RemoteException;
}
