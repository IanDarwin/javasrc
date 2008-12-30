package ejb2.orders;

import java.rmi.RemoteException;

import javax.ejb.EJBObject;

/**
 *  List of client methods for Order
 *
 *  @author MkBean
 */
public interface Order extends EJBObject  {

	public int getCustomer() throws RemoteException;
	public void setCustomer(int cust) throws RemoteException;
}
