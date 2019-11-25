package ejb2.serverinfo.bean;

import java.rmi.RemoteException;

import javax.ejb.EJBObject;

/**
 *  List of client methods for ServInfo
 *
 *  @author MkBean
 */
public interface ServInfo extends EJBObject  {

	/** getEnv retrieves an object from the java:comp/env environment
	 * in the EJB container, and returns it to the client.
	 */
	public Object getEnv(String eName) throws RemoteException;
}
