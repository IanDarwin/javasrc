package rain;

import java.rmi.*;
import javax.ejb.*;

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
