package ejb.billing;

import java.rmi.RemoteException;

import javax.ejb.EJBException;
import javax.ejb.EntityBean;
import javax.ejb.EntityContext;
import javax.ejb.RemoveException;

public class BillingBean implements EntityBean {

	private static final long serialVersionUID = 3257286915907661877L;
	int amount;

	public void ejbCreate() {
		amount = 0;
	}

	public void setTotal(int amt) {
		amount = amt;
	}

	public void setEntityContext(EntityContext arg0) throws EJBException, RemoteException {
		// TODO Auto-generated method stub
		
	}

	public void unsetEntityContext() throws EJBException, RemoteException {
		// TODO Auto-generated method stub
		
	}

	public void ejbRemove() throws RemoveException, EJBException, RemoteException {
		// TODO Auto-generated method stub
		
	}

	public void ejbActivate() throws EJBException, RemoteException {
		// TODO Auto-generated method stub
		
	}

	public void ejbPassivate() throws EJBException, RemoteException {
		// TODO Auto-generated method stub
		
	}

	public void ejbLoad() throws EJBException, RemoteException {
		// TODO Auto-generated method stub
		
	}

	public void ejbStore() throws EJBException, RemoteException {
		// TODO Auto-generated method stub
		
	}
}
