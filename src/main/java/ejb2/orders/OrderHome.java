package orders;

import java.rmi.*;
import javax.ejb.*;

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
