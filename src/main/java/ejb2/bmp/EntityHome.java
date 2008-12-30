package ejb2.bmp;

import java.rmi.RemoteException;
import java.util.Collection;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;
import javax.ejb.FinderException;

public interface EntityHome extends EJBHome {

	public String create(Recording rec) throws CreateException, RemoteException;

	public Entity findByPrimaryKey(int recid) throws FinderException, RemoteException;

	public Collection findAll() throws FinderException, RemoteException;
}
