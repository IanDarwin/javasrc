package com.darwinsys.ejb;

import java.rmi.*;
import javax.ejb.*;

/**
 *  List of client methods for ServInfo
 *
 *  @author MkBean
 */
public interface ServInfo extends EJBObject  {

	public Object getEnv(String eName) throws RemoteException;
}
