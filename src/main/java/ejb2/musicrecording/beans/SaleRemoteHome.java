package ejb2.musicrecording.beans;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;

/**
 * Remote Home interface for the "put recordings on sale" Facade.
 * @author Ian Darwin
 */
public interface SaleRemoteHome extends EJBHome {
	/** Create the session facade */
	public SaleRemote create() throws CreateException, RemoteException;
}
