package rain;

import java.rmi.*;
import javax.ejb.*;

/**
 *  List of client methods for Order
 *
 *  @author MkBean
 */
public interface Order extends EJBObject  {

	public int getCustomer() throws RemoteException;
	public void setCustomer(int cust) throws RemoteException;
}
