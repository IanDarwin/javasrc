package com.darwinsys.ejb;

import java.rmi.*;
import javax.ejb.*;

/**
 *  Home interface for the ServInfo EJB.
 *
 *  @author MkBean
 */

public interface ServInfoHome extends EJBHome {

	// Create Methods
	public ServInfo create() throws CreateException, RemoteException;
}
