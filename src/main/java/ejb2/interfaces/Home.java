package ejb2.interfaces;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;

public interface Home extends EJBHome {

	public Remote create() throws CreateException, RemoteException;

}
