package rain;

import java.rmi.*;
import javax.ejb.*;

/**
 *  Home interface for the OrderLineItem EJB.
 *
 *  @author MkBean
 */

public interface OrderLineItemHome extends EJBHome {

	// Create Methods
	public OrderLineItem create(Integer ord, int prod, int qty) throws RemoteException, CreateException;
	// Find Methods
	public  OrderLineItem findByPrimaryKey(Integer ord) throws RemoteException;
}
