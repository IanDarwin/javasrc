package ejb2.orders;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;
import javax.ejb.FinderException;

/**
 *  Home interface for the Order EJB.
 *  @author MkBean
 */
public interface OrderHome extends EJBHome {

	// Create Methods
	public OrderRemote create(Integer ord, Integer cust) throws RemoteException, CreateException;

	// Find Methods
	public  OrderRemote findByPrimaryKey(Integer ord) throws FinderException, RemoteException;
}
