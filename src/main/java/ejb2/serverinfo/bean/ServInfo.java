package com.darwinsys.ejb;

import java.rmi.*;
import javax.ejb.*;

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
