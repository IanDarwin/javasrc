package ejb2.hello.src;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;

public interface HelloHome extends EJBHome {
	/** Stateless Session beans MUST have one create() method. */
	public Hello create() throws CreateException, RemoteException;
}
