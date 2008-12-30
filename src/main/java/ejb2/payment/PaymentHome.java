package ejb2.payment;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;

public interface PaymentHome extends EJBHome {
	// Stateless Session must have exactly one method, create(), in Home.
	public Payment create() throws CreateException, RemoteException;
}
