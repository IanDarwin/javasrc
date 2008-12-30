package ejb2.musicrecording.beans;

import java.rmi.RemoteException;

import javax.ejb.EJBObject;

/** Publisher EJB.
 */
public interface PublisherRemote extends EJBObject {

	// A Local or Remote interface may list the abstract
	// accessors EXCEPT for the pkey, which isn't changeable

	public abstract Integer getId() throws RemoteException;

	public abstract String getName() throws RemoteException;
	public abstract void setName(String val) throws RemoteException;

	public abstract String getCity() throws RemoteException;
	public abstract void setCity(String val) throws RemoteException;

}
