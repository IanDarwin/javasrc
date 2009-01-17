package ejb2.musicrecording.beans;

import java.rmi.RemoteException;
import java.util.Collection;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;
import javax.ejb.FinderException;

/** Remote Home interface for Publisher EJB Entity.
 * @author Ian Darwin
 */
public interface PublisherRemoteHome extends EJBHome {

	/** Create */
	public PublisherRemote create(int pkey, String pubName,
		String city, String phone) throws CreateException, RemoteException;

	/** Find by pkey */
	public PublisherRemote findByPrimaryKey(Integer who)
		throws FinderException, RemoteException;

	/** Find by name */
	public PublisherRemote findByName(String who)
		throws FinderException, RemoteException;

	/** Get list of all (likely to be only a few dozen) */
	public Collection<PublisherRemote> findAllPublishers()
		throws FinderException, RemoteException;
}
