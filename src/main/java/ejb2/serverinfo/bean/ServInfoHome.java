package ejb2.serverinfo.bean;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;

/**
 *  Home interface for the ServInfo EJB.
 *
 *  @author MkBean
 */

public interface ServInfoHome extends EJBHome {

	// Create Methods
	public ServInfo create() throws CreateException, RemoteException;
}
