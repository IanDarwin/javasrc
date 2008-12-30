package ejb2.cart.src.cart;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;

public interface CartHome extends EJBHome {
	public Cart create() throws RemoteException, CreateException;
}
