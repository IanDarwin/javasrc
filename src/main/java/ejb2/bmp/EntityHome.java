import javax.ejb.*;
import java.rmi.RemoteException;

public interface EntityHome extends EJBHome {

	public String create(Recording rec) throws CreateException, RemoteException

	public Entity findByPrimaryKey(String title) throws FinderException, RemoteException;

	public Collection findAll() throws FinderException, RemoteException;
}
