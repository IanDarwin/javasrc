package ejb2.orders;

import java.rmi.RemoteException;

import javax.ejb.EJBObject;

/**
 *  List of client methods for OrderLineItem
 *
 *  @author MkBean
 */
public interface OrderLineItem extends EJBObject  {

	public int getProduct() throws RemoteException;
	public void setProduct(int p) throws RemoteException;
	public int getQuantity() throws RemoteException;
	public void setQuantity(int p) throws RemoteException;
}
