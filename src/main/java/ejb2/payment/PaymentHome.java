package com.darwinsys.orders;

import javax.ejb.*;
import java.rmi.RemoteException;

public interface PaymentHome extends EJBHome {
	// Stateless Session must have exactly one method, create(), in Home.
	public Payment create() throws CreateException, RemoteException;
}
