package ejb2.orders;

import java.rmi.RemoteException;
import java.util.Collection;

import javax.ejb.EJBObject;

/**
 *  List of client methods for Order
 *
 *  @author MkBean
 */
public interface OrderRemote extends EJBObject  {

	public Integer getCustomer() throws RemoteException;
	public void setCustomer(Integer i) throws RemoteException;

	public Collection getOrderItems() throws RemoteException;
	public void setOrderItems(Collection c) throws RemoteException;

	public void addItem(int product, int qty) throws RemoteException;
}
