package cart;

import javax.ejb.*;
import java.rmi.RemoteException;

public interface CartHome extends EJBHome {
	public Cart create() throws RemoteException, CreateException;
}
