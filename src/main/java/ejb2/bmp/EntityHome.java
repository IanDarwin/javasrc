import javax.ejb.*;
import java.rmi.RemoteException;
import java.util.Collection;

public interface EntityHome extends EJBHome {

	public String create(Recording rec) throws CreateException, RemoteException;

	public Entity findByPrimaryKey(int recid) throws FinderException, RemoteException;

	public Collection findAll() throws FinderException, RemoteException;
}
